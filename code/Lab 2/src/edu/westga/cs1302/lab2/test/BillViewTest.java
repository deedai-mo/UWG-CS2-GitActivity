package edu.westga.cs1302.lab2.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.lab2.model.Bill;
import edu.westga.cs1302.lab2.model.BillItem;
import edu.westga.cs1302.lab2.view.BillView;

/**
 * Unit tests for the BillView class.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
class BillViewTest {

    private Bill bill;
    private BillView view;

    @BeforeEach
    void setUp() {
        this.bill = new Bill();
        this.view = new BillView();
    }

    @Test
    void testGetTextEmptyBill() {
        String output = this.view.getText(this.bill);

        assertTrue(output.contains("ITEMS"));
        assertTrue(output.contains("SUBTOTAL - $0.0"));
        assertTrue(output.contains("TAX - $0.0"));
        assertTrue(output.contains("TIP - $0.0"));
        assertTrue(output.contains("TOTAL - $0.0"));
    }

    @Test
    void testGetTextWithItems() {
        this.bill.addItem(new BillItem("Burger", 10.0));
        this.bill.addItem(new BillItem("Fries", 5.0));

        String output = this.view.getText(this.bill);

        assertTrue(output.contains("Burger - 10.0"));
        assertTrue(output.contains("Fries - 5.0"));
        assertTrue(output.contains("SUBTOTAL - $15.0"));
        assertTrue(output.contains("TAX - $1.5"));   // 10% of 15
        assertTrue(output.contains("TIP - $3.0"));   // 20% of 15
        assertTrue(output.contains("TOTAL - $19.5")); // subtotal + tax + tip
    }

    @Test
    void testGetTextWithNullBill() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.view.getText(null);
        });
    }
}
