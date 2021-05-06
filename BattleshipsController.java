/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleships;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
/**
 *
 * @author rachelpark
 */
public class BattleshipsController extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    
    private JPanel rules;
    private GameView gameView;
    private OwnBoard ownBoard;
    private EnemyBoard enemyBoard;
    private ChatBox chatBox;
    private ChatHistory chatHistory;
    private Chat chat;
    
    public BattleshipsController() {
        super("Battleships");
        setSize(WIDTH, HEIGHT);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        rules = new JPanel();
        rules.setPreferredSize(new Dimension(WIDTH, 80));
        rules.setLayout(new BorderLayout());
        
        JTextArea instructions = new JTextArea("Instructions:\nPlease place your ships on the left board by clicking the first and last spaces for each ship. Once you are finished placing your ships, please communicate with your opponent using the chat box below to see if you are both ready. If you are both ready, then play Battleships through the chat box and keep track of your hits and misses on the right board.");
        instructions.setLineWrap(true);
        instructions.setWrapStyleWord(true);
        instructions.setPreferredSize(new Dimension(600, 80));
        JScrollPane instructionsScroll = new JScrollPane(instructions, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_NEVER);
        rules.add(instructionsScroll, BorderLayout.EAST);
        
        JTextArea numOfShips = new JTextArea("    Number of Ships:\n    Aircraft Carrier: 1\n    Battleship: 2\n    Submarine: 2\n    Patrol Boat: 4\n");
        numOfShips.setEditable(false);
        numOfShips.setPreferredSize(new Dimension(200, 80));
        rules.add(numOfShips, BorderLayout.WEST);
        add(rules, BorderLayout.NORTH);
        
        ownBoard = new OwnBoard();
        enemyBoard = new EnemyBoard();
        gameView = new GameView(ownBoard, enemyBoard);
        add(gameView, BorderLayout.CENTER);
        
        JPanel chatArea = new JPanel();
        chatArea.setLayout(new BorderLayout());
        
        chatBox = new ChatBox();
        chatArea.add(chatBox, BorderLayout.WEST);
        
        chatHistory = new ChatHistory();
        chatArea.add(chatHistory, BorderLayout.EAST);
        add(chatArea, BorderLayout.SOUTH);
        
        chat = new Chat(chatBox, chatHistory);
        chat.start();
        
    }
}
