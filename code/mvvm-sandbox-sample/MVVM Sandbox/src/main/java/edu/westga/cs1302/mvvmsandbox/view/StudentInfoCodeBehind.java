package edu.westga.cs1302.mvvmsandbox.view;

import edu.westga.cs1302.mvvmsandbox.viewmodel.StudentInfoViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

/**
 * The Class StudentInfoCodeBehind.
 * 
 * @author CS1302
 * @version Fall 2025
 */
public class StudentInfoCodeBehind {

	@FXML
	private TextField nameTextField;

	@FXML
	private TextField gradeTextField;

	@FXML
	private TextArea summaryTextField;
	
	private StudentInfoViewModel viewModel;

	/**
	 * Instantiates a new student info code behind.
	 * 
	 * @precondition none
	 * @precondition none
	 */
	public StudentInfoCodeBehind() {
		this.viewModel = new StudentInfoViewModel();
	}

	@FXML
	private void initialize() {
		this.nameTextField.setText("");
		this.summaryTextField.setEditable(false);
		this.bindControlsToViewModel();
	}

	private void bindControlsToViewModel() {
		this.nameTextField.textProperty().bindBidirectional(this.viewModel.nameProperty());
		this.gradeTextField.textProperty().bindBidirectional(this.viewModel.gradeProperty(), new NumberStringConverter());
		this.summaryTextField.textProperty().bindBidirectional(this.viewModel.summaryInfoProperty());
	}

	@FXML
	private void handleSimulateModelViewDataChange() {
		this.viewModel.simulateDataChange();
	}

	@FXML
	private void handleUpdateStudent() {
		this.viewModel.updateStudent();
	}

}
