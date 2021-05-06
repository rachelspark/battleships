/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleships_automated;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author rachelpark
 */
public class OwnBoardView extends JPanel {
    private static final int WIDTH = 400;
    private static final int NUM_ROWS = 11;
    private static final int NUM_COLS = 11;
    private static final int GAP = 1;
    
    private static final int OCCUPIED = 1;
    private static final int EMPTY = 0;
    private static final int HIT = 2;
    
    private static final String[] cols = {"  A", "  B", "  C", "  D", "  E", "  F", "  G", "  H", "  I", "  J"};
    private static final String[] rows = {"  1", "  2", "  3", "  4", "  5", "  6", "  7", "  8", "  9", "  10"};
    
    private OwnBoard board;
    private OwnCell[][] cells;
    private OwnCell firstShipCell;
    private OwnCell lastShipCell;
    private ObjectOutputStream outstream;
    
    public OwnBoardView(OwnBoard b, ObjectOutputStream ostream) {
        board = b;
        cells = new OwnCell[10][10];
        outstream = ostream;
        
        setLayout(new GridLayout(NUM_ROWS, NUM_COLS, GAP, GAP));
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(WIDTH, WIDTH));
        for(int r = 0; r < NUM_ROWS; r++) {
            if(r == 0) {
                add(new JLabel());
                for(int c = 1; c < NUM_COLS; c++) {
                    JLabel label = new JLabel(cols[c - 1]);
                    add(label);
                }
            }
            else {
                JLabel label = new JLabel(rows[r - 1]);
                add(label);
                for(int c = 1; c < NUM_ROWS; c++) {
                    cells[r-1][c-1] = new OwnCell(board, r - 1, c - 1);
                    cells[r-1][c-1].addMouseListener(new OwnCellListener());
                    add(cells[r-1][c-1]);
                }
            }
        }
    }
    
    public OwnCell getCell(int row, int col) {
        return cells[row][col];
    }
    
    public void update() {
        for(int r = 0; r < NUM_ROWS - 1; r++) {
            for(int c = 0; c < NUM_ROWS - 1; c++) {
                if(board.getCellState(r, c) == OCCUPIED) {
                    cells[r][c].setOccupied();
                }
                else if(board.getCellState(r, c) == HIT) {
                    cells[r][c].setHit();
                }
            }
        }
        revalidate();
        repaint();
    }
    
    public void fireHit(int row, int col) throws IOException {
        ArrayList<Ship> ships = board.getShips();
        
        if(cells[row][col].getState() != HIT) {
            for(Ship s: ships) {
                if(s.isHit(row, col)) {
                    cells[row][col].setHit();
                    update();
                    if(s.isSunk()) {
                        outstream.writeObject(new TextMessage(s.getName() + " is sunk!"));
                        ships.remove(s);
                        if(ships.size() == 0) {
                            outstream.writeObject(new TextMessage("VICTORY"));
                        }
                    }
                    outstream.writeObject(new GameMessage(row, col, 2));
                    return;
                }
            }
            outstream.writeObject(new GameMessage(row, col, 1));
        }
    }

    
    private class OwnCellListener implements MouseListener {
        
        public OwnCellListener() {
            
        }
        
        public void mouseClicked(MouseEvent e) {
            if(!board.allShipsPlaced()) {
                new Thread(new Runnable() {
                    public void run() {
                        if(firstShipCell == null) {
                            if(e.getSource() instanceof OwnCell) {
                                firstShipCell = (OwnCell) e.getSource();
                                if(firstShipCell.getState() == OCCUPIED) {
                                    firstShipCell = null;
                                } 
                                else firstShipCell.setOccupied();
                            }
                        }
                        else {
                            if(e.getSource() instanceof OwnCell) {
                                lastShipCell = (OwnCell) e.getSource();
                                if(board.shipIsValid(firstShipCell.getRow(), firstShipCell.getCol(), lastShipCell.getRow(), lastShipCell.getCol())) {
                                    board.setShip(firstShipCell.getRow(), firstShipCell.getCol(), lastShipCell.getRow(), lastShipCell.getCol());
                                    update();
                                    firstShipCell = null;
                                    lastShipCell = null;

                                }


                            }
                        }
                    }
                }).start();
            }
        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }
    
}
