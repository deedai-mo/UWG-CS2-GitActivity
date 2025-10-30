package edu.westga.cs1302.mvvmsandbox.viewmodel;

import edu.westga.cs1302.mvvmsandbox.model.Student;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The Class StudentInfoCodeBehind.
 * 
 * @author CS1302
 * @version Fall 2025
 */
public class StudentInfoViewModel {
	private Student student;

	private StringProperty nameProperty;
	private IntegerProperty gradeProperty;
	private StringProperty summaryInfoProperty;

	/**
	 * Instantiates a new student info view model.
	 */
	public StudentInfoViewModel() {
		this.student = new Student("", 0);
		this.nameProperty = new SimpleStringProperty();
		this.gradeProperty = new SimpleIntegerProperty();
		this.summaryInfoProperty = new SimpleStringProperty();
	}

	/**
	 * Gets the name property.
	 *
	 * @return the nameProperty
	 */
	public StringProperty nameProperty() {
		return this.nameProperty;
	}

	/**
	 * Gets the grade property.
	 *
	 * @return the gradeProperty
	 */
	public IntegerProperty gradeProperty() {
		return this.gradeProperty;
	}

	/**
	 * Gets the name property.
	 *
	 * @return the nameProperty
	 */
	public StringProperty summaryInfoProperty() {
		return this.summaryInfoProperty;
	}

	/**
	 * Update student with data that user entered in the UI.
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public void updateStudent() {
		this.student.setName(this.nameProperty.getValue());
		this.student.setGrade(this.gradeProperty.getValue());

		String summaryOutput = "Student info: " + System.lineSeparator();
		summaryOutput += "Name: " + this.student.getName() + System.lineSeparator();
		summaryOutput += "Grade: " + this.student.getGrade() + System.lineSeparator();

		this.summaryInfoProperty.set(summaryOutput);
	}

	/**
	 * Simulates a data change occurring in the view model that is automatically
	 * updated in the UI due to the property bindings. Purely for demo purposes to
	 * see how property bindings work.
	 */
	public void simulateDataChange() {
		int grade = this.student.getGrade();
		grade += 5;
		this.student.setGrade(grade);

		this.gradeProperty.set(this.student.getGrade());
	}

}
