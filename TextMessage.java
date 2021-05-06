/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleships_automated;

/**
 *
 * @author rachelpark
 */
public class TextMessage extends Message {
    private String message; 
    
    public TextMessage(String m) {
        message = m;
    }
    
    public void setMessage(String m) {
        message = m;
    }
    
    public String getText() {
        return message;
    }
}
