/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import application.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.Connection;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Saurabh
 */
public class RegistrationController implements Initializable {
    @FXML
    private JFXTextField user_name;

    @FXML
    private JFXTextField user_email;

    @FXML
    private JFXPasswordField user_pass;

    @FXML
    private JFXPasswordField user_cnfpass;

    @FXML
    private JFXButton register_btn;

    @FXML
    private Pane gridPane;
    
    public String sname , semail , spass;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void back_to_menu(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Style/FXMLDocument.fxml"));
       Main.primaryStageObj.getScene().setRoot(root);
        
    }

    @FXML
    private void RegisterStudent(MouseEvent event) throws IOException {
        Window owner = register_btn.getScene().getWindow();
        if(user_name.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), 
            "Form Error!", "Please enter your name");
            return;
        }
        else if(user_email.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), 
            "Form Error!", "Please enter your email id");
            return;
        }
        else if(user_pass.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), 
            "Form Error!", "Please enter a password");
            return;
        }
        else if(!user_pass.getText().toString().equals(user_cnfpass.getText().toString())){
            showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), 
            "Form Error!", "Password missmatch");
            return;
}
        else{

        
            sname = user_name.getText().toString().trim();
            semail = user_email.getText().toString().trim();
            spass = user_pass.getText().toString().trim();
            
            
            try{
            Socket socket = new Socket("localhost" , 8080);
            String reg_cred = sname+":"+semail+":"+spass;
            
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(reg_cred);
            DataInputStream Ios = new DataInputStream(socket.getInputStream());
            String res = Ios.readUTF().toString();
            System.out.println(res);
            if(res.equals("true"))
                
            {
            showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), 
                "Registration Successful!", "Welcome " + user_name.getText());
                 back_to_menu(event);
            
            
            }
            }
            catch (IOException e) {
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


    }




    

