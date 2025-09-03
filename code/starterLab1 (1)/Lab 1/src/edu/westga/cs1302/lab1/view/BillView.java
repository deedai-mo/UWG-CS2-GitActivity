package edu.westga.cs1302.lab1.view;

import edu.westga.cs1302.lab1.model.Bill;
import edu.westga.cs1302.lab1.model.BillItem;

/**
 * Handles formatting Bill objects for display.
 * 
 * @author CS 1302
 * @version Fall 2025
 */

public class BillView {

    /**
     * Returns a formatted text representation of a bill.
     * 
     * @param bill the bill to format
     * @return formatted string
     */
    public String getText(Bill bill) {
        if (bill == null) {
            throw new IllegalArgumentException("bill must not be null.");
        }

        StringBuilder text = new StringBuilder();
        text.append("ITEMS").append(System.lineSeparator());

        for (BillItem item : bill.getItems()) {
            text.append(item.getName())
                .append(" - $")
                .append(item.getAmount())
                .append(System.lineSeparator());
        }

        text.append(System.lineSeparator());
        text.append("SUBTOTAL - $").append(bill.getSubTotal()).append(System.lineSeparator());
        text.append("TAX - $").append(bill.getTax()).append(System.lineSeparator());
        text.append("TIP - $").append(bill.getTip()).append(System.lineSeparator());
        text.append("TOTAL - $").append(bill.getTotal());

        return text.toString();
    }
	
}
