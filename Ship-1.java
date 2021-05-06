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
public class Ship {
    private static final int AIRCRAFT_LENGTH = 5;
    private static final int BATTLESHIP_LENGTH = 4;
    private static final int SUBMARINE_LENGTH = 3;
    private static final int PATROL_LENGTH = 2;
    
    private String name;
    private int length;
    private int hitsLeft;
    private boolean isVert;
    
    private int firstRow;
    private int firstCol;
    private int lastRow;
    private int lastCol;

    public Ship(int firstRow, int firstCol, int lastRow, int lastCol) {
        this.firstRow = firstRow;
        this.firstCol = firstCol;
        this.lastRow = lastRow;
        this.lastCol = lastCol;
        if(firstCol == lastCol) {
            isVert = true;
            if(firstRow > lastRow) {
                int temp = firstRow;
                firstRow = lastRow;
                lastRow = temp;
            }
            length = lastRow - firstRow + 1;
        }
        else {
            isVert = false;
            if(firstCol > lastCol) {
                int temp = firstCol;
                firstCol = lastCol;
                lastCol = temp;
            }
            length = lastCol - firstCol + 1;
        }
        hitsLeft = length;  
        switch(length) {
            case AIRCRAFT_LENGTH:
                    name = "Aircraft";
                    break;
                case BATTLESHIP_LENGTH:
                    name = "Battleship";
                    break;
                case SUBMARINE_LENGTH:
                    name = "Submarine";
                    break;
                case PATROL_LENGTH:
                    name = "Patrol Boat";
                    break;
        }
    }
    
    public String getName() {
        return name;
    }
    
    public boolean isHit(int r, int c) {
        if(isVert) {
            if(c == firstCol) {
                if(r >= firstRow && r <= lastRow) {
                    hitsLeft--;
                    return true;
                }
            }
        }
        else {
            if(r == firstRow) {
                if(c >= firstCol && c <= lastCol) {
                    hitsLeft--;
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean isSunk() {
            return (hitsLeft == 0);
    }

}
