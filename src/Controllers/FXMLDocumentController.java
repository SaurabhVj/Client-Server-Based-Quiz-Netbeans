/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Window;

/**
 *
 * @author Saurabh
 */
public class FXMLDocumentController implements Initializable {
    
    
    @FXML
    private Pane content_area;
    
    @FXML
    private JFXTextField login_username;
    @FXML
    private JFXTextField login_password;
    @FXML
    private JFXButton btn_login;
    @FXML
    public AnchorPane gridPane;
    
    public String sname ;
    public String spass ;
        

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   

    @FXML
    private void open_registration(javafx.scene.input.MouseEvent event) throws IOException {
    Parent fxml = FXMLLoader.load(getClass().getResource("/Style/registration.fxml"));
    content_area.getChildren().removeAll();
    content_area.getChildren().setAll(fxml);
    
    
    }

    @FXML
    private void LoginStudent(javafx.scene.input.MouseEvent event) throws ClassNotFoundException, IOException {
     
   Window owner = btn_login.getScene().getWindow();      
   if(login_username.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), 
            "Form Error!", "Please enter your name");
            return;
        }
        else if(login_password.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), 
            "Form Error!", "Please enter your password id");
            return;
        } 
        else
     { 
         
         sname = login_username.getText().toString().trim();
         spass = login_password.getText().toString().trim();
          
        
         try{
             
       Socket socket = new Socket("localhost", 8080);
       String cred_login = sname+":"+spass ;
       DataOutputStream out=new DataOutputStream(socket.getOutputStream());
       out.writeUTF(cred_login);
        
        DataInputStream in = new DataInputStream(socket.getInputStream());
        String res=in.readUTF().toString();
        System.out.println(res);
        if(res.equals("true"))
        {
        Parent root = FXMLLoader.load(getClass().getResource("/Style/MainView.fxml"));
        gridPane.getChildren().removeAll();
        gridPane.getChildren().setAll(root);
        return ;
        
        }
        else{
        
           showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), 
            "Form Error!", "INVALID CREDENTIALS");
            return;
        
        
        }

        } catch (IOException e) {
            e.printStackTrace();
        }
         
            
       
       
        
       
       
       
       
       
       }
       


      }  


   
    public void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    @FXML
    private void closeApp(javafx.scene.input.MouseEvent event) {
        
        System.exit(0); 
    }
    
    
    //            Service<Void> service = new Service<Void>()
//                    {
//        @Override
//        protected Task<Void> createTask() {
//           return new Task<Void>()
//           {
//               @Override
//               protected Void call() throws Exception {
//                   
//                   
//                 Socket socket = new Socket("localhost", 8080);
//              String cred_login = sname+":"+spass ;
//             sendMessage(socket , cred_login);
//              DataInputStream in = new DataInputStream(socket.getInputStream());
//                  
//                final CountDownLatch latch = new CountDownLatch(1);
//                Platform.runLater(new Runnable()
//                {
//                    @Override
//                    public void run() {
//                        
//                        try
//                        {
//                        //fx code here
//                            if(in.readUTF().equals("true"))
//                    {
//                   Parent root = FXMLLoader.load(getClass().getResource("/Style/MainView.fxml"));
//                  gridPane.getChildren().removeAll();
//                 gridPane.getChildren().setAll(root);
//                   return ;
//        
//       }
//      else{
//        
//        showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), 
//            "Form Error!", "INVALID CREDENTIALS");
//            return;
//        
//        
//        }
//                            
//                        
//                        }
//                        catch (IOException ex) {
//                            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//                        }                        finally
//                        {
//                        latch.countDown();
//                        
//                        }
//                        
//                        
//                    }
//                
//                
//                
//                }
//                
//                
//                );
//                   
//                latch.await();
//                //keep with the background work ;
//                
//                
//                return null ;
//              }
//           
//           
//           
//           };
//                   
//                   
//        }
//                    
//                    
//                    
//                    
//                    
//                    };
//            
            
            
}
