package edu.westga.cs1302.lab3.views;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import edu.westga.cs1302.lab3.model.Bill;
import edu.westga.cs1302.lab3.model.BillItem;
/**
 * Controller class for drawing various things to our canvas window.
 * 
 * @author CS 1302
 * @version Fall 2025
 */

public class MainWindow {

    @FXML
    private TextField amount;

    @FXML
    private TextField name;

    @FXML
    private TextArea output;
    
    private Bill bill;
    private BillView billView;

    @FXML
    void addItem(ActionEvent event) {
    	String itemName = this.name.getText().trim();
        String amountText = this.amount.getText().trim();

        // Basic input validation
        if (itemName.isEmpty() || amountText.isEmpty()) {
            this.output.setText("⚠ Please enter both a name and an amount.");
            return;
        }

        try {
            double value = Double.parseDouble(amountText);

            // Create and add new BillItem
            BillItem item = new BillItem(itemName, value);
            this.bill.addItem(item);

            // Update display
            String billText = this.billView.getText(this.bill);
            this.output.setText(billText);

            // Clear input fields for next entry
            this.name.clear();
            this.amount.clear();

        } catch (NumberFormatException e){
            this.output.setText("Invalid amount. Please enter a valid number.");
        } catch (IllegalArgumentException e){
            this.output.setText(e.getMessage());
        }
    }
    /**
     * Initializes the controller after the FXML is loaded.
     *
     * @precondition none
     * @postcondition bill and billView are ready for use
     */
   
    public void initialize() {
    	this.bill = new Bill();
        this.billView = new BillView();
    	
    }
}
