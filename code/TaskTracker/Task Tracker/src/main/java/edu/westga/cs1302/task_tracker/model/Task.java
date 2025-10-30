package edu.westga.cs1302.task_tracker.model;

import java.util.ArrayList;
import java.util.List;

/** Stores basic information for a Task
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class Task implements Comparable<Task> {
	
	/** Possible priority options for a Task
	 * 
	 * @author CS 1302
	 * @version Fall 2025
	 */
	public enum TaskPriority {
		HIGH,
		MEDIUM,
		LOW;
	}
	
	private String description;
	private final String name;
	private final TaskPriority priority;
	
	/** Create a new Task with the provided information.
	 * 
	 * @preconditon name != null && !name.isEmpty() &&
	 * 				description != null &&
	 * 				priority != null
	 * 
	 * @param name the name of the task
	 * @param description the description of the task
	 * @param priority the priority of the task
	 */
	public Task(String name, String description, TaskPriority priority) {
		if (name == null) {
			throw new IllegalArgumentException("name must not be null");
		}
		if (name.isEmpty()) {
			throw new IllegalArgumentException("name must not be empty");
		}
		if (description == null) {
			throw new IllegalArgumentException("description must not be null");
		}
		if (priority == null) {
			throw new IllegalArgumentException("priority must not be null");
		}
		this.name = name;
		this.description = description;
		this.priority = priority;
	}
	/**
	 * Adds a subtask and upgrades this Task to a ContainerTask.
	 * * @param subTask the Task to be added as a subtask.
	 * @return a new ContainerTask object (returned as the base type Task) containing both the original task (this) and the new subtask.
	 */
	public Task addTask(Task subTask) { // FIX: Changed return type to Task for proper covariant override
		if (subTask == null) {
			throw new IllegalArgumentException("Subtask cannot be null.");
		}
		// Create the new ContainerTask with the original task's properties
		ContainerTask newContainer = new ContainerTask(this.name, this.getDescription(), this.priority);
		
		// Add the original task (this) as the first subtask to maintain task history/structure
		newContainer.addTask(this);
		
		// Add the new subtask argument
		newContainer.addTask(subTask);
		
		return newContainer;
	}
	
	/**
	 * Returns an empty list of Task objects for a base Task that is not a ContainerTask.
	 * @return an empty list.
	 */
	public List<Task> getSubTasks() {
		return new ArrayList<>(); 
	}
	
	
	/** Return the name of the task
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the name of the task
	 */
	public String getName() {
		return this.name;
	}
	
	/** Return the description of the task
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the description of the task
	 */
	public String getDescription() {
		return this.description;
	}
	
	/** Return the priority of the task
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the priority of the task
	 */
	public TaskPriority getPriority() {
		return this.priority;
	}
	
	/** Updates the description to the provided value
	 * 
	 * @precondition description != null
	 * @postcondition none
	 * 
	 * @param description the new description for the task
	 */
	public void setDescription(String description) {
		if (description == null) {
			throw new IllegalArgumentException("description must not be null");
		}
		this.description = description;
	}

	/** Returns the name of the task to represent the task as a String
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the name of the task
	 */
	@Override
	public String toString() {
		return this.name;
	}
	
	@Override
	public int compareTo(Task other) {
		return this.priority.compareTo(other.priority);
	}
}
