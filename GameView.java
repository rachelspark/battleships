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
public class GameView extends JPanel{
    private static final int HIT = 2;
    private static final int MISS = 1;
    private static final int UNKNOWN = 0;
    
    private OwnBoardView ownBoard;
    private EnemyBoardView enemyBoard;
    
    public GameView(OwnBoard own, EnemyBoard enemy) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        ownBoard = new OwnBoardView(own);
        add(ownBoard, BorderLayout.WEST);
        
        enemyBoard = new EnemyBoardView(enemy);
        add(enemyBoard, BorderLayout.EAST);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setSize(new Dimension(300, 50));
        JRadioButton hit = new JRadioButton("Hit");
        hit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enemyBoard.setHitState(HIT);
            }
        });
        JRadioButton miss = new JRadioButton("Miss");
        miss.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enemyBoard.setHitState(MISS);
            }
        });
        
        ButtonGroup group = new ButtonGroup();
        group.add(hit);
        group.add(miss);
        
        buttonPanel.add(hit);
        buttonPanel.add(miss);
        
        add(buttonPanel, BorderLayout.SOUTH);

    }
}
