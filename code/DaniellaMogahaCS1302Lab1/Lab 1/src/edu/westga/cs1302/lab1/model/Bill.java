package edu.westga.cs1302.lab1.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores information for a bill.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class Bill {

    private static final double TAX_RATE = 0.10;
    private static final double TIP_RATE = 0.20;

    private ArrayList<BillItem> items;

    /**
     * Create a new empty Bill.
     */
    public Bill() {
        this.items = new ArrayList<BillItem>();
    }

    /**
     * Adds the item to the bill.
     * 
     * @precondition item != null
     * @postcondition item is added to the list of items in the bill
     * 
     * @param item the item to be added to the bill
     */
    public void addItem(BillItem item) {
        if (item == null) {
            throw new IllegalArgumentException("item must not be null.");
        }
        this.items.add(item);
    }

    /**
     * Gets an unmodifiable list of items in the bill.
     * 
     * @return the items
     */
    public List<BillItem> getItems() {
        return new ArrayList<>(this.items);
    }

    /**
     * Calculates the subtotal of all items.
     * 
     * @return the subtotal
     */
    public double getSubTotal() {
        double subTotal = 0.0;
        for (BillItem item : this.items) {
            subTotal += item.getAmount();
        }
        return subTotal;
    }

    /**
     * Calculates the tax for the bill.
     * 
     * @return the tax
     */
    public double getTax() {
        return this.getSubTotal() * TAX_RATE;
    }

    /**
     * Calculates the tip for the bill.
     * 
     * @return the tip
     */
    public double getTip() {
        return this.getSubTotal() * TIP_RATE;
    }

    /**
     * Calculates the total (subtotal + tax + tip).
     * 
     * @return the total
     */
    public double getTotal() {
        return this.getSubTotal() + this.getTax() + this.getTip();
    }
}