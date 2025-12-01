 package edu.westga.cs1302.task_tracker.views;

import java.util.Comparator;
import java.util.List;

import edu.westga.cs1302.task_tracker.model.Ascending;
import edu.westga.cs1302.task_tracker.model.Descending;
import edu.westga.cs1302.task_tracker.model.Task;
import edu.westga.cs1302.task_tracker.model.Task.TaskPriority;
import edu.westga.cs1302.task_tracker.model.TaskTracker;
import edu.westga.cs1302.task_tracker.model.TaskUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import edu.westga.cs1302.task_tracker.model.AscendingByName;
import edu.westga.cs1302.task_tracker.model.DescendingByName;

/** 
 * Controller class for MainWindow of the Task Tracker system.
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindow {
    @FXML private TextArea description;
    @FXML private Label highCount;
    @FXML private Label lowCount;
    @FXML private Label mediumCount;
    @FXML private TextField name;
    @FXML private ComboBox<TaskPriority> priority;
    @FXML private TextArea selectedDescription;
    @FXML private TextField selectedPriority;
    @FXML private ListView<Task> tasks;
    @FXML private ComboBox<Comparator<Task>> order;
    @FXML private Button addSubTasksButton;
    @FXML private ListView<Task> subtasks;
     private TaskTracker tracker;

    /** Add a new task with the provided information to the listview.
     * 
     * @precondition none
     * @postcondition A task will be added to the listview with 
     * 							  1) a name matching the text of the name textfield, 
     * 							  2) a description matching the text of the description textarea,
     * 							  3) a priority matching the selected value of the priority combobox,
     * 
     * @param event we will not use this parameter, only here due to JavaFX Library requirement
     */
    @FXML 
    void addTask(ActionEvent event) {    	
    	String taskName = this.name.getText();
		String taskDescription = this.description.getText();
		TaskPriority taskPriority = this.priority.getValue();
		if (taskName != null && !taskName.trim().isEmpty()) {
			try {
				Task newTask = new Task(taskName.trim(), taskDescription.trim(), taskPriority);
				this.tracker.addTask(newTask);
				this.sortTasks(null);
				this.countPriorities(null);
				
				 this.name.clear();
	             this.description.clear();
	             this.priority.getSelectionModel().selectFirst();
				
			} catch (IllegalArgumentException error) {
				System.err.println("Error adding task: " + error.getMessage());
				
			}
		}
    	
    }
    
    /** Implements the logic to add a subtask to the currently selected task.
     * Handles the conversion of a simple Task to a ContainerTask if necessary using the Composite pattern.
     *  @param event the ActionEvent
     */
    @FXML
    
    void addSubtask(ActionEvent event) {
    	
        Task parentTask = this.tasks.getSelectionModel().getSelectedItem();

        if (parentTask == null) {
            System.err.println("Error: Must select a main task to add a subtask.");
            return;
        }

        String subName = this.name.getText();
        String subDescription = this.description.getText();
        TaskPriority subPriority = this.priority.getSelectionModel().getSelectedItem();

        if (subName != null && !subName.trim().isEmpty()) {
            try {
                Task newSubtask = new Task(subName.trim(), subDescription.trim(), subPriority);
                
                Task returnedTask = parentTask.addTask(newSubtask);

                if (returnedTask != parentTask) {
                  
                    this.tracker.removeTask(parentTask);
                    this.tracker.addTask(returnedTask);
               
                    this.sortTasks(null); 
                    int newIndex = -1;
                    List<Task> currentTasks = this.tasks.getItems();
                    for (int i = 0; i < currentTasks.size(); i++) {
                        if (currentTasks.get(i) == returnedTask) {
                            newIndex = i;
                            break;
                        }
                    }
                    if (newIndex != -1) {
                        this.tasks.getSelectionModel().select(newIndex);
                    }
                } 
                
                this.refreshSubtasksListView(returnedTask);
                this.name.clear();
                this.description.clear();
                this.priority.getSelectionModel().selectFirst();
            } catch (IllegalArgumentException error) {
                System.err.println("Error adding subtask: " + error.getMessage());
            }
        }
    }


    /** Display the priority and description of the task selected in the listview.
     * 
     * @precondition none
     * @postcondition the description for the selected task will be displayed in the selectedDescription text area &&
     * 				  the priority for the selected task will be displayed in the selectedPriority text field
     * 
     * @param event we will not use this parameter, only here due to JavaFX Library requirement
     */
    @FXML
    void selectTask(MouseEvent event) {
    	Task selectedTask = this.tasks.getSelectionModel().getSelectedItem();

    	this.selectedPriority.setText("");
    	this.selectedDescription.clear();
    	this.subtasks.getItems().clear();

    	if (selectedTask != null) {
    		this.selectedPriority.setText(selectedTask.getPriority().toString());
    		this.selectedDescription.setText(selectedTask.getDescription());

    		this.refreshSubtasksListView(selectedTask);
    	}
    }
    /**
     * Implements the functionality to display a subtask's details when selected.
     * This simulates displaying the details in a popup window.
     * * @param event we will not use this parameter, only here due to JavaFX Library requirement
     */
    @FXML
    void selectSubtask(MouseEvent event) { // <-- NEW METHOD
        Task selectedSubtask = this.subtasks.getSelectionModel().getSelectedItem();

        if (selectedSubtask != null) {
            String details = String.format(
                "--- Subtask Details ---\n" +
                "Name: %s\n" +
                "Priority: %s\n" +
                "Description:\n%s\n" +
                "-----------------------\n",
                selectedSubtask.getName(),
                selectedSubtask.getPriority().toString(),
                selectedSubtask.getDescription()
            );
            System.out.println(details); 
        }
    }

    /** Remove the currently selected task.
     * 
     * @precondition none
     * @postcondition task selected in the listview will be removed
     * 
     * @param event we will not use this parameter, only here due to JavaFX Library requirement
     */
    @FXML
    void removeTask(ActionEvent event) {
        Task selectedTask = this.tasks.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            this.tracker.removeTask(selectedTask); 
            this.sortTasks(null); 
            this.countPriorities(null);
            
            this.selectedPriority.setText("");
            this.selectedDescription.clear();
            this.subtasks.getItems().clear();
        }
    }

    /** Update the description of the selected task.
     * 
     * @precondition none
     * @postcondition description for the task selected in the listview will be updated to match the text in the selectedDescription text area.
     * 
     * @param event we will not use this parameter, only here due to JavaFX Library requirement
     */
    @FXML
    void updateDescription(ActionEvent event) {
    	  Task selectedTask = this.tasks.getSelectionModel().getSelectedItem();
          String newDescription = this.selectedDescription.getText();
          if (selectedTask != null && newDescription != null) {
              try {
                  selectedTask.setDescription(newDescription.trim());
                  this.sortTasks(null);
              } catch (IllegalArgumentException error) {
                  System.err.println("Error updating description: " + error.getMessage());
              }
          }
    }

    /** Display the count of tasks for each priority.
     * 
     * @precondition none
     * @postcondition count of tasks for each priority are displayed in the appropriate labels.
     * 
     * @param event we will not use this parameter, only here due to JavaFX Library requirement
     */
    @FXML
    void countPriorities(ActionEvent event) {
    	this.highCount.setText(Integer.toString(TaskUtility.countOfPriority(TaskPriority.HIGH, this.tasks.getItems())));
    	this.mediumCount.setText(Integer.toString(TaskUtility.countOfPriority(TaskPriority.MEDIUM, this.tasks.getItems())));
    	this.lowCount.setText(Integer.toString(TaskUtility.countOfPriority(TaskPriority.LOW, this.tasks.getItems())));
    }
    
    /** Sort tasks based on the selected ordering.
     * 
     * @precondition none
     * @postcondition tasks in the list view are sorted based on the provided ordering.
     * 
     * @param event we will not use this parameter, only here due to JavaFX Library requirement
     */
    @FXML
    void sortTasks(ActionEvent event) {
    	Comparator<Task> selectedComparator = this.order.getSelectionModel().getSelectedItem();

		if (selectedComparator != null) {
			this.tracker.sortTasks(selectedComparator);
			this.tasks.getItems().clear();
			List<Task> sortedTasks = this.tracker.getTasks();
			for (Task task : sortedTasks) {
	            this.tasks.getItems().add(task);
	        }
		}
    }
    

    /** Perform any needed initialization of UI components and underlying objects.
     * 
     * @precondition none
     * @postcondition none
     * 
     */
    @FXML
    public void initialize() {
    	this.tracker = new TaskTracker();
    	this.priority.getItems().add(Task.TaskPriority.HIGH);
        this.priority.getItems().add(Task.TaskPriority.MEDIUM);
        this.priority.getItems().add(Task.TaskPriority.LOW);
        this.priority.getSelectionModel().selectFirst();
    	this.order.getItems().add(new Ascending());
    	this.order.getItems().add(new Descending());
    	this.order.getItems().add(new AscendingByName());
    	this.order.getItems().add(new DescendingByName());
    	this.order.getSelectionModel().selectFirst();
        this.highCount.setText("0");
        this.mediumCount.setText("0");
        this.lowCount.setText("0");
    	
    }
    /**
     * Helper method to refresh the subtasks ListView for a given task.
     * @param task the task whose subtasks should be displayed
     */
    
    private void refreshSubtasksListView(Task task) {
    	 this.subtasks.getItems().clear();
         if (task != null) {
             List<Task> currentSubtasks = task.getSubTasks(); 
             for (int i = 0; i < currentSubtasks.size(); i++) {
                 Task subtask = currentSubtasks.get(i);
                 this.subtasks.getItems().add(subtask);
             }
         }
     }
}
