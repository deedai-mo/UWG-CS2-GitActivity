package edu.westga.cs1302.task_tracker.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Manages the collection of tasks in the Task Tracker application.
 *
 * @author CS 1302
 * @version Fall 2025
 */

public class TaskTracker {
	private List<Task> tasks;

	/**
	 * Creates a new TaskTracker with an empty list of tasks.
	 *
	 * @precondition none
	 * @postcondition this.tasks != null
	 */
	public TaskTracker() {
		this.tasks = new ArrayList<Task>();
	}

	/**
	 * Adds a task to the tracker.
	 *
	 * @precondition task != null
	 * @postcondition task is added to the collection
	 *
	 * @param task the task to add
	 */
	public void addTask(Task task) {
		if (task == null) {
			throw new IllegalArgumentException("task must not be null");
		}
		this.tasks.add(task);
	}

	/**
	 * Removes a task from the tracker.
	 *
	 * @precondition task != null
	 * @postcondition task is removed from the collection
	 *
	 * @param task the task to remove
	 * @return true if the task was removed, false otherwise
	 */
	public boolean removeTask(Task task) {
		if (task == null) {
			throw new IllegalArgumentException("task must not be null");
		}
		return this.tasks.remove(task);
	}

	/**
	 * Gets an unmodifiable list of all tasks.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the list of tasks
	 */
	public List<Task> getTasks() {
		return new ArrayList<Task>(this.tasks);
	}

	/**
	 * Sorts the tasks using the provided comparator.
	 *
	 * @precondition comparator != null
	 * @postcondition tasks are sorted based on the comparator
	 *
	 * @param comparator the comparator to use for sorting
	 */
	public void sortTasks(Comparator<Task> comparator) {
		if (comparator == null) {
			return;
		}
		TaskUtility.sort(this.tasks, comparator);
	}
}
	

