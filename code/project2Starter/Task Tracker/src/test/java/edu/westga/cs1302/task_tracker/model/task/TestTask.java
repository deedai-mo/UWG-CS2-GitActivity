package edu.westga.cs1302.task_tracker.model.task;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.task_tracker.model.ContainerTask;
import edu.westga.cs1302.task_tracker.model.Task;
import edu.westga.cs1302.task_tracker.model.Task.TaskPriority;

public class TestTask {
	@Test
	void testValidConstruction() {
		Task task = new Task("Test Task", "A description.", TaskPriority.MEDIUM);
		assertEquals("Test Task", task.getName());
		assertEquals("A description.", task.getDescription());
		assertEquals(TaskPriority.MEDIUM, task.getPriority());
	}
	
	@Test
	void testConstructionWithEmptyName() {
		assertThrows(IllegalArgumentException.class, () -> 
			new Task("", "Description", TaskPriority.LOW));
	}
	
	@Test
	void testConstructionWithNullName() {
		assertThrows(IllegalArgumentException.class, () -> 
			new Task(null, "Description", TaskPriority.LOW));
	}
	
	@Test
	void testConstructionWithNullDescription() {
		assertThrows(IllegalArgumentException.class, () -> 
			new Task("Name", null, TaskPriority.LOW));
	}
	
	@Test
	void testConstructionWithNullPriority() {
		assertThrows(IllegalArgumentException.class, () -> 
			new Task("Name", "Description", null));
	}
	
	@Test
	void testSetDescriptionValid() {
		Task task = new Task("Name", "Old", TaskPriority.HIGH);
		task.setDescription("New Description");
		assertEquals("New Description", task.getDescription());
	}
	
	@Test
	void testSetDescriptionNull() {
		Task task = new Task("Name", "Old", TaskPriority.HIGH);
		assertThrows(IllegalArgumentException.class, () -> task.setDescription(null));
	}

	@Test
	void testGetSubTasksReturnsEmptyList() {
		Task task = new Task("Name", "Description", TaskPriority.LOW);
		assertTrue(task.getSubTasks().isEmpty());
		task.getSubTasks().add(new Task("Extra", "", TaskPriority.LOW));
		assertTrue(task.getSubTasks().isEmpty());
	}


	@Test
	void testAddTaskConvertsToContainerTask() {
		Task originalTask = new Task("Original", "Desc", TaskPriority.HIGH);
		Task subtask = new Task("Sub", "SubDesc", TaskPriority.LOW);
		Task result = originalTask.addTask(subtask);
		assertTrue(result instanceof ContainerTask);
		assertEquals("Original", result.getName());
		assertEquals("Desc", result.getDescription());
		assertEquals(TaskPriority.HIGH, result.getPriority());
		assertEquals(1, result.getSubTasks().size());
		assertEquals(subtask, result.getSubTasks().get(0));
	}
	
	@Test
	void testAddTaskWithNullSubtask() {
		Task originalTask = new Task("Original", "Desc", TaskPriority.HIGH);
		assertThrows(IllegalArgumentException.class, () -> originalTask.addTask(null));
	}
	
	/**
	 * Test the Leaf's removeTask method, which should always return false 
	 * as a simple Task cannot contain subtasks.
	 */
	@Test
	void testRemoveTaskReturnsFalse() {
		Task originalTask = new Task("Original", "Desc", TaskPriority.HIGH);
		Task subtask = new Task("Sub", "SubDesc", TaskPriority.LOW);
		
		assertFalse(originalTask.removeTask(subtask));
	}

}
