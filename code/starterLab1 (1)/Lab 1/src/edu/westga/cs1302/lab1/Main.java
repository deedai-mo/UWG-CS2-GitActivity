	package edu.westga.cs1302.lab1;

	import edu.westga.cs1302.lab1.model.Bill;
	import edu.westga.cs1302.lab1.model.BillItem;
	import edu.westga.cs1302.lab1.view.BillView;

	public class Main {
	    public static void main(String[] args) {
	        // Create a bill
	        Bill bill = new Bill();

	        // Add some items
	        bill.addItem(new BillItem("Burger", 8.50));
	        bill.addItem(new BillItem("Fries", 3.25));
	        bill.addItem(new BillItem("Soda", 1.75));

	        // Use BillView to display formatted text
	        BillView billView = new BillView();
	        System.out.println(billView.getText(bill));
	    }
	}
	


