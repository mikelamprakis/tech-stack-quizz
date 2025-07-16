package com.quiz.model;

public class Section {
    private String id;
    private String name;
    private String quizId;
    private String difficulty;

    // Constructor
    public Section() {}

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getQuizId() { return quizId; }
    public String getDifficulty() { return difficulty; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setQuizId(String quizId) { this.quizId = quizId; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    @Override
    public String toString() {
        return "Section{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", quizId='" + quizId + '\'' +
                ", difficulty='" + difficulty + '\'' +
                '}';
    }
}