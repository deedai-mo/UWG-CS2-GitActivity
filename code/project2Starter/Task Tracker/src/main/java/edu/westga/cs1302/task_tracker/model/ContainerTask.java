package edu.westga.cs1302.task_tracker.model;

import java.util.ArrayList;
import java.util.List;
import edu.westga.cs1302.task_tracker.model.Task.TaskPriority;

/**
 * Represents a Task that contains a list of subtasks. Inherits from Task.
 *
 * @author CS 1302
 * @version Fall 2025
 */
public class ContainerTask extends Task {
	 private List<Task> subtasks;

	    /**
	     * Constructs a new ContainerTask using the data from the original Task (Leaf) 
	     * and adding the first subtask.
	     *
	     * @param name the name of the original task
	     * @param description the description of the original task
	     * @param priority the priority of the original task
	     * @param firstSubtask the first task to be added as a subtask
	     */
	    public ContainerTask(String name, String description, TaskPriority priority, Task firstSubtask) {
	        super(name, description, priority);
	        this.subtasks = new ArrayList<Task>();
	        if (firstSubtask != null) {
	            this.subtasks.add(firstSubtask);
	        }
	    }

	    /**
	     * Adds a subtask to this container.
	     *
	     * @param task the subtask to add
	     * @return this ContainerTask instance
	     */
	    @Override
	    public Task addTask(Task task) {
	        if (task == null) {
	            throw new IllegalArgumentException("Task must not be null");
	        }
	        this.subtasks.add(task);
	        return this; // Return itself since it's already a composite
	    }

	    /**
	     * Removes a subtask from this container.
	     *
	     * @param task the subtask to remove
	     * @return true if the subtask was successfully removed, false otherwise
	     */
	    @Override
	    public boolean removeTask(Task task) {
	        return this.subtasks.remove(task);
	    }

	    /**
	     * Returns a defensive copy of the list of the subtasks.
	     * The caller can modify this returned list without affecting the internal state.
	     *
	     * @return a new list containing the subtasks
	     */
	    @Override
	    public List<Task> getSubTasks() {
	        return new ArrayList<Task>(this.subtasks);
	    }
	    
	    /** Returns the name of the task with a subtask indicator */
	    @Override
	    public String toString() {
	        return this.getName() + " (+)";
	    }
	
}
