package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author GENISYS-X
 */
public class Main extends Application {
	
	public static Stage primaryStageObj;
	
	@Override
	public void start(Stage primaryStage) {
		try {
		   
			
			Parent root = FXMLLoader.load(getClass().getResource("/SplashScreen/SplashUI.fxml"));			
			Scene scene = new Scene(root);
		    primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
                        this.primaryStageObj = primaryStage;
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
		launch(args);
	}

    /**
     *
     * @return
     */
    public static Stage getPrimaryStage() {
	        return primaryStageObj;
	 }

}
