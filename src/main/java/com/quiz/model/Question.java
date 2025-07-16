package com.quiz.model;

import java.util.Map;

public class Question {
    private String id;
    private String questionText;
    private Map<String,String> options;
    private String correctAnswer;
    private String explanation;
    private int points = 1;

    // Constructor
    public Question() {}

    public Question(String id, String questionText, Map<String, String> options, 
                   String correctAnswer, String explanation, int points) {
        this.id = id;
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.explanation = explanation;
        this.points = points;
    }

    // Getters
    public String getId() { return id; }
    public String getQuestionText() { return questionText; }
    public Map<String, String> getOptions() { return options; }
    public String getCorrectAnswer() { return correctAnswer; }
    public String getExplanation() { return explanation; }
    public int getPoints() { return points; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }
    public void setOptions(Map<String, String> options) { this.options = options; }
    public void setCorrectAnswer(String correctAnswer) { this.correctAnswer = correctAnswer; }
    public void setExplanation(String explanation) { this.explanation = explanation; }
    public void setPoints(int points) { this.points = points; }

    // Builder pattern
    public static QuestionBuilder builder() {
        return new QuestionBuilder();
    }

    public static class QuestionBuilder {
        private String id;
        private String questionText;
        private Map<String,String> options;
        private String correctAnswer;
        private String explanation;
        private int points = 1;

        public QuestionBuilder id(String id) { this.id = id; return this; }
        public QuestionBuilder questionText(String questionText) { this.questionText = questionText; return this; }
        public QuestionBuilder options(Map<String,String> options) { this.options = options; return this; }
        public QuestionBuilder correctAnswer(String correctAnswer) { this.correctAnswer = correctAnswer; return this; }
        public QuestionBuilder explanation(String explanation) { this.explanation = explanation; return this; }
        public QuestionBuilder points(int points) { this.points = points; return this; }

        public Question build() {
            return new Question(id, questionText, options, correctAnswer, explanation, points);
        }
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", questionText='" + questionText + '\'' +
                ", options=" + options +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", explanation='" + explanation + '\'' +
                ", points=" + points +
                '}';
    }
}