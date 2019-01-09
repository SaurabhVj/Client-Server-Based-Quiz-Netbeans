/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.Serializable;

public class Message implements Serializable {
    private String message;
   

    public Message(String message) {
        this.message = message;
        
    }
    @Override
    public String toString() {
        return String.format("%s"
                , message);

    }

    
}
