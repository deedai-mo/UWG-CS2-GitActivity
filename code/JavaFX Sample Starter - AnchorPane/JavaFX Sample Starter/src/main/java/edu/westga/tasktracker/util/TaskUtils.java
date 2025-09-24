package edu.westga.tasktracker.util;

import java.util.List;

import edu.westga.cs1302.tasktracker.model.Priority;
import edu.westga.cs1302.tasktracker.model.Task;

public class TaskUtils {
	 private TaskUtils() { }

	    /**
	     * Count tasks of the given priority in the provided list.
	     * Static so it can be called without creating an object.
	     *
	     * @param priority the priority to count (non-null)
	     * @param tasks the list of tasks (non-null)
	     * @return number of tasks with that priority
	     */
	    public static long countTasksByPriority(Priority priority, List<Task> tasks) {
	        if (priority == null) {
	            throw new IllegalArgumentException("Priority must not be null.");
	        }
	        if (tasks == null) {
	            throw new IllegalArgumentException("Tasks list must not be null.");
	        }
	        return tasks.stream()
	                    .filter(t -> t.getPriority() == priority)
	                    .count();
	    }

}
