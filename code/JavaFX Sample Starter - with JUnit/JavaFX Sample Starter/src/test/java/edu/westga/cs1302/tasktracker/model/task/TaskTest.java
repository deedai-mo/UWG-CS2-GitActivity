package edu.westga.cs1302.tasktracker.model.task;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.tasktracker.model.Task;

class TaskTest {
	@Test
	public void testGetters() {
		Task task1 = new Task("Buy milk", "2 Liters", "High");
		assertEquals("Buy milk",task1.getName());
		assertEquals("2 Liters",task1.getDescription());
		assertEquals("High",task1.getPriority());
		
	}
	
	@Test
	public void testChangeDescription() {
		Task task1 = new Task("A","","Low");
		task1.setDescription("New description");
		assertEquals("New description",task1.getDescription());
		
	}
	
	public void testInvalidName() {
		assertThrows(IllegalArgumentException.class, ()-> new Task(null,"2 Liters","High"));
		assertThrows(IllegalArgumentException.class, ()-> new Task(" ","2 Liters","High"));
	}
	
	public void testNullPriority() {
		assertThrows(IllegalArgumentException.class, ()-> new Task("Buy milk","2 Liters",null));
	}


}
