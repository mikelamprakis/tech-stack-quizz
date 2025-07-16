package com.quiz.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Import(TestConfig.class)
@ActiveProfiles("test")
public class QuizControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void testGetAllQuizzes() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/api/quizzes")
                .peek().prettyPeek()
        .then()
            .statusCode(200)
            .body("$", not(empty()))
            .body("[0].id", equalTo("kafka"))
            .body("[0].name", equalTo("Kafka"))
            .body("[0].description", equalTo("Questions about Kafka"))
            .body("[1].id", equalTo("aws"))
            .body("[1].name", equalTo("Aws"))
            .body("[1].description", equalTo("Questions about Aws"));;
    }

    @Test
    void testGetSectionsForQuiz() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/api/quizzes/kafka/sections")
        .then()
            .statusCode(200)
            .body("$", hasSize(2))
            .body("id", containsInAnyOrder("section1", "section2"))
            .body("name", containsInAnyOrder("Section1", "Section2"))
            .body("quizId", everyItem(equalTo("kafka")))
            .body("difficulty", everyItem(notNullValue()));
    }

    @Test
    void testGetQuestionsForSections() {
        given()
            .contentType(ContentType.JSON)
            .queryParam("sectionIds", List.of("math", "geography"))
        .when()
            .get("/api/quizzes/test-quiz/questions")
        .then()
            .statusCode(200)
            .body("$", hasSize(2))
            .body("id", containsInAnyOrder("q1", "q2"))
            .body("questionText", containsInAnyOrder("What is 2 + 2?", "What is the capital of France?"))
            .body("options", everyItem(notNullValue()))
            .body("correctAnswer", containsInAnyOrder("4", "Paris"))
            .body("points", everyItem(equalTo(1)));
    }

    @Test
    void testSubmitQuiz() {
        Map<String, Object> submission = Map.of(
            "quizId", "test-quiz",
            "sectionIds", List.of("math", "geography"),
            "answers", Map.of(
                "q1", "4",
                "q2", "Paris"
            )
        );

        given()
            .contentType(ContentType.JSON)
            .body(submission)
        .when()
            .post("/api/submit")
        .then()
            .statusCode(200)
            .body("quizId", equalTo("test-quiz"))
            .body("sectionIds", equalTo(List.of("math", "geography")))
            .body("score", equalTo(2))
            .body("totalPossibleScore", equalTo(2));
    }

    @Test
    void testSubmitQuizWithWrongAnswers() {
        Map<String, Object> submission = Map.of(
            "quizId", "test-quiz",
            "sectionIds", List.of("math", "geography"),
            "answers", Map.of(
                "q1", "3",
                "q2", "London"
            )
        );

        given()
            .contentType(ContentType.JSON)
            .body(submission)
        .when()
            .post("/api/submit")
        .then()
            .statusCode(200)
            .body("quizId", equalTo("test-quiz"))
            .body("sectionIds", equalTo(List.of("math", "geography")))
            .body("score", equalTo(0))
            .body("totalPossibleScore", equalTo(2));
    }

    @Test
    void testInvalidQuizId() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/api/quizzes/invalid-id/sections")
        .then()
            .statusCode(200)
            .body("$", empty());
    }
}