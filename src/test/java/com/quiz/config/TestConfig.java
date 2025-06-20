//package com.quiz.config;
//
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Primary;
//import com.quiz.service.QuizService;
//import com.quiz.parser.QuestionUtils;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import com.quiz.model.Question;
//
//@TestConfiguration
//public class TestConfig {
//
//    @Bean
//    @Primary
//    public QuizService quizService() throws IOException {
//        return new QuizService() {
//            @Override
//            protected Map<String, Map<String, List<Question>>> loadQuestions() {
//                // Create test data
//                Map<String, Map<String, List<Question>>> testData = new HashMap<>();
//
//                // Create a test quiz
//                Map<String, List<Question>> sections = new HashMap<>();
//
//                // Create a test section with questions
//                Question question1 = new Question();
//                question1.setId("q1");
//                question1.setQuestionText("What is 2 + 2?");
//                //question1.setOptions(List.of("3", "4", "5", "6"));
//                question1.setCorrectAnswer("4");
//                question1.setPoints(1);
//
//                Question question2 = new Question();
//                question2.setId("q2");
//                question2.setQuestionText("What is the capital of France?");
//                question2.setOptions(List.of("London", "Berlin", "Paris", "Madrid"));
//                question2.setCorrectAnswer("Paris");
//                question2.setPoints(1);
//
//                sections.put("math", List.of(question1));
//                sections.put("geography", List.of(question2));
//
//                testData.put("test-quiz", sections);
//
//                return testData;
//            }
//        };
//    }
//}