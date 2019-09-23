package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample.fxml"));
			SampleController controller;
			loader.setController(controller = new SampleController());
			AnchorPane root = (AnchorPane)loader.load();
			Scene scene = new Scene(root,600,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
			
		
			scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			      switch(key.getCode()) {
			      case UP: controller.move_up(); break;
			      case DOWN: controller.move_down(); break;
			      case LEFT: controller.move_left(); break;
			      case RIGHT: controller.move_right(); break;
			      default: break;
			      }
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
		
		
	}
}
