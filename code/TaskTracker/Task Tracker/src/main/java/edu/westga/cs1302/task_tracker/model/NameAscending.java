package edu.westga.cs1302.task_tracker.model;

import java.util.Comparator;

/**
 * Compares two Tasks based on their name in ascending (alphabetical) order (A -> Z).
 * @author CS 1302
 * @version Fall 2025
 */

public class NameAscending implements Comparator<Task> {
	private static final String COMPARATOR_NAME = "Name: A -> Z";

    /**
	 * Compares its two Task arguments for order based on task name.
	 * @param task1 the first Task to be compared
	 * @param task2 the second Task to be compared
	 * @return a negative integer, zero, or a positive integer as task1's name
	 * is alphabetically less than, equal to, or greater than task2's name.
	 */
    @Override
    public int compare(Task task1, Task task2) {
        // Compares task names alphabetically (A -> Z)
        return task1.getName().compareTo(task2.getName());
    }

    /**
	 * Returns the name of the comparator to display in the UI.
	 * @return the name of the comparator
	 */
    @Override
    public String toString() {
        return COMPARATOR_NAME;
    }

}
