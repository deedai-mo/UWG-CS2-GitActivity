package edu.westga.cs1302.tasktracker.views;

import java.util.List;

import edu.westga.cs1302.tasktracker.model.Task;
import edu.westga.cs1302.tasktracker.model.TaskUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Controller class for drawing various things to our canvas window.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindow {

    @FXML
    private Button addaskButton;

    @FXML
    private TextArea newTaskDescriptionArea;

    @FXML
    private TextField newTaskNameField;

    @FXML
    private ComboBox<String> priorityComboBox;

    @FXML
    private ListView<Task> taskListView;
    
    @FXML
    private TextArea displayDescriptionArea;

    @FXML
    private TextField displayPriorityField;
    
    @FXML
    private Button updateDescriptiomButton;
    
    @FXML
    private Button countByPriorityButton;
    
    
    @FXML
    private Button removeTaskButton;
    
    @FXML
    private Label highPriorityLabel;
    
    @FXML
    private Label mediumPriorityLabel;
    
    @FXML
    private Label lowPriorityLabel;


  
    
    /**
     * Perform any needed initialization of UI components and underlying objects.
     */
    public void initialize() {
    	this.priorityComboBox.getItems().setAll("High", "Medium", "Low");
    	this.priorityComboBox.getSelectionModel().selectFirst();
    	this.taskListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    	    if (newValue != null) {
    	        this.displayDescriptionArea.setText(newValue.getDescription());
    	        this.displayPriorityField.setText(newValue.getPriority());
    	    } else {
    	        this.displayDescriptionArea.setText("");
    	        this.displayPriorityField.setText("");
    	    }
    	});

    	
    	
    }
    public void onAddTaskButtonClick(ActionEvent event) {
    	String taskName = this.newTaskNameField.getText();
        String taskDescription = this.newTaskDescriptionArea.getText();
        String taskPriority = this.priorityComboBox.getValue();
        
        Task newTask = new Task(taskName, taskDescription, taskPriority);
        
        this.taskListView.getItems().add(newTask);
        this.newTaskNameField.clear();
        this.newTaskDescriptionArea.clear();
        this.priorityComboBox.getSelectionModel().selectFirst();

    
    	
    }
    @FXML
    private void onUpdateDescriptionButtonClick(ActionEvent event) {
        Task selectedTask = this.taskListView.getSelectionModel().getSelectedItem();

        if (selectedTask != null) {
            String newDescription = this.displayDescriptionArea.getText();
            selectedTask.setDescription(newDescription);

           
            this.taskListView.refresh();
        }
    }
    
    @FXML
    private void onRemoveTaskButtonClick(ActionEvent event) {
        Task selectedTask = this.taskListView.getSelectionModel().getSelectedItem();
        
        if (selectedTask != null) {
            this.taskListView.getItems().remove(selectedTask);
        }
    }
    
    @FXML
    private void onCountTasksButtonClick(ActionEvent event) {
    	List<Task> allTasks = this.taskListView.getItems();

        int highCount = TaskUtils.countTasksByPriority("High", allTasks);
        this.highPriorityLabel.setText("High Priority: " + highCount);

        int mediumCount = TaskUtils.countTasksByPriority("Medium", allTasks);
        this.mediumPriorityLabel.setText("Medium Priority: " + mediumCount);

        int lowCount = TaskUtils.countTasksByPriority("Low", allTasks);
        this.lowPriorityLabel.setText("Low Priority: " + lowCount);
    }
    	
}
