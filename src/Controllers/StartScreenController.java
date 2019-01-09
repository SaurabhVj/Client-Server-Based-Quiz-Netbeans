/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Saurabh
 */
public class StartScreenController implements Initializable {

    @FXML
    private JFXButton teacher_login;
    @FXML
    private JFXButton login_student;
    @FXML
    private AnchorPane ap;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void LoginStudent(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Style/FXMLDocument.fxml"));
        ap.getChildren().removeAll();
        ap.getChildren().setAll(root);
        return ;
        
        
    }

    @FXML
    private void LoginTeacher(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Style/CreateQuiz.fxml"));
        ap.getChildren().removeAll();
        ap.getChildren().setAll(root);
        return ;   
        
        
    }
    
}
