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

/**
 *
 * @author rachelpark
 */
public class ChatBox extends JPanel {
    private ObjectOutputStream outstream;
    private JButton sendButton;
    private JTextField textField;
    
    public ChatBox(ObjectOutputStream ostream) {
        setLayout(new BorderLayout());
        
        textField = new JTextField(30);
        add(textField, BorderLayout.WEST);
        
        outstream = ostream;
        
        sendButton = new JButton("Send");
        sendButton.setOpaque(true);
        sendButton.setPreferredSize(new Dimension(50, 60));
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    outstream.writeObject(new TextMessage(sendMessage()));
                }
                catch(IOException ex) {
                    System.out.println(ex);
                }
            }
        });
        add(sendButton, BorderLayout.EAST);
        
    }

    
    public String sendMessage() {
        String message = textField.getText();
        textField.setText("");
        return message;
    }
   
    
}
