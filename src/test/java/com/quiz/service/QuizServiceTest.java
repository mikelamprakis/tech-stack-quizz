package com.quiz.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class QuizServiceTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllQuestions() throws IOException {
         new QuizService();
    }

    public static boolean isValidMarkdown(String markdown) {
        String regex = "##\\s*(Question\\s*\\d+)\\n\\n(.*?)\\n\\n((?:-\\s*.*\\n)+).*?\\*\\*Answer:\\*\\*\\s*(\\w)\\n\\n\\*\\*Explanation:\\*\\*\\s*(.*?)\\n\\n";
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(markdown);
        return matcher.matches();
    }


    public static void main(String[] args) {
        String markdown = """
        ## Question 1

        Which tool is commonly used to monitor Kafka cluster health and performance?

        - A. Nagios
        - B. Prometheus
        - C. Elasticsearch
        - D. Splunk

        <details><summary>Response:</summary> 

        **Answer:** B

        **Explanation:**
        Prometheus is widely used for monitoring Kafka cluster health and performance. It collects metrics from Kafka brokers, producers, and consumers, and stores them in a time-series database. Prometheus can be used with Grafana for visualizing these metrics.

        - A, C, and D are incorrect because while Nagios, Elasticsearch, and Splunk can be used for monitoring and logging, Prometheus is more specialized for metrics collection and monitoring.

        </details>
        """;

        String regex = "##\\s*(Question\\s*\\d+)\\n\\n(.*?)\\n\\n((?:-\\s*.*\\n)+).*?\\*\\*Answer:\\*\\*\\s*(\\w)\\n\\n\\*\\*Explanation:\\*\\*\\s*(.*?)\\n\\n";
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(markdown);

        while (matcher.find()) {
            String questionNumber = matcher.group(1).trim();
            String questionContent = matcher.group(2).trim();
            String options = matcher.group(3).trim();
            String correctAnswer = matcher.group(4).trim();
            String explanation = matcher.group(5).trim();

            System.out.println("Question Number: " + questionNumber);
            System.out.println("Question Content: " + questionContent);
            System.out.println("Options: " + options);
            System.out.println("Correct Answer: " + correctAnswer);
            System.out.println("Explanation: " + explanation);
            System.out.println("-----------------------------");
        }
    }
}