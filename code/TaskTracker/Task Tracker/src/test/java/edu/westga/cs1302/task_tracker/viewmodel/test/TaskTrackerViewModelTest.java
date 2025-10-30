package edu.westga.cs1302.task_tracker.viewmodel.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import edu.westga.cs1302.tas_tracke.viewmodel.TaskTrackerViewModel;
import edu.westga.cs1302.task_tracker.model.NameAscending;
import edu.westga.cs1302.task_tracker.model.Task;
import edu.westga.cs1302.task_tracker.model.Task.TaskPriority;


class TaskTrackerViewModelTest {
	private TaskTrackerViewModel viewModel;
	private Task testTask1;
	private Task testTask2;

	@BeforeEach
	void setUp() {
		this.viewModel = new TaskTrackerViewModel();
		this.testTask1 = new Task("Z Test Task", "Description 1", TaskPriority.LOW);
		this.testTask2 = new Task("A Test Task", "Description 2", TaskPriority.HIGH);
		
		// Add tasks in reverse order of expected default sort (Priority HIGH -> LOW)
		this.viewModel.getTaskList().add(this.testTask1);
		this.viewModel.getTaskList().add(this.testTask2);
	}
	
	// --- Requirement 1A Tests: Re-Sort on Add/Update ---
	
	@Test
	void testAddTaskShouldSortImmediatelyByPriorityDefault() {
		// Default comparator is PriorityAscending (HIGH -> LOW)
		this.viewModel.addTask("B Test Task", "New Description", TaskPriority.MEDIUM);
		
		// Expected order: A (HIGH), B (MEDIUM), Z (LOW)
		assertEquals("A Test Task", this.viewModel.getTaskList().get(0).getName(), "HIGH task should be first");
		assertEquals("B Test Task", this.viewModel.getTaskList().get(1).getName(), "MEDIUM task should be second");
		assertEquals("Z Test Task", this.viewModel.getTaskList().get(2).getName(), "LOW task should be third");
	}
	
	@Test
	void testUpdateDescriptionShouldResortIfComparatorIsByName() {
		// Set comparator to NameAscending (A -> Z)
		Comparator<Task> nameAscending = new NameAscending();
		this.viewModel.setCurrentComparator(nameAscending);
		this.viewModel.sortTasks();
		
		// Initial sort by Name: A (0), Z (1)
		assertEquals("A Test Task", this.viewModel.getTaskList().get(0).getName());
		
		// Update Z task's name to "C" (which is after A but before Z)
		Task taskToUpdate = this.viewModel.getTaskList().get(1); // Z Test Task
		this.viewModel.updateTaskDescription(taskToUpdate, "New Description"); // Update doesn't change Name, but triggers a sort.

		// Check if the list order is still A, Z (since the name didn't change)
		assertEquals("A Test Task", this.viewModel.getTaskList().get(0).getName(), "A should still be first");
		assertEquals("Z Test Task", this.viewModel.getTaskList().get(1).getName(), "Z should still be second");
	}
	
	@Test
	void testSortTasksWithCustomComparator() {
		// Test manual sort using NameAscending (A -> Z)
		Comparator<Task> nameAscending = new NameAscending();
		this.viewModel.setCurrentComparator(nameAscending);
		this.viewModel.sortTasks();
		
		// Expected order: A (0), Z (1)
		assertEquals("A Test Task", this.viewModel.getTaskList().get(0).getName(), "A Test Task should be first when sorting by name A->Z");
		assertEquals("Z Test Task", this.viewModel.getTaskList().get(1).getName(), "Z Test Task should be second when sorting by name A->Z");
	}
	
	// --- Task Utility (ViewModel) Tests ---
	
	@Test
	void testCountPriority() {
		assertEquals(1, this.viewModel.countPriority(TaskPriority.HIGH), "Should count one HIGH task");
		assertEquals(1, this.viewModel.countPriority(TaskPriority.LOW), "Should count one LOW task");
		assertEquals(0, this.viewModel.countPriority(TaskPriority.MEDIUM), "Should count zero MEDIUM tasks");
		
		this.viewModel.addTask("Another HIGH", "d", TaskPriority.HIGH);
		assertEquals(2, this.viewModel.countPriority(TaskPriority.HIGH), "Should count two HIGH tasks after adding one");
	}


}
