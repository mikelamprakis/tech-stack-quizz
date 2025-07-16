package com.quiz.model;

import java.util.List;
import java.util.Map;

public class QuizSubmission {
    private String userId;
    private String quizId;
    private List<String> sectionIds;
    private Map<String, String> answers; // questionId -> selectedAnswer
    private int score;
    private int totalPossibleScore;

    // Constructor
    public QuizSubmission() {}

    // Getters
    public String getUserId() { return userId; }
    public String getQuizId() { return quizId; }
    public List<String> getSectionIds() { return sectionIds; }
    public Map<String, String> getAnswers() { return answers; }
    public int getScore() { return score; }
    public int getTotalPossibleScore() { return totalPossibleScore; }

    // Setters
    public void setUserId(String userId) { this.userId = userId; }
    public void setQuizId(String quizId) { this.quizId = quizId; }
    public void setSectionIds(List<String> sectionIds) { this.sectionIds = sectionIds; }
    public void setAnswers(Map<String, String> answers) { this.answers = answers; }
    public void setScore(int score) { this.score = score; }
    public void setTotalPossibleScore(int totalPossibleScore) { this.totalPossibleScore = totalPossibleScore; }

    @Override
    public String toString() {
        return "QuizSubmission{" +
                "userId='" + userId + '\'' +
                ", quizId='" + quizId + '\'' +
                ", sectionIds=" + sectionIds +
                ", answers=" + answers +
                ", score=" + score +
                ", totalPossibleScore=" + totalPossibleScore +
                '}';
    }
}