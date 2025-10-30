package edu.westga.cs1302.mvvmsandbox;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Entry point for application.
 * 
 * @author CS1302
 * @version Fall 2025
 */
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("view/StudentInfoGui.fxml"));
			Pane pane = loader.load();
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("MVVM introduction");
			primaryStage.show();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	/**
	 * Entry point
	 * 
	 * @precondition none
	 * @postcondition none
	 * @param args
	 *            Not used
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
