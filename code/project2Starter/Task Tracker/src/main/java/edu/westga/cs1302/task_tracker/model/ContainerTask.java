package edu.westga.cs1302.task_tracker.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Task that contains a list of subtasks. Inherits from Task.
 *
 * @author CS 1302
 * @version Fall 2025
 */
public class ContainerTask extends Task {
	private final List<Task> subtasks;
	
	/**
	 * Creates a new ContainerTask. This is used when a base Task is converted.
	 *
	 * @param name the name of the task
	 * @param description the description of the task
	 * @param priority the priority of the task
	 * @param firstSubtask the first subtask to be added
	 */
	public ContainerTask(String name, String description, TaskPriority priority, Task firstSubtask) {
		super(name, description, priority);
		
		if (firstSubtask == null) {
			throw new IllegalArgumentException("firstSubtask must not be null");
		}
		
		this.subtasks = new ArrayList<Task>();
		this.subtasks.add(firstSubtask);
	}
	
}
