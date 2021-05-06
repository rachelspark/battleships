/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleships_automated;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author rachelpark
 */
public class OwnCell extends JPanel {
    private static final int WIDTH = 40;
    private static final Color OCCUPIED_COLOR = Color.BLACK;
    private static final Color EMPTY_COLOR = Color.LIGHT_GRAY;
    private static final Color HIT_COLOR = Color.RED;
    
    private static final int OCCUPIED = 1;
    private static final int EMPTY = 0;
    private static final int HIT = 2;
    
    private OwnBoard board;
    
    private int row, state, col;
   
    
    public OwnCell(OwnBoard b, int r, int c) {
        super();
        board = b;
        
        row = r;
        col = c;
        state = b.getCellState(row, col);
        
        setSize(WIDTH, WIDTH);
        
        if(state % 2 == 1) setOccupied();
        else setEmpty();
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
    
    public int getState() {
        return state;
    }
    
    public void setOccupied() {
        board.setCellState(row, col, OCCUPIED);
        setBackground(OCCUPIED_COLOR);
    }
    
    public void setEmpty() {
        board.setCellState(row, col, EMPTY);
        setBackground(EMPTY_COLOR);
    }
    
    public void setHit() {
        board.setCellState(row, col, HIT);
        setBackground(HIT_COLOR);
    }
}
