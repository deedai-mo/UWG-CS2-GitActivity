package edu.westga.cs1302.task_tracker.model;

import java.util.Comparator;
import java.util.List;

import edu.westga.cs1302.task_tracker.model.Task.TaskPriority;

/**
 * Provides utility functions for working with tasks
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class TaskUtility {

	/**
	 * Returns the number of tasks with the specified priority in the provided list
	 * 
	 * @precondition tasks != null
	 * @postcondition none
	 * 
	 * @param priority the priority of tasks to include in the count
	 * @param tasks    the list of tasks to use for the count
	 * 
	 * @return the count of the number of tasks in the provided list with the
	 *         specified priority
	 */
	public static int countOfPriority(TaskPriority priority, List<Task> tasks) {
		if (priority == null || tasks == null) {
			return 0;
		}
		int count = 0;
		for (Task task : tasks) {
			if (task.getPriority() == priority) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Sorts the given list of tasks using the provided comparator.
	 * 
	 * @param tasks      the list to sort
	 * @param comparator the comparator to use
	 */
	public static void sort(List<Task> tasks, Comparator<Task> comparator) {
		for (int count1 = 0; count1 < tasks.size() - 1; count1++) {
			for (int count2 = 0; count2 < tasks.size() - count1 - 1; count2++) {
				if (comparator.compare(tasks.get(count2), tasks.get(count2 + 1)) > 0) {
					Task temp = tasks.get(count2);
					tasks.set(count2, tasks.get(count2 + 1));
					tasks.set(count2 + 1, temp);
				}
			}
		}
	}

}
