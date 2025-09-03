package edu.westga.cs1302.lab2.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import edu.westga.cs1302.lab2.model.*;

class BillItemTest {

	@Test
    void testConstructorValid() {
        BillItem item = new BillItem("Burger", 10.0);
        assertEquals("Burger", item.getName());
        assertEquals(10.0, item.getAmount(), 0.001);
    }

    @Test
    void testConstructorNullNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BillItem(null, 10.0);
        });
    }

    @Test
    void testConstructorNegativeAmountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BillItem("Burger", -5.0);
        });
    }

    @Test
    void testGetName() {
        BillItem item = new BillItem("Fries", 3.5);
        assertEquals("Fries", item.getName());
    }

    @Test
    void testGetAmount() {
        BillItem item = new BillItem("Soda", 2.0);
        assertEquals(2.0, item.getAmount(), 0.001);
    }
}
