package com.quiz.controller;

import com.quiz.model.Question;
import com.quiz.model.Quiz;
import com.quiz.model.QuizSubmission;
import com.quiz.model.Section;
import com.quiz.model.Category;
import com.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class QuizController {
    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/quizzes")
    public List<Quiz> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @GetMapping("/test")
    public String test() {
        return "Test endpoint working";
    }

    @GetMapping("/quizzes/{quizId}/categories")
    public List<Category> getCategoriesForQuiz(@PathVariable String quizId) {
        System.out.println("DEBUG: getCategoriesForQuiz called with quizId: " + quizId);
        List<Category> categories = quizService.getCategoriesForQuiz(quizId);
        System.out.println("DEBUG: Returning " + categories.size() + " categories");
        categories.forEach(category -> System.out.println("---->>>"+category.getName()));
        return categories;
    }

    @GetMapping("/quizzes/{quizId}/sections")
    public List<Section> getSectionsForQuiz(@PathVariable String quizId) {
        return quizService.getSectionsForQuiz(quizId);
    }

    @GetMapping("/quizzes/{quizId}/questions")
    public List<Question> getQuestionsForSections(
            @PathVariable String quizId,
            @RequestParam List<String> sectionIds) {
        return quizService.getQuestionsForSections(quizId, sectionIds);
    }

//    @GetMapping("/stack")
//    public List<String> getStackList() {
//        return quizService.getStackList();
//    }
//
//    @RequestMapping("/stack/{stack}")
//    public List<String> getSections(String stack) {
//        return quizService.getSections(stack);
//    }

    @PostMapping("/submit")
    public QuizSubmission submitQuiz(@RequestBody QuizSubmission submission) {
        //System.out.println("---"+submission);
        return quizService.submitQuiz(submission);
    }
} 