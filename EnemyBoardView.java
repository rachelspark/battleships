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
public class EnemyBoardView extends JPanel {
    private static final int WIDTH = 400; 
    private static final int NUM_ROWS = 11;
    private static final int NUM_COLS = 11;
    private static final int GAP = 1;
    
    private static final int HIT = 2;
    private static final int MISS = 1;
    private static final int UNKNOWN = 0;
    
    private static final String[] cols = {"  A", "  B", "  C", "  D", "  E", "  F", "  G", "  H", "  I", "  J"};
    private static final String[] rows = {"  1", "  2", "  3", "  4", "  5", "  6", "  7", "  8", "  9", "  10"};
    
    private EnemyBoard board;
    private EnemyCell[][] cells;
    private int currentHitState;
    
    public EnemyBoardView(EnemyBoard b) {
        board = b;
        cells = new EnemyCell[NUM_ROWS][NUM_COLS];
        currentHitState = UNKNOWN;
        
        setLayout(new GridLayout(NUM_ROWS, NUM_COLS, GAP, GAP));
        setPreferredSize(new Dimension(WIDTH, WIDTH));
        setBackground(Color.WHITE);
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
                for(int c = 1; c < NUM_COLS; c++) {
                    cells[r-1][c-1] = new EnemyCell(board, r - 1, c - 1);
                    cells[r-1][c-1].addMouseListener(new EnemyCellListener());
                    add(cells[r-1][c-1]);
                }
            }
        }
    }
    
    public EnemyCell getCell(int r, int c) {
        return cells[r][c];
    }
    
    public int getHitState() {
        return currentHitState;
    }
    
    public void setHitState(int h) {
        currentHitState = h;
    }
    
    public void update() {
        for(int r = 0; r < NUM_ROWS - 1; r++) {
            for(int c = 0; c < NUM_ROWS - 1; c++) {
                if(board.getCellState(r, c) == HIT) {
                    cells[r][c].setHit();
                }
                else if(board.getCellState(r, c) == MISS) {
                    cells[r][c].setMiss();
                }
                else {
                    cells[r][c].setUnknown();
                }
            }
        }
        revalidate();
        repaint();
    }
    
    private class EnemyCellListener implements MouseListener {
        
        public EnemyCellListener() {
            
        }
        
        public void mouseClicked(MouseEvent e) {
            if(e.getSource() instanceof EnemyCell) {
                EnemyCell currentCell = (EnemyCell) e.getSource();
                if(currentHitState == HIT) {
                    currentCell.setHit();
                }
                else if(currentHitState == MISS) {
                    currentCell.setMiss();
                }
                update();
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
