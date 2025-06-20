package com.quiz.model;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class QuizSubmission {
    private String userId;
    private String quizId;
    private List<String> sectionIds;
    private Map<String, String> answers; // questionId -> selectedAnswer
    private int score;
    private int totalPossibleScore;


} 