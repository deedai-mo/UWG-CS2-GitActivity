package edu.westga.cs1302.tas_tracke.viewmodel;

import java.util.Comparator;

import edu.westga.cs1302.task_tracker.model.Task;
import edu.westga.cs1302.task_tracker.model.Task.TaskPriority;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The ViewModel manages the state of the task list and contains all business logic.
 * It sits between the UI (MainWindow) and the Model (Task).
 * * @author CS 1302
 * @version Fall 2025
 */

public class TaskTrackerViewModel {
	private final ObservableList<Task> taskList;
	private Comparator<Task> currentComparator;

	/**
	 * Initializes a new TaskTrackerViewModel, initializing the observable list.
	 */
	public TaskTrackerViewModel() {
		this.taskList = FXCollections.observableArrayList();
		// Set a default comparator (e.g., the Task's natural order)
		this.currentComparator = Comparator.naturalOrder();
	}

	/**
	 * Returns the observable list of tasks for the UI to bind to.
	 * @return the observable list of tasks.
	 */
	public ObservableList<Task> getTaskList() {
		return this.taskList;
	}

	/**
	 * Adds a new task to the list and re-sorts the list immediately.
	 * Fulfills the "re-sort when adding" part of requirement A.
	 * * @param name the name of the task
	 * @param description the description of the task
	 * @param priority the priority of the task
	 */
	public void addTask(String name, String description, TaskPriority priority) {
		Task newTask = new Task(name, description, priority);
		this.taskList.add(newTask);
		this.sortTasks();
	}

	/**
	 * Updates the description of the selected task and re-sorts the list immediately.
	 * Fulfills the "re-sort when updating" part of requirement A.
	 * * @precondition selectedTask != null
	 * @param selectedTask the task to update
	 * @param newDescription the new description
	 */
	public void updateTaskDescription(Task selectedTask, String newDescription) {
		if (selectedTask == null) {
			// Should be handled by the controller/view, but included for robustness
			throw new IllegalArgumentException("Selected task must not be null.");
		}
		
		selectedTask.setDescription(newDescription);
		
		// Note: Since only the description changed, sorting by priority will not be affected.
		// However, to ensure *all* updates trigger a resort (as per the requirement),
		// and to handle cases where a different property might be updated, we call sortTasks.
		this.sortTasks();
	}

	/**
	 * Sets the comparator to be used for future sorting operations.
	 * @param comparator the comparator to use.
	 */
	public void setCurrentComparator(Comparator<Task> comparator) {
		this.currentComparator = comparator;
	}

	/**
	 * Sorts the list of tasks using the currently active comparator.
	 */
	public void sortTasks() {
		if (this.currentComparator != null) {
			this.taskList.sort(this.currentComparator);
		} else {
			// Fallback to Task's natural order
			this.taskList.sort(Comparator.naturalOrder());
		}
	}
	
	/**
	 * Returns the count of tasks with the specified priority.
	 * @param priority the priority to count.
	 * @return the count.
	 */
	public int countPriority(TaskPriority priority) {
		return (int) this.taskList.stream()
                .filter(task -> task.getPriority() == priority)
                .count();
	}

}
