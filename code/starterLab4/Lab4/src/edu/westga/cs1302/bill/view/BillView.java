package edu.westga.cs1302.bill.view;

import edu.westga.cs1302.bill.model.Bill;
import edu.westga.cs1302.bill.model.BillCalculator;
import edu.westga.cs1302.bill.model.BillItem;

/**
 * Supports displaying the information contained in a Bill.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class BillView {

    /**
     * Return a String containing the list of bill items and totals for the bill.
     * 
     * @precondition bill != null
     * @postcondition none
     * 
     * @param bill the bill to be viewed
     * @return a String containing the list of bill items and totals for the bill
     */
    public static String getText(Bill bill) {
    	 if (bill == null) {
             throw new NullPointerException("Bill must not be null.");
         }

         StringBuilder builder = new StringBuilder();

         // Header
         builder.append("ITEMS").append(System.lineSeparator());

         // Items list
         for (BillItem item : bill.getItems()) {
             builder.append(item.getName())
                    .append(" - ")
                    .append(String.format("%.1f", item.getAmount())) // force one decimal place
                    .append(System.lineSeparator());
         }

         builder.append(System.lineSeparator());

         // Convert list to array for BillCalculator
         BillItem[] itemsArray = bill.getItems().toArray(new BillItem[0]);

         double subtotal = BillCalculator.calculateSubtotal(itemsArray);
         double tax = BillCalculator.calculateTax(itemsArray, Bill.TAX_RATE);
         double tip = BillCalculator.calculateTip(itemsArray, Bill.TIP_RATE);
         double total = BillCalculator.calculateTotal(itemsArray, Bill.TAX_RATE, Bill.TIP_RATE);

         // Totals
         builder.append("SUBTOTAL - $").append(String.format("%.1f", subtotal)).append(System.lineSeparator());
         builder.append("TAX - $").append(String.format("%.1f", tax)).append(System.lineSeparator());
         builder.append("TIP - $").append(String.format("%.1f", tip)).append(System.lineSeparator());
         builder.append("TOTAL - $").append(String.format("%.1f", total));

         return builder.toString();
     }
}