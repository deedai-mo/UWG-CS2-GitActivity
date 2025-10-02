package edu.westga.cs1302.tasktracker.model;
import java.util.List;

	/**
	 * A utility class for performing operations on Task objects.
	 *
	 * @author CS 1302
	 * @version Fall 2025
	 */
	public class TaskUtils {
		 public static int countTasksByPriority(String priority, List<Task> tasks) {
		        if (priority == null) {
		            throw new IllegalArgumentException("Priority cannot be null.");
		        }
		        if (tasks == null) {
		            throw new IllegalArgumentException("Task list cannot be null.");
		        }

		        int count = 0;
		        for (Task task : tasks) {
		            if (task.getPriority().equals(priority)) {
		                count++;
		            }
		        }
		        return count;
		    }
	}


