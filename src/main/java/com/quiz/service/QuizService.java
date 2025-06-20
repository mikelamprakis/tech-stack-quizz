package com.quiz.service;

import com.quiz.model.Question;
import com.quiz.model.Quiz;
import com.quiz.model.QuizSubmission;
import com.quiz.model.Section;
import com.quiz.parser.QuestionUtils;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class QuizService {

    private Map<String, Map<String, List<Question>>> questions;

    public QuizService() throws IOException {
        this.questions = loadQuestions();
    }

    protected Map<String, Map<String, List<Question>>> loadQuestions() throws IOException {
        return QuestionUtils.loadQuestions();
    }

    public List<Quiz> getAllQuizzes() {
        System.out.println(questions);
        return questions.keySet().stream()
            .map(quizId -> {
                Quiz quiz = new Quiz();
                quiz.setId(quizId);
                quiz.setName(formatQuizName(quizId));
                quiz.setDescription("Questions about " + formatQuizName(quizId));
                return quiz;
            })
            .toList();
    }

    public List<Section> getSectionsForQuiz(String quizId) {
        Map<String, List<Question>> sections = questions.get(quizId);
        if (sections == null) {
            return new ArrayList<>();
        }

        return sections.keySet().stream()
            .map(sectionId -> {
                Section section = new Section();
                section.setId(sectionId);
                section.setName(formatSectionName(sectionId));
                section.setQuizId(quizId);
                section.setDifficulty(determineDifficulty(sectionId));
                return section;
            })
            .toList();
    }

    public List<Question> getQuestionsForSections(String quizId, List<String> sectionIds) {
        List<Question> filteredQuestions = new ArrayList<>();
        Map<String, List<Question>> quizQuestions = questions.get(quizId);
        
        if (quizQuestions != null) {
            for (String sectionId : sectionIds) {
                List<Question> sectionQuestions = quizQuestions.get(sectionId);
                if (sectionQuestions != null) {
                    filteredQuestions.addAll(sectionQuestions);
                }
            }
        }

        System.out.println("filteredQuestions---->"+ filteredQuestions);
        
        return filteredQuestions;
    }

    public QuizSubmission submitQuiz(QuizSubmission submission) {
        AtomicInteger score = new AtomicInteger();
        AtomicInteger totalPossibleScore = new AtomicInteger();

        List<Question> allQuestions = getQuestionsForSections(
            submission.getQuizId(), 
            submission.getSectionIds()
        );

        submission.getAnswers().forEach((qId, answer) -> {
            Question question = allQuestions.stream()
                    .filter(q -> q.getId().equals(qId)).findFirst()
                    .orElse(null);

            if (question != null) {
                totalPossibleScore.addAndGet(question.getPoints());
                System.out.println("-answer"+ answer + "-getCorrectAnswer" +question.getCorrectAnswer() );
                if (answer.equals(question.getCorrectAnswer())) {
                    score.addAndGet(question.getPoints());
                }
            }
        });

        submission.setTotalPossibleScore(totalPossibleScore.get());
        submission.setScore(score.get());
        System.out.println("---final submision "+ submission);
        return submission;
    }

    private String formatQuizName(String quizId) {
        // Convert quiz ID to a more readable format
        // e.g., "java-basics" -> "Java Basics"
        return Arrays.stream(quizId.split("-"))
            .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
            .collect(Collectors.joining(" "));
    }

    private String formatSectionName(String sectionId) {
        // Convert section ID to a more readable format
        // e.g., "variables-and-data-types" -> "Variables and Data Types"
        return Arrays.stream(sectionId.split("-"))
            .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
            .collect(Collectors.joining(" "));
    }

    private String determineDifficulty(String sectionId) {
        // You can implement your own logic to determine difficulty
        // For now, we'll return a default value
        return "medium";
    }
}