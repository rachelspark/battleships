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
public class Battleships {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BattleshipsController battleships = new BattleshipsController();
        battleships.setVisible(true);
    }
    
}
