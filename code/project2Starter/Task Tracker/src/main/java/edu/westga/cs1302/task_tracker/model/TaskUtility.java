package edu.westga.cs1302.task_tracker.model;

import java.util.Comparator;
import java.util.List;

import edu.westga.cs1302.task_tracker.model.Task.TaskPriority;

/** Provides utility functions for working with tasks
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class TaskUtility {

	/** Returns the number of tasks with the specified priority in the provided list
	 * 
	 * @precondition tasks != null
	 * @postcondition none
	 * 
	 * @param priority the priority of tasks to include in the count
	 * @param tasks the list of tasks to use for the count
	 * 
	 * @return the count of the number of tasks in the provided list with the specified priority
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
     * @param tasks the list to sort
     * @param comparator the comparator to use
     */
    public static void sort(List<Task> tasks, Comparator<Task> comparator) {
        for (int i = 0; i < tasks.size() - 1; i++) {
            for (int j = 0; j < tasks.size() - i - 1; j++) {
                if (comparator.compare(tasks.get(j), tasks.get(j + 1)) > 0) {
                    Task temp = tasks.get(j);
                    tasks.set(j, tasks.get(j + 1));
                    tasks.set(j + 1, temp);
                }
            }
        }
    }
    
    
}

