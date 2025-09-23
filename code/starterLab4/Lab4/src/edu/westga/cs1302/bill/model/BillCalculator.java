package edu.westga.cs1302.bill.model;

public class BillCalculator {
	public BillCalculator() {
		
	}
	
	/**
     * Calculate subtotal (sum of price * quantity for each item).
     */
    public static double calculateSubtotal(BillItem[] items) {
        checkNotNull(items);
        double subtotal = 0.0;
        for (BillItem item : items) {
            subtotal += item.getAmount() ;
        }
        return subtotal;
    }

    /**
     * Calculate tax on subtotal.
     * @param items bill items (not null)
     * @param taxRate tax rate as decimal (e.g., 0.07 for 7%)
     */
    public static double calculateTax(BillItem[] items, double taxRate) {
        return calculateSubtotal(items) * taxRate;
    }

    /**
     * Calculate tip on subtotal.
     * @param items bill items (not null)
     * @param tipRate tip rate as decimal (e.g., 0.15 for 15%)
     */
    public static double calculateTip(BillItem[] items, double tipRate) {
        return calculateSubtotal(items) * tipRate;
    }

    /**
     * Calculate total (subtotal + tax + tip).
     */
    public static double calculateTotal(BillItem[] items, double taxRate, double tipRate) {
        double subtotal = calculateSubtotal(items);
        return subtotal + (subtotal * taxRate) + (subtotal * tipRate);
    }

    // Helper to enforce precondition
    private static void checkNotNull(BillItem[] items) {
        if (items == null) {
            throw new IllegalArgumentException("Bill items array cannot be null.");
        }
        for (BillItem item : items) {
            if (item == null) {
                throw new IllegalArgumentException("Bill items array cannot contain null elements.");
            }
        }
    }
}
