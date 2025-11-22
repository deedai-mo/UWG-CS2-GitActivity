package edu.westga.cs1302.task_tracker.model;

import java.util.Comparator;

/**
 * Compare two Tasks to identify the correct Descending ordering of the tasks by Name (Z to A).
 *
 * @author CS 1302
 * @version Fall 2025
 */

public class DescendingByName implements Comparator<Task> {
	/**
	 * Returns a value indicating ordering of the two tasks based on Descending Name.
	 *
	 * @precondition o1 != null && o2 != null
	 * @postcondition none
	 *
	 * @param o1 the first task to compare
	 * @param o2 the second task to compare
	 *
	 * @return a negative integer, zero, or a positive integer as the
	 * first task name is less than, equal to, or greater than the second task name.
	 */
	@Override
	public int compare(Task o1, Task o2) {
		if (o1 == null || o2 == null) {
			throw new IllegalArgumentException("Tasks must not be null");
		}
		return o2.getName().compareTo(o1.getName());
	}

	/**
	 * Returns the name of the comparator to represent it as a String
	 *
	 * @return "Descending Name"
	 */
	@Override
	public String toString() {
		return "Descending Name";
	}

}
