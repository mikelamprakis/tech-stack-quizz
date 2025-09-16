package com.quiz.model;

import java.util.List;

public class Category {
    private String id;
    private String name;
    private String quizId;
    private List<Section> sections;
    private boolean expanded;

    public Category() {}

    public Category(String id, String name, String quizId) {
        this.id = id;
        this.name = name;
        this.quizId = quizId;
        this.expanded = false;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
} 