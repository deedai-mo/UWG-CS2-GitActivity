package edu.westga.cs1302.tasktracker;

public class Task {
	private final String name;
	private String description;
	private final String priority;
	public Task(String name,String description, String priority) {
		 if (name == null || name.strip().isEmpty()) {
	            throw new IllegalArgumentException("Name must be provided.");
	        }
	        if (priority == null) {
	        	 throw new IllegalArgumentException("Priority must be provided.");
	        }
	        this.name = name;
	        this.description = description;
	        this.priority = priority;
		
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
		
	}
	
	public String getPriority() {
		return this.priority;
	}
	//public String setDescription(String newDescription) {
	  //  newDescription = this.description;
	   // return newDescription;
	//}
	@Override 
	public String toString() {
		return this.name;
		
	}

	public void setDescription(String newDescription) {
		// TODO Auto-generated method stub
		newDescription = this.description;
	}

}
