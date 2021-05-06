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
public class EnemyCell extends JPanel {
    private static final int WIDTH = 40;
    private static final Color HIT_COLOR = Color.RED;
    private static final Color MISS_COLOR = Color.WHITE;
    private static final Color UNKNOWN_COLOR = Color.LIGHT_GRAY;
    
    private static int HIT = 2;
    private static int MISS = 1;
    private static int UNKNOWN = 0;
    
    private EnemyBoard board;
    private int row, state, col;
    
    public EnemyCell(EnemyBoard b, int r, int c) {
        board = b;
        row = r;
        col = c;
        
        setSize(WIDTH, WIDTH);
        
        state = board.getCellState(row, col);
        
        if(state == HIT) {
            setHit();
        }
        else if(state == MISS) {
            setMiss();
        }
        else {
            setBackground(UNKNOWN_COLOR);
        }
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
    
    
    public void setUnknown() {
        setBackground(UNKNOWN_COLOR);
        board.setCellState(row, col, UNKNOWN);
    }
    
    public void setHit() {
        setBackground(HIT_COLOR);
        board.setCellState(row, col, HIT);
    }
    
    public void setMiss() {
        setBackground(MISS_COLOR);
        board.setCellState(row, col, MISS);
    }
}
