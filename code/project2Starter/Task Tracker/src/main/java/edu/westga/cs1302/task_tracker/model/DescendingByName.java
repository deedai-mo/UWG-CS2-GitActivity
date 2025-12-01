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
     * Compares two Task objects based on their name in descending order.
     *
     * @precondition task1 != null && task2 != null
     * @postcondition none
     *
     * @param task1 the first task to be compared
     * @param task2 the second task to be compared
     * @return a negative integer, zero, or a positive integer as the 
     * first task's name is greater than, equal to, or less than 
     * the second task's name.
     */
    @Override
    public int compare(Task task1, Task task2) {
        if (task1 == null || task2 == null) {
            throw new IllegalArgumentException("Tasks being compared must not be null.");
        }
        return task2.getName().compareTo(task1.getName());
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
