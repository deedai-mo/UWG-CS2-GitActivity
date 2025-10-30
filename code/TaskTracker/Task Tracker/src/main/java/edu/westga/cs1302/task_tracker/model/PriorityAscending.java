package edu.westga.cs1302.task_tracker.model;

import java.util.Comparator;

/**
 * Compares two Tasks based on priority in ascending order (LOW, MEDIUM, HIGH).
 * Lower priority comes first.
 * @author CS 1302
 * @version Fall 2025
 */
public class PriorityAscending implements Comparator<Task> {
	private static final String COMPARATOR_NAME = "PriorityAscending Priority (LOW -> HIGH)";
	
	/**
     * Compares its two Task arguments for order.
     * * @param task1 the first Task to be compared
     * @param task2 the second Task to be compared
     * @return a negative integer, zero, or a positive integer as task1's priority
     * is less than, equal to, or greater than task2's priority.
     */
	@Override 
	public int compare(Task task1, Task task2) {
		return task2.getPriority().compareTo(task1.getPriority());
		
	}
	/**
     * Returns the name of the comparator to display in the UI.
     *  @return the name of the comparator
     */
	
	@Override
	public String toString() {
		return COMPARATOR_NAME;
	}
}
