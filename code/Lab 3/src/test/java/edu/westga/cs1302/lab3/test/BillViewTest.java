package edu.westga.cs1302.lab3.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.westga.cs1302.lab3.model.Bill;
import edu.westga.cs1302.lab3.model.BillItem;
import edu.westga.cs1302.lab3.views.BillView;


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
        assertTrue(output.contains("TAX - $1.5"));
        assertTrue(output.contains("TIP - $3.0"));
        assertTrue(output.contains("TOTAL - $19.5"));
    }

}
