package edu.westga.cs1302.tasktracker.model;

public class Task {
	private final String name;
    private String description;
    private final Priority priority;

    public Task(String name, String description, Priority priority) {
        if (name == null || name.strip().isEmpty()) {
            throw new IllegalArgumentException("Name required.");
        }
        if (priority == null) {
            throw new IllegalArgumentException("Priority required.");
        }
        this.name = name;
        this.description = description == null ? "" : description;
        this.priority = priority;
    }

    public String getName() { return this.name; }
    public String getDescription() { return this.description; }
    public Priority getPriority() { return this.priority; }

    public void setDescription(String newDescription) {
        this.description = newDescription == null ? "" : newDescription;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
