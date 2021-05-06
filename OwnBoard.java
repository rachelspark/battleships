/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleships;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
/**
 *
 * @author rachelpark
 */
public class OwnBoard {
    private static final int OCCUPIED = 1;
    private static final int EMPTY = 0;
    private static final int HIT = 2;
    
    private static final int AIRCRAFT_LENGTH = 5;
    private static final int BATTLESHIP_LENGTH = 4;
    private static final int SUBMARINE_LENGTH = 3;
    private static final int PATROL_LENGTH = 2;
    
    private int aircraftCount = 1;
    private int battleshipCount = 2;
    private int submarineCount = 2;
    private int patrolCount = 4;
    
    private int currentShipLength;
    
    private int[][] cells;
    private ArrayList<Ship> ships;
    
    public OwnBoard() {
        cells = new int[10][10];
        ships = new ArrayList<>();
    }
    
    public void setCellState(int row, int col, int state) {
        cells[row][col] = state;
    }
    
    public int getCellState(int row, int col) {
        return cells[row][col];
    }
    
    public boolean shipIsVertical(int firstRow, int firstCol, int lastRow, int lastCol) {
        return(firstCol == lastCol);
    }
    
    public boolean shipIsHorizontal(int firstRow, int firstCol, int lastRow, int lastCol) {
        return(firstRow == lastRow);
    }
    
    public boolean shipIsValid(int firstRow, int firstCol, int lastRow, int lastCol) {
        if(shipIsVertical(firstRow, firstCol, lastRow, lastCol)) {
            if(firstRow < lastRow) {
                for(int r = firstRow + 1; r < lastRow; r++) {
                    if(cells[r][firstCol] == OCCUPIED) return false;
                }
            }
            else {
                for(int r = lastRow; r < firstRow; r++) {
                    if(cells[r][firstCol] == OCCUPIED) return false;
                }
            }
            currentShipLength = Math.abs(firstRow - lastRow) + 1;
            switch(currentShipLength) {
                case AIRCRAFT_LENGTH:
                    if(aircraftCount > 0) aircraftCount--;
                    else return false;
                    break;
                case BATTLESHIP_LENGTH:
                    if(battleshipCount > 0) battleshipCount--;
                    else return false;
                    break;
                case SUBMARINE_LENGTH:
                    if(submarineCount > 0) submarineCount--;
                    else return false;
                    break;
                case PATROL_LENGTH:
                    if(patrolCount > 0) patrolCount--;
                    else return false;
                    break;
                default:
                    return false;
            }
            return true;
        }
        else if(shipIsHorizontal(firstRow, firstCol, lastRow, lastCol)) {
            if(firstCol < lastCol) {
                for(int c = firstCol + 1; c < lastCol; c++) {
                    if(cells[firstRow][c] == OCCUPIED) return false;
                }
            }
            else {
                for(int c = lastCol + 1; c < firstCol; c++) {
                    if(cells[firstRow][c] == OCCUPIED) return false;
                }
            }
            currentShipLength = Math.abs(firstCol - lastCol) + 1;
            switch(currentShipLength) {
                case AIRCRAFT_LENGTH:
                    if(aircraftCount > 0) aircraftCount--;
                    else return false;
                    break;
                case BATTLESHIP_LENGTH:
                    if(battleshipCount > 0) battleshipCount--;
                    else return false;
                    break;
                case SUBMARINE_LENGTH:
                    if(submarineCount > 0) submarineCount--;
                    else return false;
                    break;
                case PATROL_LENGTH:
                    if(patrolCount > 0) patrolCount--;
                    else return false;
                    break;
                default:
                    return false;
            }
            return true;
        }
        return false;
    }
    
    public void setShip(int firstRow, int firstCol, int lastRow, int lastCol) {
        if(shipIsVertical(firstRow, firstCol, lastRow, lastCol)) {
            if(firstRow < lastRow) {
                for(int r = firstRow; r <= lastRow; r++) {
                    cells[r][firstCol] = OCCUPIED;
                }
            }
            else {
                for(int r = lastRow; r <= firstRow; r++) {
                    cells[r][firstCol] = OCCUPIED;
                }
            }
        }
        else {
            if(firstCol < lastCol) {
                for(int c = firstCol; c <= lastCol; c++) {
                    cells[firstRow][c] = OCCUPIED;
                }
            }
            else {
                for(int c = lastCol; c <= firstCol; c++) {
                    cells[firstRow][c] = OCCUPIED;
                }
            }
        }
        Ship currentShip = new Ship(firstRow, firstCol, lastRow, lastCol);
        ships.add(currentShip);
    }
    
    public boolean allShipsPlaced() {
        return (aircraftCount == 0 && battleshipCount == 0 && submarineCount == 0 && patrolCount == 0);
    }
    
}
