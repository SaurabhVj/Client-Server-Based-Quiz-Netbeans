/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import java.net.*; 
import java.io.*; 
import javafx.scene.control.Alert;
import javafx.stage.Window;
  
public class Client 
{ 
    // initialize socket and input output streams 
    public Socket socket            = null; 
    public DataInputStream  input   = null; 
    public DataOutputStream out     = null; 
    
  
    // constructor to put ip address and port 
    
    public Client(String address, int port,String username,String password) 
    { 
        // establish a connection 
        try
        { 
            socket = new Socket(address, port); 
            //System.out.println("Connected"); 
  
            // takes input from terminal 
            input  = new DataInputStream(System.in); 
  
            // sends output to the socket 
            out    = new DataOutputStream(socket.getOutputStream()); 
            String line =username+":"+password; 
            out.writeUTF(line);
        } 
        catch(UnknownHostException u) 
        { 
            FXMLDocumentController obj = new FXMLDocumentController();
            
           
            showAlert(Alert.AlertType.ERROR, obj.gridPane.getScene().getWindow(), 
                        "Form Error!", "Error 503 Bad Internal Server"); 
            // System.out.println(u); 
        } 
        catch(IOException i) 
        { 
           // System.out.println(i); 
        } 
  
        // string to read message from input 
        
        
        // keep reading until "Over" is input 
        
  
        // close the connection 
        
    } 
    
    
    
    public void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
    
  
    
} 