package edu.westga.cs1302.tasktracker;

import java.util.List;

public class TaskUtils {
	/**
     * Counts the number of tasks in a list that have a specific priority.
     * * @param priority The priority to count.
     * @param tasks The list of tasks to search through.
     * @return The number of tasks with the specified priority.
     */
    public static int countTasksByPriority(String priority, List<Task> tasks) {
        int count = 0;
        for (Task task : tasks) {
            if (task.getPriority().equals(priority)) {
                count++;
            }
        }
        return count;
    }

}
