package com.quiz.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@ToString
@Getter
@Setter
public class Question {
    private String id;
    private String questionText;
    private Map<String,String> options;
    private String correctAnswer;
    private String explanation;
    private int points = 1 ;
} 