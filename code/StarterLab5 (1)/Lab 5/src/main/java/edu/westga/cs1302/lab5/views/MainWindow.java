package edu.westga.cs1302.lab5.views;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.westga.cs1302.lab5.model.GradeCalculator;
import edu.westga.cs1302.lab5.model.Student;
import edu.westga.cs1302.lab5.persistence.StudentDataPersistenceManager;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * Controller class for drawing various things to our canvas window.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindow {
	@FXML private TextField avgGrade;
	@FXML private TextField grade;
	@FXML private TextField name;
	@FXML private ListView<Student> students;

	@FXML
	void addStudent(ActionEvent event) {
		try {
			String name = this.name.getText();
			int grade = Integer.parseInt(this.grade.getText());
			Student student = new Student(name, grade);
			this.students.getItems().add(student);
			this.avgGrade.setText(Double.toString(GradeCalculator.calculateAverageGrade(this.students.getItems())));
		} catch (NumberFormatException error) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Invalid grade provided, must be an integer value. Please reenter grade.");
			alert.showAndWait();
		} catch (IllegalArgumentException error) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Unable to generate Student: " + error.getMessage() + ". Please reenter Student information.");
			alert.showAndWait();
		}
	}

	@FXML
	void loadStudents(ActionEvent event) {
		//this.loadStudents();
		this.performLoad();
	}
	
	 private void performLoad() {
	        try {
	            Student[] loadedStudents = StudentDataPersistenceManager.loadStudentData();
	            
	            // 1. Clear and add new data
	            this.students.getItems().clear();
	            this.students.getItems().addAll(loadedStudents);
	            
	            // 2. FIX: Update the average grade after successfully loading data
	            this.updateAverageGrade(); 

	        } catch (FileNotFoundException error) {
	             // It's acceptable to just clear the list and update the average if the file doesn't exist
	             this.students.getItems().clear();
	             this.updateAverageGrade();
	        } catch (IOException error) {
	            Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setContentText(error.getMessage());
	            alert.showAndWait();
	        }
	    }

//	private void loadStudents() {
//		try {
//			Student[] students = StudentDataPersistenceManager.loadStudentData();
//			this.students.getItems().clear();
//			this.students.getItems().addAll(students);
//		} catch (IOException error) {
//			Alert alert = new Alert(Alert.AlertType.ERROR);
//			alert.setContentText(error.getMessage());
//			alert.showAndWait();
//		}
//	}

	@FXML
	void saveStudents(ActionEvent event) {
		try {
			StudentDataPersistenceManager.saveStudentData(this.students.getItems().toArray(new Student[0]));
		} catch (Exception error) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText(error.getMessage());
			alert.showAndWait();
		}
	}
	
    private void updateAverageGrade() {
        ObservableList<Student> studentList = this.students.getItems();
        
        // Check for empty list (GradeCalculator handles this, but it's cleaner here)
        if (studentList.isEmpty()) {
            this.avgGrade.setText("");
        } else {
            double average = GradeCalculator.calculateAverageGrade(studentList);
            // Format to 2 decimal places for a clean display
            this.avgGrade.setText(String.format("%.2f", average)); 
        }
    }

	/**
	 * Perform any needed initialization of UI components and underlying objects.
	 */
	public void initialize() {
		//this.loadStudents();
		this.performLoad();
		
	}
}