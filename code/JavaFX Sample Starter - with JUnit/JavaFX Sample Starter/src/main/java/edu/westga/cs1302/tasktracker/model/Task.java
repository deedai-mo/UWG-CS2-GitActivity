package edu.westga.cs1302.tasktracker.model;

public class Task {
	private final String name;
	private  String description;
	private final String priority;
	
	public Task(String name, String description, String priority ) {
		if(name.isEmpty()|| name == null) {
			throw new IllegalArgumentException("Invalid Input");
		}
		if(priority == null) {
			throw new IllegalArgumentException("Input priority");
		}
		this.name=name;
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
	
	@Override
	public String toString() {
		return this.name;
	}

	public void setDescription(String newDescription) {
		// TODO Auto-generated method stub
		this.description = newDescription;
		
	}
	
	

}
