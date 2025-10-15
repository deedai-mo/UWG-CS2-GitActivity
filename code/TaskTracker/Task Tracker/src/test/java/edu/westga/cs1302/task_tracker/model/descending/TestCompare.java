package edu.westga.cs1302.task_tracker.model.descending;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.task_tracker.model.Ascending;
import edu.westga.cs1302.task_tracker.model.Descending;
import edu.westga.cs1302.task_tracker.model.Task;
import edu.westga.cs1302.task_tracker.model.Task.TaskPriority;

class TestCompare {
	 // Test Tasks
    private Task lowPriorityTask;
    private Task mediumPriorityTask;
    private Task highPriorityTask;
    private Task lowPriorityTaskDuplicate; // Used for equality tests

    /**
     * Set up test tasks before each test run.
     */
    @BeforeEach
    public void setUp() {
        // Task constructor: name, description, priority
        this.lowPriorityTask = new Task("Wash Dishes", "Need to clean up after dinner", TaskPriority.LOW);
        this.mediumPriorityTask = new Task("Write Email", "Send update to professor", TaskPriority.MEDIUM);
        this.highPriorityTask = new Task("Study for Exam", "Review chapters 1-4 for CS exam", TaskPriority.HIGH);
        this.lowPriorityTaskDuplicate = new Task("Do Laundry", "Wash all clothes", TaskPriority.LOW);
    }

    // --- Tests for Ascending Comparator (LOW -> HIGH) ---

    /**
     * Tests that two tasks with the same priority compare as equal (return 0).
     */
    @Test
    public void testAscendingComparatorSamePriority() {
        Ascending comparator = new Ascending();
        // LOW vs LOW should be 0
        assertEquals(0, comparator.compare(this.lowPriorityTask, this.lowPriorityTaskDuplicate), 
            "Tasks with the same priority (LOW) should return 0 in ascending order.");
        // HIGH vs HIGH should be 0
        assertEquals(0, comparator.compare(this.highPriorityTask, new Task("Test H2", "", TaskPriority.HIGH)),
            "Tasks with the same priority (HIGH) should return 0 in ascending order.");
    }

    /**
     * Tests that a LOW priority task comes before a MEDIUM priority task.
     */
    @Test
    public void testAscendingComparatorLowVsMedium() {
        Ascending comparator = new Ascending();
        // LOW should be less than MEDIUM (negative result)
        assertTrue(comparator.compare(this.lowPriorityTask, this.mediumPriorityTask) < 0, 
            "LOW priority should come before MEDIUM priority in ascending order.");
    }

    /**
     * Tests that a MEDIUM priority task comes before a HIGH priority task.
     */
    @Test
    public void testAscendingComparatorMediumVsHigh() {
        Ascending comparator = new Ascending();
        // MEDIUM should be less than HIGH (negative result)
        assertTrue(comparator.compare(this.mediumPriorityTask, this.highPriorityTask) < 0, 
            "MEDIUM priority should come before HIGH priority in ascending order.");
    }

    /**
     * Tests the full range: LOW vs HIGH.
     */
    @Test
    public void testAscendingComparatorLowVsHigh() {
        Ascending comparator = new Ascending();
        // LOW should be much less than HIGH (negative result)
        assertTrue(comparator.compare(this.lowPriorityTask, this.highPriorityTask) < 0, 
            "LOW priority should come before HIGH priority in ascending order.");
    }

    /**
     * Tests the reverse order: HIGH vs LOW (should be positive).
     */
    @Test
    public void testAscendingComparatorHighVsLow() {
        Ascending comparator = new Ascending();
        // HIGH should be greater than LOW (positive result)
        assertTrue(comparator.compare(this.highPriorityTask, this.lowPriorityTask) > 0, 
            "HIGH priority should come after LOW priority in ascending order.");
    }

    // --- Tests for Descending Comparator (HIGH -> LOW) ---

    /**
     * Tests that two tasks with the same priority compare as equal (return 0).
     */
    @Test
    public void testDescendingComparatorSamePriority() {
        Descending comparator = new Descending();
        // LOW vs LOW should be 0
        assertEquals(0, comparator.compare(this.lowPriorityTask, this.lowPriorityTaskDuplicate), 
            "Tasks with the same priority (LOW) should return 0 in descending order.");
        // MEDIUM vs MEDIUM should be 0
        assertEquals(0, comparator.compare(this.mediumPriorityTask, new Task("Test M2", "", TaskPriority.MEDIUM)), 
            "Tasks with the same priority (MEDIUM) should return 0 in descending order.");
    }

}
