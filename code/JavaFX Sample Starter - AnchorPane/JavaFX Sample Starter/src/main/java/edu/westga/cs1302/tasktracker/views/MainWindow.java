package edu.westga.cs1302.tasktracker.views;

import edu.westga.cs1302.tasktracker.model.Priority;
import edu.westga.cs1302.tasktracker.model.Task;
import edu.westga.tasktracker.util.TaskUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;


/**
 * Controller class for drawing various things to our canvas window.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindow {
	  @FXML
	    private TextField newTaskNameField;         // input for name

	    @FXML
	    private TextArea newTaskDescriptionArea;    // input for description

	    @FXML
	    private ComboBox<Priority> priorityComboBox; // choose priority when creating a task

	    @FXML
	    private Button addTaskButton;               // button to create new task

	    @FXML
	    private ListView<Task> tasksListView;       // shows all tasks (selectable)

	    @FXML
	    private TextArea displayDescriptionArea;    // shows/edits selected task description

	    @FXML
	    private TextField displayPriorityField;     // shows selected task priority (read-only)

	    @FXML
	    private Button updateDescriptionButton;     // apply changes to description

	    @FXML
	    private Button removeTaskButton;            // remove selected task

	    @FXML
	    private Button countByPriorityButton;       // show counts for each priority

	    @FXML
	    private Label lowCountLabel;                // label to display low count

	    @FXML
	    private Label mediumCountLabel;             // label to display medium count

	    @FXML
	    private Label highCountLabel;               // label to display high count

	    // --- backing model collection ---
	    private ObservableList<Task> taskList;      // observable list used by ListView

	    // Initialize method called by FXMLLoader after fields are injected
	    @FXML
	    private void initialize() {
	        // initialize the observable list and bind to ListView
	        this.taskList = FXCollections.observableArrayList();
	        this.tasksListView.setItems(this.taskList);

	        // initialize combobox with all Priority values
	        this.priorityComboBox.setItems(FXCollections.observableArrayList(Priority.values()));
	        this.priorityComboBox.setValue(Priority.MEDIUM); // default selection

	        // configure display controls
	        this.displayDescriptionArea.setEditable(false);
	        this.displayPriorityField.setEditable(false);

	        // Add listener to update display when selection changes
	        this.tasksListView.getSelectionModel().selectedItemProperty().addListener(
	            (obs, oldTask, newTask) -> displaySelectedTask(newTask)
	        );
	    }

	    // handle the Add Task button
	    @FXML
	    private void handleAddTask(ActionEvent event) {
	        try {
	            String name = this.newTaskNameField.getText();
	            String desc = this.newTaskDescriptionArea.getText();
	            Priority priority = this.priorityComboBox.getValue();

	            Task t = new Task(name, desc, priority);
	            this.taskList.add(t);

	            // clear inputs after successful add
	            this.newTaskNameField.clear();
	            this.newTaskDescriptionArea.clear();

	            // optionally select the new task
	            this.tasksListView.getSelectionModel().select(t);

	        } catch (IllegalArgumentException ex) {
	            // Show an alert instead of crashing; helps with invalid input
	            showErrorAlert("Invalid Task", ex.getMessage());
	        }
	    }

	    // Display the selected task's details in the UI
	    private void displaySelectedTask(Task task) {
	        if (task == null) {
	            this.displayDescriptionArea.clear();
	            this.displayPriorityField.clear();
	            return;
	        }
	        this.displayDescriptionArea.setText(task.getDescription());
	        this.displayPriorityField.setText(task.getPriority().name());
	    }

	    // handle update description button
	    @FXML
	    private void handleUpdateDescription(ActionEvent event) {
	        Task selected = this.tasksListView.getSelectionModel().getSelectedItem();
	        if (selected == null) {
	            showErrorAlert("No task selected", "Please select a task to update.");
	            return;
	        }
	        String newDesc = this.displayDescriptionArea.getText();
	        selected.setDescription(newDesc);

	        // Because Task.toString returns the name, ListView doesn't need refresh for name.
	        // If you changed something displayed by toString you'd need to refresh.
	        this.tasksListView.refresh();
	    }

	    // handle remove button
	    @FXML
	    private void handleRemoveTask(ActionEvent event) {
	        Task selected = this.tasksListView.getSelectionModel().getSelectedItem();
	        if (selected == null) {
	            showErrorAlert("No task selected", "Please select a task to remove.");
	            return;
	        }
	        this.taskList.remove(selected);
	    }

	    // count tasks for each priority and display
	    @FXML
	    private void handleCountByPriority(ActionEvent event) {
	        long low = TaskUtils.countTasksByPriority(Priority.LOW, this.taskList);
	        long med = TaskUtils.countTasksByPriority(Priority.MEDIUM, this.taskList);
	        long high = TaskUtils.countTasksByPriority(Priority.HIGH, this.taskList);

	        this.lowCountLabel.setText("Low: " + low);
	        this.mediumCountLabel.setText("Medium: " + med);
	        this.highCountLabel.setText("High: " + high);
	    }

	   
	    private void showErrorAlert(String title, String message) {
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(message);
	        alert.showAndWait();
	    }
}
