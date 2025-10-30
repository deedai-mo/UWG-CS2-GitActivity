package edu.westga.cs1302.mvvmsandbox.model;

/**
 * Defines a student
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class Student {
	private static final String NAME_CANNOT_BE_NULL = "name cannot be null.";
	private static final String GRADE_OUT_OF_RANGE = "grade out of range - range 0 to 100, inclusive.";
	private String name = "";
	private int grade = 0;

	/**
	 * Creates a new Student with the specified name and grade.
	 * 
	 * @precondition name!=null AND grade >=0 AND grade <= 100
	 * @postcondition getName() == name AND getGrade() == grade
	 * 
	 * @param name
	 *            name of the student
	 * @param grade
	 *            the students numeric grade (between 0 and 100, inclusive)
	 */
	public Student(String name, int grade) {
		if (name == null) {
			throw new IllegalArgumentException(NAME_CANNOT_BE_NULL);
		}

		if (grade < 0 || grade > 100) {
			throw new IllegalArgumentException(GRADE_OUT_OF_RANGE);
		}

		this.name = name;
		this.grade = grade;
	}

	/**
	 * Returns the Student's grade
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the Student's grade
	 */
	public int getGrade() {
		return this.grade;
	}

	/**
	 * Sets the grade.
	 * 
	 * @precondition grade >=0 AND grade <= 100
	 * @postcondition getGrade() == grade
	 *
	 * @param grade
	 *            the grade to set
	 */
	public void setGrade(int grade) {
		if (grade < 0 || grade > 100) {
			throw new IllegalArgumentException(GRADE_OUT_OF_RANGE);
		}

		this.grade = grade;
	}

	/**
	 * Returns the Student's name
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the Student's name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 *
	 * @precondition name!=null
	 * @postcondition getName() == name
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException(NAME_CANNOT_BE_NULL);
		}

		this.name = name;
	}

	@Override
	public String toString() {
		return this.name + ": " + this.grade;
	}

}
