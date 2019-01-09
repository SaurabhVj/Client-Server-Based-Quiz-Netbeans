/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Style;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Saurabh
 */
public class AddQuestionsController implements Initializable {
    private ToggleGroup tgGroup;
    @FXML
    private Text qNo;
    @FXML
    private JFXRadioButton b1;
    @FXML
    private JFXRadioButton b4;
    @FXML
    private JFXRadioButton b3;
    @FXML
    private JFXRadioButton b2;
    @FXML
    private JFXButton bbnxt;
    @FXML
    private JFXButton sumbmit;
    @FXML
    private TextField opt1;
    @FXML
    private TextField opt2;
    @FXML
    private TextField opt3;
    @FXML
    private TextField opt4;
    @FXML
    private TextArea que;
    public AnchorPane gridPane;
    
    String queno ;
    int num ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tgGroup = new ToggleGroup();
			b1.setToggleGroup(tgGroup);
			b2.setToggleGroup(tgGroup);
			b3.setToggleGroup(tgGroup);
			b4.setToggleGroup(tgGroup);
                        
                        if(qNo.getText()==null)
                        {
                        queno = "1";
                        qNo.setText(queno);
                        
                        }
                        
                        else
                        
             {
                   int num = Integer.parseInt(qNo.getText());
                    num++;
                   queno = num+"";
                  qNo.setText(queno);
                        
                        
                        
                        
              }
                        
                        
        
    }    

   
    @FXML
    private void addQuestion(MouseEvent event) {
        
       int num1 = Integer.parseInt(qNo.getText());
       num1++;
       
        
        
        String question=que.getText();
        String option1=opt1.getText();
        String option2=opt2.getText();
        String option3=opt3.getText();
        String option4=opt4.getText();
        String answer=null;
        if(b1.isSelected())
            answer=option1;
        else if(b2.isSelected())
            answer=option2;
         else if(b3.isSelected())
            answer=option3;
         else if(b4.isSelected())
            answer=option4;
        
        try{
            Socket socket = new Socket("localhost", 8080);
       String add = queno+":"+question+":"+option1+":"+option2+":"+option3+":"+option4+":"+answer+":"+"add";
       DataOutputStream out=new DataOutputStream(socket.getOutputStream());
       out.writeUTF(add);
       
       DataInputStream in = new DataInputStream(socket.getInputStream());
        String res=in.readUTF().toString();
        System.out.println(res);
        if(res.equals("true")){
            qNo.setText(num1+"");
            que.setText("");
            opt1.setText("");
            opt2.setText("");
            opt3.setText("");
            opt4.setText("");
            showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), 
            "Form Error!", "Question added");
        }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
    }

    

    @FXML
    private void Completed(MouseEvent event) throws IOException {
        //replace this code
        Parent root = FXMLLoader.load(getClass().getResource("/Style/MainView.fxml"));
        gridPane.getChildren().removeAll();
        gridPane.getChildren().setAll(root);
        
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
