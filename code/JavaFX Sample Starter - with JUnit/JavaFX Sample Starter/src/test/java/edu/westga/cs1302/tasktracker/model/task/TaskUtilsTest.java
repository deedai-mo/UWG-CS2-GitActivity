package edu.westga.cs1302.tasktracker.model.task;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.tasktracker.model.Task;
import edu.westga.cs1302.tasktracker.model.TaskUtils;

class TaskUtilsTest {
	@Test
	public void testCounts() {
		List<Task>tasks = new ArrayList<>();
		
		tasks.add(new Task("A","2 Liters","Low"));
		tasks.add(new Task("B", "Wash Car", "Medium"));
        tasks.add(new Task("C", "Do Laundry", "Medium"));
        tasks.add(new Task("D", "pay light bill", "High"));
        
        assertEquals(1, TaskUtils.countTasksByPriority("Low", tasks));
        assertEquals(2, TaskUtils.countTasksByPriority("Medium", tasks));
        assertEquals(1, TaskUtils.countTasksByPriority("High", tasks));
		
		
	}
	   @Test
	    public void countTasksByPriority_throws_on_nulls() {
	        assertThrows(IllegalArgumentException.class, () -> TaskUtils.countTasksByPriority(null, new ArrayList<>()));
	        assertThrows(IllegalArgumentException.class, () -> TaskUtils.countTasksByPriority("Low", null));
	    }


}
