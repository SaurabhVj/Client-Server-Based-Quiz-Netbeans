/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Style;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Saurabh
 */
public class CreateQuizController implements Initializable {
    
    @FXML
    public AnchorPane gridPane;
    
    @FXML
    private JFXButton btn_addQuiz;
    @FXML
    private JFXTextField add_subject;
    @FXML
    private JFXTextField add_quiz;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AddQuiz(MouseEvent event) {
        String subject="";
         String name="";
        
       if(add_subject.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), 
            "Form Error!", "Please enter a subject");
            return;
        }
        else if(add_quiz.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), 
            "Form Error!", "Please Enter the quiz name");
            return;
        } 
        else
     {
         try{
             subject=add_subject.getText().toString();
        name=add_quiz.getText().toString();
       Socket socket = new Socket("localhost", 8080);
       String passToServer=subject + ":" + name + ":" + "host";
       DataOutputStream out=new DataOutputStream(socket.getOutputStream());
       out.writeUTF(passToServer);
       DataInputStream in = new DataInputStream(socket.getInputStream());
       String res=in.readUTF().toString();
       System.out.print(res);
       if(res.equals("true"))
        {
            //need to replace this code
        Parent root = FXMLLoader.load(getClass().getResource("AddQuestions.fxml"));
        gridPane.getChildren().removeAll();
        gridPane.getChildren().setAll(root);
        return ;
        
        }
       else{
        
           showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), 
            "Form Error!", "ERROR OCCURED");
            return;
        
        
        }
        
        }   catch (IOException ex) {
                Logger.getLogger(CreateQuizController.class.getName()).log(Level.SEVERE, null, ex);
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
