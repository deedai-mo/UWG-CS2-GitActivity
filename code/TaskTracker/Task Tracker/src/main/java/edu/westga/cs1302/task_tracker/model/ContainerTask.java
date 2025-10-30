package edu.westga.cs1302.task_tracker.model;

import java.util.ArrayList;
import java.util.List;

/**
* Represents a Task that can contain a list of subtasks.
* Inherits the name, description, and priority from the base Task.
* * @author CS 1302
* @version Fall 2025
*/

public class ContainerTask extends Task{
	private final List<Task> subtasks;

	/**
	 * Creates a new ContainerTask.
	 * * @param name The name of the task.
	 * @param description The description of the task.
	 * @param priority The priority of the task.
	 */
	public ContainerTask(String name, String description, TaskPriority priority) {
		super(name, description, priority);
		this.subtasks = new ArrayList<>();
	}

	/**
	 * Overrides the base Task method to return the list of subtasks stored in this container.
	 * * @return the list of subtasks.
	 */
	@Override
	public List<Task> getSubTasks() {
		return this.subtasks;
	}

	/**
	 * Overrides the base Task method to add the argument to its list of subtasks and return itself.
	 * * @param subTask the Task to be added as a subtask.
	 * @return this ContainerTask instance.
	 */
	@Override
	public ContainerTask addTask(Task subTask) {
		if (subTask == null) {
			throw new IllegalArgumentException("Subtask cannot be null.");
		}
		this.subtasks.add(subTask);
		return this;
	}

	/**
	 * Overrides the base Task method to include the count of subtasks.
	 * * @return the name of the task followed by the subtask count (e.g., "Clean Room (3 subtasks)").
	 */
	@Override
	public String toString() {
		return super.toString() + " (" + this.subtasks.size() + " subtasks)";
	}
	

}
