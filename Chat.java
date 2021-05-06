/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleships;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author rachelpark
 */
public class Chat {
    private static final int HIT = 2;
    private static final int MISS = 1;
    private static final int UNKNOWN = 0;
    
    private Socket socket;
    private int port = 4321;
    private ChatBox chatBox;
    private ChatHistory chatHistory;
    
    
    public Chat(ChatBox box, ChatHistory history) {
        chatBox = box;
        chatHistory = history;
    }
    
    public void start() {
        System.out.println("Trying to start client");
        startClient();
        if(socket == null) {
            System.out.println("Client Failed. Starting Server.");
            startServer();
        }
        System.out.println("Creating Chat Input and OutputStream");
        Thread instreamHandler = new Thread(new ChatInputStreamHandler(socket, chatHistory));
        Thread outstreamHandler = new Thread(new ChatOutputStreamHandler(socket,chatBox, chatHistory));
        instreamHandler.start();
        outstreamHandler.start();
    }
    
    private void startClient() {
        try {
            InetAddress host = InetAddress.getLocalHost();
            socket = new Socket(host.getHostName(), port);
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
            ServerSocket server = new ServerSocket(port);
            System.out.println("Waiting for client message...");
            socket = server.accept();
            System.out.println("Connected to client.");
        }
        catch(IOException e) {
            System.out.println("Error starting server.");
            System.out.println(e);
        }
    }
    
    private class ChatOutputStreamHandler implements Runnable {
        private ObjectOutputStream outstream;
        private Socket socket;
        private ChatBox chatBox;
        private ChatHistory chatHistory;
        
        public ChatOutputStreamHandler(Socket s, ChatBox box, ChatHistory history) {
            System.out.println("Creating OutputStreamHandler");
            socket = s;
            chatBox = box;
            chatHistory = history;
        }
        
        public void run() {
            try {
                System.out.println("Starting output streams");
                outstream = new ObjectOutputStream(socket.getOutputStream());
                
                while(true) {
                    if(chatBox.getSendButtonClicked()) {
                        String message = chatBox.sendMessage();
                        chatHistory.addMessage(message);
                        outstream.writeObject(message);
                        chatBox.setSendButtonClicked(false);
                    }
                    Thread.sleep(500);
                }
            }
            catch (IOException e) {
                System.out.println(e);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            
        }
        
    }
    
    private class ChatInputStreamHandler implements Runnable {
        private ObjectInputStream instream;
        private Socket socket;
        private ChatHistory chatHistory;
        
        public ChatInputStreamHandler(Socket s, ChatHistory history) {
            System.out.println("Creating InputStreamHandler");
            socket = s;
            chatHistory = history;
        }
        
        public void run() {
            try{
                System.out.println("Starting input streams");
                instream = new ObjectInputStream(socket.getInputStream());
                
                while(true) {
                    String message = (String) instream.readObject();
                    if(message.length() >= 1) {
                        chatHistory.addMessage(message);
                    }
                    Thread.sleep(500);
                }
            }
            catch(IOException e) {
                System.out.println(e);
            }catch (ClassNotFoundException e) {
                System.out.println(e);
            } catch (InterruptedException ex) {
                Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
                
                
    }
    
}
