/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleships_automated;

/**
 *
 * @author rachelpark
 */
public class GameMessage extends Message {
    private static int HIT = 2;
    private static int MISS = 1;
    private static int UNKNOWN = 0;
    
    private int row;
    private int col;
    private int state;
           
    public GameMessage(int r, int c) {
        row = r;
        col = c;
        state = UNKNOWN;
    }
    
    public GameMessage(int r, int c, int s) {
        row = r;
        col = c;
        state = s;
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
    
    public void setState(int s) {
        state = s;
    }
    
    public boolean hasState() {
        return state != UNKNOWN;
    }
}
