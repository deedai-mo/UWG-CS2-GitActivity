package edu.westga.cs1302.lab3.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import edu.westga.cs1302.lab3.model.BillItem;

class BillItemTest {
	@Test
    void testValidBillItem() {
        BillItem item = new BillItem("Fries", 5.0);
        assertEquals("Fries", item.getName());
        assertEquals(5.0, item.getAmount());
    }

    @Test
    void testNullNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BillItem(null, 5.0);
        });
    }

    @Test
    void testNegativeAmountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BillItem("Fries", -3.0);
        });
    }

    @Test
    void testZeroAmountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BillItem("Fries", 0.0);
        });
    }



}
