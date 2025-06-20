package com.quiz.parser;

import com.quiz.model.Question;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class QuestionUtils {

    //    public static void loadQuestions() throws IOException {
//        // TODO : load Qs from markdown
//        QuestionParser parser = new QuestionParser();
//        String content = readMarkdownFile("src/main/resources/questions/part3.md");
//        List<String> list = Arrays.stream(content.split("##")).toList().stream().skip(1).toList();
//        List<Question> l = list.stream().map(s -> parser.parse(s.trim())).toList();
//
//    }

    public static Map<String, Map<String, List<Question>>> loadQuestions() throws IOException {
        Map<String, Map<String, List<Question>>> questions = new HashMap<>();
        Path basePath = Paths.get("src/main/resources/questions");

        Files.walk(basePath)
                .filter(Files::isRegularFile)
                .filter(path -> path.toString().endsWith(".md"))
                .forEach(path -> {
                    try {
                        String stackName = basePath.relativize(path.getParent()).getName(0).toString();
                        String sectionName = basePath.relativize(path.getParent()).getName(1).toString();
                        String content = Files.readString(path);
                        System.out.println("---"+sectionName);

                        List<String> questionBlocks = Arrays.stream(content.split("##"))
                                .skip(1)
                                .map(String::trim)
                                .toList();

                        List<Question> questionList = questionBlocks.stream()
                                .map(questionBlock -> parse(questionBlock, sectionName, stackName))
                                .toList();

                        questions.computeIfAbsent(stackName, k -> new HashMap<>())
                                .put(sectionName, questionList);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        //System.out.println("---->"+ questions);
        return questions;
    }

    public static Question parse(String questionMd, String sectionName, String stackName){
        String part1 = questionMd.split("<details><summary>Response:</summary>")[0].trim();

        List<String> l = Arrays.stream(part1.split("\n")).map(String::trim).filter(s -> !s.isBlank()).toList();
        System.out.println(l);


        String qId = l.get(0).replace("\s","").trim();
        System.out.println(String.join("-", stackName, sectionName, qId));
        String qContent = l.get(1).trim();
        System.out.println(qContent);
        Map<String,String> options = l.subList(2, l.size()).stream()
                .peek(s -> System.out.println("--->"+s))
                .collect(Collectors.toMap(s -> s.split("\\.")[0].replace("-", "").replace("*","").trim(), s -> s.split("\\.\s")[1]));
        System.out.println("options--->"+ options);
        String part2 = questionMd.split("<details><summary>Response:</summary>")[1].trim();


        Question q = new Question();
        q.setId(String.join("-", stackName, sectionName, qId));
        q.setQuestionText(qContent);
        q.setOptions(options);
        q.setCorrectAnswer(part2.trim().substring(part2.indexOf("**Answer:**")+ "**Answer:**".length(), part2.indexOf("**Explanation:**")).trim());
        q.setExplanation(part2.trim().substring(
                part2.trim().indexOf("**Explanation:**")+ "**Explanation:**".length(),
                part2.trim().indexOf("</details>")).trim()
        );
        System.out.println(q);
        return q;
    }

    public static String readMarkdownFile(String filePath) throws IOException {
        return Files.readString(Path.of(filePath));
    }


    //    public List<Question> getAllQuestions(String quiz, List<String> sections) {
    //        List<Question> list = questions.get(quiz).entrySet().stream()
    //                .filter(entry -> sections.contains(entry.getKey()))
    //                .flatMap(entry -> entry.getValue().stream())
    //                .toList();
    //        return list;
    //    }
}
