/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleships;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
    
    public OwnBoardView(OwnBoard b) {
        board = b;
        cells = new OwnCell[10][10];
        
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

    
    private class OwnCellListener implements MouseListener {
        
        public OwnCellListener() {
            
        }
        
        public void mouseClicked(MouseEvent e) {
            if(!board.allShipsPlaced()) {
                if(firstShipCell == null) {
                    if(e.getSource() instanceof OwnCell) {
                        firstShipCell = (OwnCell) e.getSource();
                        if(firstShipCell.getState() == OCCUPIED) {
                            firstShipCell = null;
                            return;
                        } 
                        firstShipCell.setOccupied();
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
            else {
                if(e.getSource() instanceof OwnCell) {
                    firstShipCell = (OwnCell) e.getSource();
                    firstShipCell.setHit();
                    update();
 
                }
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
