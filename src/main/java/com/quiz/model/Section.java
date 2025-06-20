package com.quiz.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Section {
    private String id;
    private String name;
    private String quizId;
    private String difficulty;
} 