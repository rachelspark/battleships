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
public class GameView extends JPanel {
    private static final int HIT = 2;
    private static final int MISS = 1;
    private static final int UNKNOWN = 0;
    
    private OwnBoardView ownBoard;
    private EnemyBoardView enemyBoard;
    
    public GameView(OwnBoard own, EnemyBoard enemy) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        

    }
}
