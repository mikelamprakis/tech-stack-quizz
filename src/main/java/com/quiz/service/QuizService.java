package com.quiz.service;

import com.quiz.model.Question;
import com.quiz.model.Quiz;
import com.quiz.model.QuizSubmission;
import com.quiz.model.Section;
import com.quiz.model.Category;
import com.quiz.parser.QuestionUtils;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class QuizService {

    private Map<String, Map<String, List<Question>>> questions;

    public QuizService() throws IOException, URISyntaxException {
        this.questions = loadQuestions();
    }

    protected Map<String, Map<String, List<Question>>> loadQuestions() throws IOException, URISyntaxException {
        return QuestionUtils.loadQuestions();
    }

    public List<Quiz> getAllQuizzes() {
        //System.out.println(questions);
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

    public List<Category> getCategoriesForQuiz(String quizId) {
        Map<String, List<Question>> sections = questions.get(quizId);

        if (sections == null) {
            return new ArrayList<>();
        }

        // Get unique category IDs from existing sections
        Set<String> categoryIds = sections.keySet().stream()
            .map(this::extractCategoryId)
            .collect(Collectors.toSet());

        List<Category> categories = new ArrayList<>();
        
        for (String categoryId : categoryIds) {
            System.out.println(categoryId);
            Category category = new Category();
            category.setId(categoryId);
            category.setName(formatCategoryName(categoryId));
            category.setQuizId(quizId);
            
            // Scan for markdown files in this category directory
            List<Section> subcategories = scanCategoryForSubcategories(quizId, categoryId);
            category.setSections(subcategories);
            
            categories.add(category);
        }

        return categories.stream()
            .sorted(Comparator.comparing(category -> Integer.parseInt(category.getId().split("\\.")[0].trim())))
            .toList();
    }

    private List<Section> scanCategoryForSubcategories(String quizId, String categoryId) {
        List<Section> subcategories = new ArrayList<>();
        
        try {
            java.nio.file.Path categoryPath = java.nio.file.Paths.get("src/main/resources/questions", quizId, categoryId);
            
            if (java.nio.file.Files.exists(categoryPath) && java.nio.file.Files.isDirectory(categoryPath)) {
                java.nio.file.Files.list(categoryPath)
                    .filter(path -> path.toString().endsWith(".md"))
                    .sorted()
                    .forEach(mdFile -> {
                        String fileName = mdFile.getFileName().toString();
                        String subcategoryId = fileName.replace(".md", "");
                        String subcategoryName = formatSubcategoryName(subcategoryId);
                        
                        Section subcategory = new Section();
                        subcategory.setId(categoryId + "-" + subcategoryId);
                        subcategory.setName(subcategoryName);
                        subcategory.setQuizId(quizId);
                        subcategory.setDifficulty(determineDifficulty(subcategoryId));
                        
                        subcategories.add(subcategory);
                    });
            }
        } catch (Exception e) {
            System.err.println("Error scanning category " + categoryId + ": " + e.getMessage());
        }
        
        return subcategories.stream()
                .sorted(Comparator.comparing(section -> {
                    System.out.println("-"+section.getName());
                    return Integer.parseInt(section.getName().split("\\.")[0].trim());
                }))
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
                //System.out.println(">>>"+section);
                return section;
            })
            .toList();
    }

    public List<Question> getQuestionsForSections(String quizId, List<String> sectionIds) {
        List<Question> filteredQuestions = new ArrayList<>();
        Map<String, List<Question>> quizQuestions = questions.get(quizId);
        //System.out.println("--->"+sectionIds+"---"+quizQuestions);
        if (quizQuestions != null) {
            for (String sectionId : sectionIds) {
                List<Question> sectionQuestions = quizQuestions.get(sectionId);
                if (sectionQuestions != null) {
                    filteredQuestions.addAll(sectionQuestions);
                }
            }
        }

        //System.out.println("filteredQuestions---->"+ filteredQuestions);
        
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
                
                // Normalize answers for comparison - remove spaces around commas
                String normalizedUserAnswer = answer.replaceAll("\\s*,\\s*", ",").trim();
                String normalizedCorrectAnswer = question.getCorrectAnswer().replaceAll("\\s*,\\s*", ",").trim();
                System.out.println(normalizedUserAnswer+" -vs-"+normalizedCorrectAnswer);
                if (normalizedUserAnswer.equals(normalizedCorrectAnswer)) {
                    score.addAndGet(question.getPoints());
                }
            }
        });

        submission.setTotalPossibleScore(totalPossibleScore.get());
        submission.setScore(score.get());
        System.out.println("---final submision "+ submission);
        return submission;
    }

    private String extractCategoryId(String sectionId) {
        // Extract the main category from section ID
        // e.g., "1.broker-1-Broker Configurations" -> "1.broker"
        String[] parts = sectionId.split("-", 2);
        return parts[0];
    }

    private String formatQuizName(String quizId) {
        // Convert quiz ID to a more readable format
        // e.g., "java-basics" -> "Java Basics"
        return Arrays.stream(quizId.split("-"))
            .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
            .collect(Collectors.joining(" "));
    }

    private String formatCategoryName(String categoryId) {
        // Convert category ID to a more readable format
        // e.g., "1.broker" -> "1. Broker"
        String[] parts = categoryId.split("\\.");
        if (parts.length >= 2) {
            return parts[0] + ". " + Arrays.stream(parts[1].split("-"))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
                .collect(Collectors.joining(" "));
        }
        return categoryId;
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

    private String formatSubcategoryName(String subcategoryId) {
        // Convert subcategory ID to readable name
        // e.g., "1-Broker Configurations" -> "1. Broker Configurations"
        if (subcategoryId.contains("-")) {
            String[] parts = subcategoryId.split("-", 2);
            if (parts.length >= 2) {
                return parts[0] + ". " + parts[1].replace("-", " ");
            }
        }
        return subcategoryId.replace("-", " ");
    }
}