/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleships;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.Scanner;
/**
 *
 * @author rachelpark
 */
public class ChatBox extends JPanel {
    private JButton sendButton;
    private JTextField textField;
    private boolean sendButtonClicked;
    
    public ChatBox() {
        setLayout(new BorderLayout());
        
        textField = new JTextField(30);
        add(textField, BorderLayout.WEST);
        
        sendButtonClicked = false;
        
        sendButton = new JButton("Send");
        sendButton.setOpaque(true);
        sendButton.setPreferredSize(new Dimension(50, 60));
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendButtonClicked = true;
            }
        });
        add(sendButton, BorderLayout.EAST);
        
    }
    
    public void setSendButtonClicked(boolean s) {
        sendButtonClicked = s;
    }
    
    public boolean getSendButtonClicked() {
        return sendButtonClicked;
    }
    
    public String sendMessage() {
        String message = textField.getText();
        textField.setText("");
        return message;
    }
   
    
}
