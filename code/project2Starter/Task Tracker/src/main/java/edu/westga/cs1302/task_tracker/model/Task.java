package edu.westga.cs1302.task_tracker.model;

import java.util.ArrayList;
import java.util.List;

/** Stores basic information for a Task
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class Task {
	
	/** Possible priority options for a Task
	 * 
	 * @author CS 1302
	 * @version Fall 2025
	 */
	public enum TaskPriority {
		HIGH(1),
		MEDIUM(2),
		LOW(3);
		
		private int value;
		
		TaskPriority(int value) {
			this.value = value;
		}
		
		/** Return the value for the priority
		 * HIGH is 1
		 * MEDIUM is 2
		 * LOW is 3
		 * 
		 * @return the value
		 */
		public int getValue() {
			return this.value;
		}
	}
	
	private String description;
	private final String name;
	private final TaskPriority priority;
	//private final List<Task> subtasks;
	
	/** Create a new Task with the provided information.
	 * 
	 * @precondition name != null && !name.isEmpty() &&
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
	 * Adds a subtask to this task. Since this is a simple Task (Leaf), 
	 * it converts itself into a ContainerTask (Composite) and returns the new composite object.
	 *
	 * @param task the subtask to add
	 * @return the new ContainerTask composite object
	 */
	
	public Task addTask(Task task) {
		if (task == null) {
			throw new IllegalArgumentException("Task must not be null");
		}
		return new ContainerTask(this.name, this.description, this.priority, task);
	}
	
	/**
	 * Removes a subtask. For a Leaf Task, this always returns false.
	 *
	 * @param task the task to remove
	 * @return always false
	 */
	public boolean removeTask(Task task) {
	    return false;
	}
	
	/** Return the name of the task
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
	
	
	//    /**
	//     * Adds a subtask to this task.
	//     * @precondition subtask != null
	//     * @postcondition subtask is added to the subtasks list
	//     *
	//     * @param subtask the subtask to add
	//     */
	
	//    public void addSubtask(Task subtask) {
	//        if (subtask == null) {
	//            throw new IllegalArgumentException("subtask must not be null");
	//        }
	//        this.subtasks.add(subtask);
	//     }
	
	
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
	/**
	 * Returns a defensive copy of the list of the subtasks.
	 * The caller can modify this returned list without affecting the internal state.
	 *
	 * @return a new list containing the subtasks
	 */
	
	public List<Task> getSubTasks() {
		return new ArrayList<Task>();
	}
}
