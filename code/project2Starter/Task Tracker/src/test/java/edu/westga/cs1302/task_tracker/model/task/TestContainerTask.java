package edu.westga.cs1302.task_tracker.model.task;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.task_tracker.model.ContainerTask;
import edu.westga.cs1302.task_tracker.model.Task;
import edu.westga.cs1302.task_tracker.model.Task.TaskPriority;

class TestContainerTask {

	private Task subtask1;
	private Task subtask2;
	private Task container;
	
	@BeforeEach
	void setup() {
		this.subtask1 = new Task("Sub1", "D1", TaskPriority.LOW);
		this.subtask2 = new Task("Sub2", "D2", TaskPriority.MEDIUM);
		// Container constructor takes the details of the original task and the first subtask
		this.container = new ContainerTask("Main Task", "Container Description", TaskPriority.HIGH, this.subtask1);
	}
	
	@Test
	void testValidConstruction() {
		assertEquals("Main Task", this.container.getName());
		assertEquals(TaskPriority.HIGH, this.container.getPriority());
		assertEquals(1, this.container.getSubTasks().size());
		assertEquals(this.subtask1, this.container.getSubTasks().get(0));
	}
	
	@Test
	void testConstructionWithNullFirstSubtask() {
		Task nullContainer = new ContainerTask("Null Main", "Desc", TaskPriority.LOW, null);
		assertEquals(0, nullContainer.getSubTasks().size());
	}
	
	@Test
	void testAddTaskSuccessful() {
		Task result = this.container.addTask(this.subtask2);
		assertSame(this.container, result); 
		assertEquals(2, this.container.getSubTasks().size());
		assertTrue(this.container.getSubTasks().contains(this.subtask2));
	}
	
	@Test
	void testAddTaskWithNullTask() {
		assertThrows(IllegalArgumentException.class, () -> this.container.addTask(null));
	}
	
	
	@Test
	void testRemoveTaskSuccessful() {
		// Arrange: Two subtasks (subtask1 from setup, subtask2 added here)
		this.container.addTask(this.subtask2);
		assertEquals(2, this.container.getSubTasks().size());
		
		// Act
		boolean removed = this.container.removeTask(this.subtask1);
		
		// Assert
		assertTrue(removed);
		assertEquals(1, this.container.getSubTasks().size());
		assertFalse(this.container.getSubTasks().contains(this.subtask1));
		assertTrue(this.container.getSubTasks().contains(this.subtask2));
	}
	
	@Test
	void testRemoveNonExistentTask() {
		Task thirdTask = new Task("NonExistent", "D3", TaskPriority.LOW);
		
		// Should return false and list size should remain 1
		assertFalse(this.container.removeTask(thirdTask));
		assertEquals(1, this.container.getSubTasks().size());
	}
	
	// --- Get Subtasks Tests ---
	
	@Test
	void testGetSubTasksReturnsDefensiveCopy() {
		// Arrange: Get the list
		int originalSize = this.container.getSubTasks().size();
		List<Task> subList = this.container.getSubTasks();
		
		// Act: Modify the returned list
		subList.add(this.subtask2);
		
		// Assert: The internal list size should not have changed
		assertEquals(originalSize, this.container.getSubTasks().size());
		// The returned list should have changed
		assertEquals(originalSize + 1, subList.size());
	}
	
	// --- ToString Test ---
	
	@Test
	void testToStringIncludesIndicator() {
		assertTrue(this.container.toString().contains("(+)"));
	}

}
