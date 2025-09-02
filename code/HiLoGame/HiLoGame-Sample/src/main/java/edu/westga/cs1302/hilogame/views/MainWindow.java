package edu.westga.cs1302.hilogame.views;

import java.util.Random;

import edu.westga.cs1302.hilogame.model.HiLoGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/** Manages components and behavior for the MainWindow of the application
 * 
 * @author CS 1302
 * @version Fall 2025
 * 
 */
public class MainWindow {
    @FXML private TextField guess;
    @FXML private TextArea outputArea;
    
    private HiLoGame game;

    @FXML
    void checkGuess(ActionEvent event) {
    	int guess = Integer.parseInt(this.guess.getText());
    	String result = this.game.makeGuess(guess);
    	this.outputArea.setText(result);
    }

    @FXML
    void initialize() {
        assert this.guess != null : "fx:id=\"guess\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.outputArea != null : "fx:id=\"outputArea\" was not injected: check your FXML file 'MainWindow.fxml'.";

        Random randomNumberGenerator = new Random();
        this.game = new HiLoGame(randomNumberGenerator.nextInt());
    }
}
