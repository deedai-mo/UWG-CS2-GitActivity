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
	private final List<Task> subtasks;
	
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
		this.subtasks = new ArrayList<Task>();
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
	/**
	 * Returns an unmodifiable list of the subtasks.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the list of subtasks
	 */
	
	public List<Task> getSubtasks() {
	    return new ArrayList<Task>(this.subtasks);
	}
	
    /**
     * Adds a subtask to this task.
     * @precondition subtask != null
     * @postcondition subtask is added to the subtasks list
     *
     * @param subtask the subtask to add
     */
	
    public void addSubtask(Task subtask) {
        if (subtask == null) {
            throw new IllegalArgumentException("subtask must not be null");
        }
        this.subtasks.add(subtask);
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
}
