package com.quiz.model;

public class Quiz {
    private String id;
    private String name;
    private String description;

    // Constructor
    public Quiz() {}

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return "Quiz{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}