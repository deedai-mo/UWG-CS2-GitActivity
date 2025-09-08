package edu.westga.cs1302.lab3.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import edu.westga.cs1302.lab3.model.Bill;
import edu.westga.cs1302.lab3.model.BillItem;


class BillTest {
	private Bill bill;

    @BeforeEach
    void setUp() {
        this.bill = new Bill();
    }

    @Test
    void testNewBillHasNoItems() {
        assertEquals(0, this.bill.getItems().size());
    }

    @Test
    void testAddValidItem() {
        BillItem item = new BillItem("Burger", 10.0);
        this.bill.addItem(item);

        ArrayList<BillItem> items = this.bill.getItems();
        assertEquals(1, items.size());
        assertEquals("Burger", items.get(0).getName());
        assertEquals(10.0, items.get(0).getAmount());
    }

    @Test
    void testAddNullItemThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.bill.addItem(null);
        });
    }

}
