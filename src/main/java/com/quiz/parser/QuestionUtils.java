package com.quiz.parser;

import com.quiz.model.Question;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QuestionUtils {

    public static Map<String, Map<String, List<Question>>> loadQuestions() throws IOException {
        Map<String, Map<String, List<Question>>> questions = new HashMap<>();
        Path basePath = Paths.get("src/main/resources/questions");

        Files.walk(basePath)
                .filter(Files::isRegularFile)
                .filter(path -> path.toString().endsWith(".md"))
                .peek(path -> System.out.println("path is --> "+path))
                .forEach(path -> {
                    try {
                        String stackName = basePath.relativize(path.getParent()).getName(0).toString();
                        String sectionName = basePath.relativize(path.getParent()).getName(1).toString();
                        String content = Files.readString(path);

                        List<String> questionBlocks = Arrays.stream(content.split("##"))
                                .skip(1)
                                .map(String::trim)
                                .toList();

                        List<Question> questionList = questionBlocks.stream()
                                .map(questionBlock -> parse(questionBlock, sectionName, stackName))
                                .toList();

                        System.out.println("questions--->"+questions);
                        if (questions.containsKey(stackName)){
                            if (questions.get(stackName).containsKey(sectionName)){
                                questions.get(stackName).computeIfPresent(sectionName, (section, existingQuestions) -> {
                                    List<Question> updatedQuestionList = new ArrayList<>();
                                    updatedQuestionList.addAll(existingQuestions);
                                    updatedQuestionList.addAll(questionList);
                                    return updatedQuestionList;
                                });
                            }else{
                                questions.get(stackName).computeIfAbsent(sectionName , s -> new ArrayList<>()).addAll(questionList);
                            }
                        }else{
                            questions.computeIfAbsent(stackName, k -> new HashMap<>()).put(sectionName, questionList);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("-1-->"+path);
                    }
                });

        // order the questions map

        return questions;
    }

    private static String extractContent(String text, String regex, int group){
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(text);
        String content = "";
        if (matcher.find()) {
            content = matcher.group(group).trim();
            System.out.println("Extracted Content: " + content);
        }
        return content;
    }

    private static Map<String,String> getAnswerOptions(String questionSection){
        String answerOptions = extractContent(questionSection.substring(questionSection.indexOf("**Options**")), "```markdown(.*?)```", 1);
        List<String> optionList = Arrays.stream(answerOptions.split("\n")).map(String::trim).filter(s -> !s.isBlank()).toList();
        Map<String,String> options = optionList
                .stream()
                .peek(s -> System.out.println("--->"+s))
                .collect(Collectors.toMap(s ->
                                s.split("\\.")[0]
                                        .replace("-", "")
                                        .replace("*","")
                                        .trim(),
                        s -> s.split("\\.\s")[1]
                ));
        return options;
    }

    public static Question parse(String questionMd, String sectionName, String stackName){
        System.out.println("questionMd--->"+questionMd);
        String questionRegex = "(.*?)<details><summary>Response:</summary>";
        String questionSection = extractContent(questionMd,questionRegex, 1);

        System.out.println("questionSection--->"+questionSection);
        String qId = questionSection.substring(0,questionSection.indexOf("```markdown")).replace("\s", "");
        System.out.println("---->>"+qId);
        String question = extractContent(questionSection, "```markdown(.*?)```", 1);
        Map<String,String> options = getAnswerOptions(questionSection);

        String responseRegex = "<details><summary>Response:</summary>(.*?)</details>";
        String responseSection = extractContent(questionMd, responseRegex,1);
        String correctAnswer = responseSection.substring(
                responseSection.indexOf("**Answer:**")+"**Answer:**".length(),
                responseSection.indexOf("**Explanation:**")
        ).trim();
        System.out.println("-->"+correctAnswer);
        System.out.printf("___");
        String explanation = extractContent(responseSection, "```markdown(.*?)```", 1);

        Question q = Question.builder()
                .id(String.join("-", stackName, sectionName, qId))
                .questionText(question)
                .options(options)
                .correctAnswer(correctAnswer)
                .explanation(explanation)
                .build();
        System.out.println(q);
        return q;
    }

    public static String readMarkdownFile(String filePath) throws IOException {
        return Files.readString(Path.of(filePath));
    }

}
