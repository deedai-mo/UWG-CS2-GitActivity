package edu.westga.cs1302.password_generator.viewmodel;
import java.util.Random;

import edu.westga.cs1302.password_generator.model.PasswordGenerator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * ViewModel for the Password Generator application.
 * It mediates between the MainWindow View and the PasswordGenerator Model.
 *  @author CS 1302 
 * @version Fall 2024
 */

public class PasswordGeneratorViewModel {
	private  PasswordGenerator generator;
	private  StringProperty minimumLengthProperty;
	private  BooleanProperty mustIncludeDigitsProperty;
	private  BooleanProperty mustIncludeLowerCaseLettersProperty;
	private  BooleanProperty mustIncludeUpperCaseLettersProperty;
	private  StringProperty outputProperty;
	
	/**
	 * Creates a new view model, initializing the model and properties.
	 * @precondition none
	 * @postcondition All properties are initialized to default states and the
	 * PasswordGenerator is created.
	 */
	
	public PasswordGeneratorViewModel() {
		Random randomNumberGenerator = new Random();
        this.generator = new PasswordGenerator(randomNumberGenerator.nextLong());
	
		this.minimumLengthProperty = new SimpleStringProperty("1"); 
		this.mustIncludeDigitsProperty = new SimpleBooleanProperty(this.generator.getMustHaveAtLeastOneDigit());
		this.mustIncludeLowerCaseLettersProperty = new SimpleBooleanProperty(this.generator.getMustHaveAtLeastOneLowerCaseLetter());
		this.mustIncludeUpperCaseLettersProperty = new SimpleBooleanProperty(this.generator.getMustHaveAtLeastOneUpperCaseLetter());
		this.outputProperty = new SimpleStringProperty("");
		
	}
	/**
	 * Gets the StringProperty for the minimum password length.
	 * @return the minimum length property
	 */
	
	public StringProperty minimumLengthProperty() {
		return this.minimumLengthProperty;
	}

	/**
	 * Gets the BooleanProperty for the 'must include digits' requirement.
	 * @return the digits requirement property
	 */
	
	public BooleanProperty mustIncludeDigitsProperty() {
		return this.mustIncludeDigitsProperty;
	}

	/**
	 * Gets the BooleanProperty for the 'must include lower case letters' requirement.
	 * @return the lower case letters requirement property
	 */
	
	public BooleanProperty mustIncludeLowerCaseLettersProperty() {
		return this.mustIncludeLowerCaseLettersProperty;
	}

	/**
	 * Gets the BooleanProperty for the 'must include upper case letters' requirement.
	 * @return the upper case letters requirement property
	 */
	
	public BooleanProperty mustIncludeUpperCaseLettersProperty() {
		return this.mustIncludeUpperCaseLettersProperty;
	}

	/**
	 * Gets the StringProperty for the output (generated password).
	 * @return the output password property
	 */
	
	public StringProperty outputProperty() {
		return this.outputProperty;
	}

	/**
	 * Generates a password using the current settings, updates the model, 
	 * and sets the output property.
	 * * @precondition The minimum length text must be a valid integer >= 1.
	 * @postcondition The outputProperty contains the newly generated password.
	 * * @throws NumberFormatException if minimum length is not a valid integer.
	 * @throws IllegalArgumentException if minimum length is < 1.
	 */
	public void generatePassword() {
		int minimumLength = Integer.parseInt(this.minimumLengthProperty.get());
		this.generator.setMinimumLength(minimumLength);
		this.generator.setMustHaveAtLeastOneDigit(this.mustIncludeDigitsProperty.get());
		this.generator.setMustHaveAtLeastOneLowerCaseLetter(this.mustIncludeLowerCaseLettersProperty.get());
		this.generator.setMustHaveAtLeastOneUpperCaseLetter(this.mustIncludeUpperCaseLettersProperty.get());
		
		String password = this.generator.generatePassword();
		this.outputProperty.set(password);
	}

}
