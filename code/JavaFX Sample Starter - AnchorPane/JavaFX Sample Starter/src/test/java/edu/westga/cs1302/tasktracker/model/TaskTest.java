package edu.westga.cs1302.tasktracker.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskTest {

    @Test
    public void constructor_and_getters_work() {
        Task t = new Task("Buy milk", "2 liters", Priority.LOW);
        assertEquals("Buy milk", t.getName());
        assertEquals("2 liters", t.getDescription());
        assertEquals(Priority.LOW, t.getPriority());
    }

    @Test
    public void setDescription_changes_description() {
        Task t = new Task("A", "", Priority.MEDIUM);
        t.setDescription("New desc");
        assertEquals("New desc", t.getDescription());
    }

    @Test
    public void constructor_throws_on_invalid_name() {
        assertThrows(IllegalArgumentException.class, () -> new Task(null, "x", Priority.HIGH));
        assertThrows(IllegalArgumentException.class, () -> new Task("   ", "x", Priority.HIGH));
    }

    @Test
    public void constructor_throws_on_null_priority() {
        assertThrows(IllegalArgumentException.class, () -> new Task("Name", "desc", null));
    }

	

}
