package edu.westga.cs1302.password_generator.view;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import edu.westga.cs1302.password_generator.viewmodel.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/** Code behind for the MainWindow of the Application.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindow {

    @FXML private CheckBox mustIncludeDigits;
    @FXML private CheckBox mustIncludeLowerCaseLetters;
    @FXML private CheckBox mustIncludeUpperCaseLetters;
    @FXML private TextField minimumLength;
    @FXML private Label errorTextLabel;
    @FXML private Label minLengthErrorText;
    @FXML private Button generatePasswordButton;
    @FXML private ListView<String> passwordHistory;
    @FXML private MenuItem saveMenuItem;
	@FXML private MenuItem aboutMenuItem;
	@FXML private MenuItem closeMenuItem;
    
    
    private ViewModel vm;
    
    @FXML
    void initialize() {
    	this.vm = new ViewModel();
    	this.vm.getRequireDigits().bind(this.mustIncludeDigits.selectedProperty());
    	this.vm.getRequireLowercase().bind(this.mustIncludeLowerCaseLetters.selectedProperty());
    	this.vm.getRequireUppercase().bind(this.mustIncludeUpperCaseLetters.selectedProperty());
    	this.minimumLength.setText(this.vm.getMinimumLength().getValue());
    	this.vm.getMinimumLength().bind(this.minimumLength.textProperty());
    	
    	this.errorTextLabel.textProperty().bind(this.vm.getErrorText());
    	this.passwordHistory.setItems(this.vm.getPasswordHistory());
    	
    	this.generatePasswordButton.disableProperty().bind(this.vm.getIsInputValid());
    	this.minimumLength.textProperty().addListener((observable, newValue, oldValue) -> {
    		boolean isValid = newValue.matches("\\d+") && (newValue.isEmpty() || Integer.parseInt(newValue) > 0);
    		this.minLengthErrorText.setVisible(!isValid);
    	});
    	
    	this.generatePasswordButton.setOnAction(
    			(event) -> { 
    				this.vm.generatePassword();
    			} 
    	);
    }
    /** Handles the Save menu item action. Opens a FileChooser and saves the history.
     * * @param event the event that triggered the method (ignored)
     */
    
    @FXML
    private void handleSave() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Password History");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        
        // Use any control to get the stage
        Stage stage = (Stage)
        		((Node) this.generatePasswordButton).getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try (PrintWriter writer = new PrintWriter(file)) {
                for (String password : this.vm.getPasswordHistory()) {
                    writer.println(password);
                }
            } catch (IOException e) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Save Error");
                errorAlert.setHeaderText("Could not save file");
                errorAlert.setContentText("An error occurred while writing to the file: " + e.getMessage());
                errorAlert.showAndWait();
            }
        }
    }

    /** Handles the About menu item action. Displays an informational alert.
     * * @param event the event that triggered the method (ignored)
     */
    @FXML
    private void handleAbout() {
        Alert aboutAlert = new Alert(Alert.AlertType.INFORMATION);
        aboutAlert.setTitle("About Password Generator");
        aboutAlert.setHeaderText("Password Generator Project");
        aboutAlert.setContentText("PassWord Generator");
        aboutAlert.showAndWait();
    }
    
    /** Handles the Close menu item action. Exits the application.
     * * @param event the event that triggered the method (ignored)
     */
    @FXML
    private void handleClose() {
        ((Node) this.generatePasswordButton).getScene().getWindow().hide();
    }
}
