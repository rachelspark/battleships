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
public class ChatHistory extends JPanel {
    private JTextArea textArea;
    
    public ChatHistory() {
        
        textArea = new JTextArea(10, 30);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane);
    }
    
    public void addMessage(String message) {
        textArea.append(message + "\n");
    }
    
}
