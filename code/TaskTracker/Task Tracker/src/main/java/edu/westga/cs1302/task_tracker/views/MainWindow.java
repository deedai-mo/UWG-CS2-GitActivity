package edu.westga.cs1302.task_tracker.views;

import java.util.Comparator;

import edu.westga.cs1302.tas_tracke.viewmodel.TaskTrackerViewModel;
import edu.westga.cs1302.task_tracker.model.NameAscending;
import edu.westga.cs1302.task_tracker.model.NameDescending;
import edu.westga.cs1302.task_tracker.model.PriorityAscending;
import edu.westga.cs1302.task_tracker.model.PriorityDescending;
import edu.westga.cs1302.task_tracker.model.Task;
import edu.westga.cs1302.task_tracker.model.Task.TaskPriority;
import edu.westga.cs1302.task_tracker.model.TaskUtility;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/** Controller class for MainWindow of the Task Tracker system.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindow {
	private TaskTrackerViewModel viewModel;
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
    @FXML private ListView<Task> subTasksListView; 
    @FXML private TextField newSubtaskNameField; 
    @FXML private Button addSubTaskButton; 
    
    @FXML 
    void addTask(ActionEvent event) {
    	try {
    		this.tasks.getItems().add(new Task(this.name.getText(), this.description.getText(), this.priority.getValue()));
    	} catch (IllegalArgumentException error) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setContentText(error.getMessage());
    		alert.showAndWait();
    	}
    }

    @FXML
    void selectTask(MouseEvent event) {
    	Task selectedTask = this.tasks.getSelectionModel().getSelectedItem();
    	if (selectedTask != null) {
    		this.selectedPriority.setText(selectedTask.getPriority().toString());
    		this.selectedDescription.setText(selectedTask.getDescription());
    	}
    }
    
    @FXML
    void displaySelectedSubtask(MouseEvent event) {
        Task selectedSubtask = this.subTasksListView.getSelectionModel().getSelectedItem();

        if (selectedSubtask != null) {
            String content = String.format(
                "Name: %s\nPriority: %s\n\nDescription:\n%s",
                selectedSubtask.getName(),
                selectedSubtask.getPriority().toString(),
                selectedSubtask.getDescription()
            );

            // Create and show the popup alert
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Subtask Details");
            alert.setHeaderText(selectedSubtask.getName() + " Details");
            alert.setContentText(content);
            alert.showAndWait();
        }
    }
    @FXML
    void addSubTask(ActionEvent event) {
        Task selectedTask = this.tasks.getSelectionModel().getSelectedItem();
        String subTaskName = this.newSubtaskNameField.getText();
        
        if (selectedTask == null) {
            new Alert(AlertType.WARNING, "Please select a task to add a subtask to.").showAndWait();
            return;
        }
        
        if (subTaskName == null || subTaskName.trim().isEmpty()) {
             new Alert(AlertType.WARNING, "Subtask name cannot be empty.").showAndWait();
            return;
        }

        try {
            // Create a simple new Task object for the subtask (LOW priority used as default)
            Task newSubtask = new Task(subTaskName.trim(), "Subtask of " + selectedTask.getName(), TaskPriority.LOW);
            
            // Get the list index of the task being replaced
            int selectedIndex = this.tasks.getSelectionModel().getSelectedIndex();
            
            // Call the addTask method on the selected task, which returns a ContainerTask (as Task type)
            Task newContainerTask = selectedTask.addTask(newSubtask);
            
            // --- Requirement 3C: Replace the currently selected Task ---
            // Remove the old task and insert the new container task at the same index
            this.tasks.getItems().remove(selectedIndex);
            this.tasks.getItems().add(selectedIndex, newContainerTask);
            
            // Re-select the new container task and refresh the subtask display
            this.tasks.getSelectionModel().select(selectedIndex);
            this.selectTask(null); // Manually call selectTask to refresh subtask list
            this.newSubtaskNameField.clear();
            
            // Re-sort the main list as a property might have changed (e.g., toString display)
            this.viewModel.sortTasks();
            
        } catch (IllegalArgumentException error) {
            new Alert(AlertType.ERROR, error.getMessage()).showAndWait();
        }
    }


    @FXML
    void removeTask(ActionEvent event) {
    	Task selectedTask = this.tasks.getSelectionModel().getSelectedItem();
    	if (selectedTask != null) {
    		this.tasks.getItems().remove(selectedTask);
    	}
    }

    @FXML
    void updateDescription(ActionEvent event) {
    	Task selectedTask = this.tasks.getSelectionModel().getSelectedItem();
    	if (selectedTask != null) {
    		selectedTask.setDescription(this.selectedDescription.getText());
    	}
    }

    @FXML
    void countPriorities(ActionEvent event) {
    	this.highCount.setText(Integer.toString(TaskUtility.countOfPriority(TaskPriority.HIGH, this.tasks.getItems())));
    	this.mediumCount.setText(Integer.toString(TaskUtility.countOfPriority(TaskPriority.MEDIUM, this.tasks.getItems())));
    	this.lowCount.setText(Integer.toString(TaskUtility.countOfPriority(TaskPriority.LOW, this.tasks.getItems())));
    }
    
    @FXML
    void sortTasks(ActionEvent event) {
    	Comparator<Task> selectedComparator = this.order.getValue();
    	if (selectedComparator != null) {
    		this.tasks.getItems().sort(selectedComparator);
    	}

    }

    /** Perform any needed initialization of UI components and underlying objects.
     * 
     */
    public void initialize() {
    	this.viewModel = new TaskTrackerViewModel();

    	this.tasks.setItems(this.viewModel.getTaskList());

    	this.priority.getItems().addAll(TaskPriority.HIGH, TaskPriority.MEDIUM, TaskPriority.LOW);
    	this.priority.setValue(this.priority.getItems().get(0));

    	// Initialize the subtask list view
    	this.subTasksListView.setItems(FXCollections.observableArrayList());

    	this.order.getItems().addAll(
    			new PriorityAscending(),
    			new PriorityDescending(),
    			new NameAscending(),
    			new NameDescending()
    			);

    	this.order.setValue(this.order.getItems().get(0));
    	this.viewModel.setCurrentComparator(this.order.getValue());
    }
}
