package edu.westga.cs1302.password_generator.viewmodel.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.password_generator.viewmodel.PasswordGeneratorViewModel;

public class TestPasswordGeneratorViewModel {
	private PasswordGeneratorViewModel viewModel;

    @BeforeEach
    public void setUp() {
        this.viewModel = new PasswordGeneratorViewModel();
    }

    @Test
    public void testInitialization() {
        assertNotNull(this.viewModel.minimumLengthProperty());
        assertEquals("1", this.viewModel.minimumLengthProperty().get());

        assertNotNull(this.viewModel.mustIncludeDigitsProperty());
        assertEquals(false, this.viewModel.mustIncludeDigitsProperty().get());
        
       
    }

    @Test
    public void testGeneratePassword_ValidInput() {
        this.viewModel.minimumLengthProperty().set("5");
        this.viewModel.mustIncludeDigitsProperty().set(true);

        this.viewModel.generatePassword();

        
        String password = this.viewModel.outputProperty().get();
        assertNotNull(password);
        
        assertEquals(true, password.length() >= 5); 
    }
    
    @Test
    public void testGeneratePassword_InvalidLength_ThrowsException() {
       
        this.viewModel.minimumLengthProperty().set("0"); // Invalid per Model's setMinimumLength precondition

        
        assertThrows(IllegalArgumentException.class, () -> {
            this.viewModel.generatePassword();
        });
    }
    
    @Test
    public void testGeneratePassword_NonIntegerLength_ThrowsException() {
      
        this.viewModel.minimumLengthProperty().set("abc");

       
        assertThrows(NumberFormatException.class, () -> {
            this.viewModel.generatePassword();
        });
    }

}
