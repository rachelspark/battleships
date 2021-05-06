/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleships;

/**
 *
 * @author rachelpark
 */
public class EnemyBoard {
    private static int HIT = 2;
    private static int MISS = 1;
    private static int UNKNOWN = 0;
    
    private int[][] cells;
    
    public EnemyBoard() {
        cells = new int[10][10];
    }
    
    public void setCellState(int row, int col, int state) {
        cells[row][col] = state;
    }
    
    public int getCellState(int row, int col) {
        return cells[row][col];
    }
}
