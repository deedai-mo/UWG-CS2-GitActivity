package edu.westga.cs1302.task_tracker.model;

import java.util.Comparator;

/** Compare two Tasks to identify the correct Ascending ordering of the tasks.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class Ascending implements Comparator<Task> {


	@Override
    public int compare(Task task1, Task task2) {
        if (task1 == null || task2 == null) {
            throw new IllegalArgumentException("Tasks being compared must not be null.");
        }
        return Integer.compare(task1.getPriority().getValue(), task2.getPriority().getValue());
    }

	/** Returns the name of the task to represent the task as a String
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the name of the task
	 */
	@Override
	public String toString() {
		return "Priority(Low to High)";
	}

}
