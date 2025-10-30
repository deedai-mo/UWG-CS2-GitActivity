package edu.westga.cs1302.task_tracker.model.task;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.task_tracker.model.NameAscending;
import edu.westga.cs1302.task_tracker.model.NameDescending;
import edu.westga.cs1302.task_tracker.model.Task;
import edu.westga.cs1302.task_tracker.model.Task.TaskPriority;

public class NameComparatorTest {

	private Task taskA = new Task("Alpha Task", "a", TaskPriority.HIGH);
	private Task taskB = new Task("Beta Task", "b", TaskPriority.MEDIUM);
	private Task taskC = new Task("Alpha Task", "c", TaskPriority.LOW); // Same name as A

	@Test
	void testNameAscendingCompareShouldSortAlphabetically() {
		NameAscending comparator = new NameAscending();

		// Alpha (A) comes before Beta (B) -> Negative result
		assertTrue(comparator.compare(this.taskA, this.taskB) < 0, "Alpha should be < Beta (A before B)");

		// Beta (B) comes after Alpha (A) -> Positive result
		assertTrue(comparator.compare(this.taskB, this.taskA) > 0, "Beta should be > Alpha (B after A)");

		// Alpha (A) compared to Alpha (C) -> Zero result
		assertEquals(0, comparator.compare(this.taskA, this.taskC), "Same name should be equal (0)");
	}

	@Test
	void testNameDescendingCompareShouldSortReverseAlphabetically() {
		NameDescending comparator = new NameDescending();

		// Alpha (A) should come after Beta (B) in descending order -> Positive result
		assertTrue(comparator.compare(this.taskA, this.taskB) > 0, "Alpha should be > Beta (A after B)");

		// Beta (B) should come before Alpha (A) in descending order -> Negative result
		assertTrue(comparator.compare(this.taskB, this.taskA) < 0, "Beta should be < Alpha (B before A)");

		// Alpha (A) compared to Alpha (C) -> Zero result
		assertEquals(0, comparator.compare(this.taskA, this.taskC), "Same name should be equal (0)");
	}

}
