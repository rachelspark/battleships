/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleships_automated;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
/**
 *
 * @author rachelpark
 */
public class BattleshipsController extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int PORT = 4321;
    
    private Socket socket;
    private ObjectOutputStream outstream;
    
    private JPanel rules;

    private OwnBoard ownBoard;
    private OwnBoardView ownBoardView;
    private EnemyBoard enemyBoard;
    private EnemyBoardView enemyBoardView;
    private ChatBox chatBox;
    private ChatHistory chatHistory;
    
    public BattleshipsController() {
        super("Battleships");
        startConnection();
        setUpGUI();
    }
    
    public void setUpGUI() {
        setSize(WIDTH, HEIGHT);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        rules = new JPanel();
        rules.setPreferredSize(new Dimension(WIDTH, 80));
        rules.setLayout(new BorderLayout());
        
        JTextArea instructions = new JTextArea("Instructions:\nPlease place your ships on the left board by clicking the first and last spaces for each ship. Once you are finished placing your ships, please communicate with your opponent using the chat box below to see if you are both ready. If you are both ready, then play Battleships by clicking on the right board to fire shots one at a time. First person to sink all boats wins!");
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
        
        JPanel gameView = new JPanel();
        gameView.setLayout(new BorderLayout());
        ownBoard = new OwnBoard();
        ownBoardView = new OwnBoardView(ownBoard, outstream);
        enemyBoard = new EnemyBoard();
        enemyBoardView = new EnemyBoardView(enemyBoard, outstream);
        gameView.add(ownBoardView, BorderLayout.WEST);
        gameView.add(enemyBoardView, BorderLayout.EAST);
        add(gameView, BorderLayout.CENTER);
        
        JPanel chatArea = new JPanel();
        chatArea.setLayout(new BorderLayout());
        
        chatBox = new ChatBox(outstream);
        chatArea.add(chatBox, BorderLayout.WEST);
        
        chatHistory = new ChatHistory();
        chatArea.add(chatHistory, BorderLayout.EAST);
        add(chatArea, BorderLayout.SOUTH);
    }
    
    public void startConnection() {
        System.out.println("Trying to start client");
        startClient();
        if(socket == null) {
            System.out.println("Client Failed. Starting Server.");
            startServer();
        }
        
        try {
            System.out.println("Creating ObjectOutputStream");
            outstream = new ObjectOutputStream(socket.getOutputStream());
        }
        catch(IOException e) {
            System.out.println(e);
        }
        
        System.out.println("Creating InputStream");
        Thread instreamHandler = new Thread(new InputStreamHandler(socket));;
        instreamHandler.start();
    }
    
    public void startClient() {
        try {
            InetAddress host = InetAddress.getLocalHost();
            socket = new Socket(host.getHostName(), PORT);
            System.out.println("Client connected to server.");
        }
        catch(UnknownHostException e) {
            System.out.println("Unknown host.");
            System.out.println(e);
        }
        catch(IOException e) {
            System.out.println("No server found.");
            System.out.println(e);
        }
        
    }
    
    private void startServer() {
        try {
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("Waiting for client message...");
            socket = server.accept();
            System.out.println("Connected to client.");
        }
        catch(IOException e) {
            System.out.println("Error starting server.");
            System.out.println(e);
        }
    }
    
    
    private class InputStreamHandler implements Runnable {
    private ObjectInputStream instream;
    private Socket socket;
    
    public InputStreamHandler(Socket s) {
        System.out.println("Creating InputStreamHandler");
        socket = s;
    }
    
    public void run() {
        try {
            System.out.println("Starting input stream");
            instream = new ObjectInputStream(socket.getInputStream());
            
            while(true) {
                Object o = instream.readObject();
                if(o instanceof GameMessage) {
                    GameMessage message = (GameMessage) o;
                    if(message.hasState()) {
                        enemyBoardView.setState(message.getRow(), message.getCol(), message.getState());
                    }
                    else {
                        ownBoardView.fireHit(message.getRow(), message.getCol());
                    }
                        
                }
                else if (o instanceof TextMessage) {
                    TextMessage message = (TextMessage) o;
                    chatHistory.addMessage(message.getText());
                }
                Thread.sleep(500);
            }
        }
        catch(IOException | ClassNotFoundException | InterruptedException e) {
            System.out.println(e);
        }
    }
    
}
}
