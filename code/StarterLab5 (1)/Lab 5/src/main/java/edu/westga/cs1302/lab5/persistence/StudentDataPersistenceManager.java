package edu.westga.cs1302.lab5.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.westga.cs1302.lab5.model.Student;

/** Supports saving and loading student data,
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class StudentDataPersistenceManager {
	
	public static final String FILE_LOCATION = "data.txt";
	private static final String SEPARATOR = ",";
	
	/** Save the students!
	 * 
	 * @precondition students != null
	 * @postcondition none
	 * 
	 * @param students the set of students to save
	 * @throws IllegalArgumentException if precondition is violated
	 * @throws IOException Unable to write to FILE_LOCATION
	 */
	public static void saveStudentData(Student[] students) throws IOException, IllegalArgumentException {
		if (students == null) {
			throw new IllegalArgumentException("must provide an array of students");
		}
		try (FileWriter writer = new FileWriter(StudentDataPersistenceManager.FILE_LOCATION)) {
			for (Student currStudent : students) {
				String csvLine = currStudent.getName() + SEPARATOR + currStudent.getGrade() + System.lineSeparator();
				writer.write(csvLine);
			}
		}
	}

	/** Load the students!
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the set of students loaded
	 * @throws FileNotFoundException no file exists at FILE_LOCATION
	 * @throws IOException unable to read file due to formatting issue 
	 */
	public static Student[] loadStudentData() throws FileNotFoundException, IOException {
		ArrayList<Student> students = new ArrayList<Student>();
		File inputFile = new File(StudentDataPersistenceManager.FILE_LOCATION);
		
		int lineNumber = 0;
		try (Scanner reader = new Scanner(inputFile)) {
			while (reader.hasNextLine()) {
                lineNumber++;
				String line = reader.nextLine();
				String[] parts = line.split(SEPARATOR);
				
				if (parts.length != 2) {
					// FIX: Updated message to reflect the correct separator used here
					throw new IOException("File format error on line " + lineNumber + ": Expected format is 'name,grade'.");
				}
                
                String name = parts[0].trim();
                int grade;

				try {
					grade = Integer.parseInt(parts[1].trim());
				} catch (NumberFormatException error) {
					throw new IOException("File format error on line " + lineNumber + ": Grade value was not formatted as an integer.");
				}
				students.add(new Student(name, grade));
			}
		} catch (IllegalArgumentException error) {
			throw new IOException("Data validation error on line " + lineNumber + ": " + error.getMessage());
		}
		
		return students.toArray(new Student[0]);
	
   }
}
