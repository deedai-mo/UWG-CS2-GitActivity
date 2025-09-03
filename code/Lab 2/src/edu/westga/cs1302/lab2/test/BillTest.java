package edu.westga.cs1302.lab2.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import edu.westga.cs1302.lab2.model.Bill;
import edu.westga.cs1302.lab2.model.BillItem;

class BillTest {
	private Bill bill;

    @BeforeEach
    void setUp() {
        this.bill = new Bill();
    }

    @Test
    void testAddItemValid() {
        BillItem item = new BillItem("Pizza", 12.0);
        this.bill.addItem(item);
        assertTrue(this.bill.getItems().contains(item));
    }

    @Test
    void testAddNullItemThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.bill.addItem(null);
        });
    }

    @Test
    void testEmptyBillHasNoItems() {
        assertTrue(this.bill.getItems().isEmpty());
    }

}
