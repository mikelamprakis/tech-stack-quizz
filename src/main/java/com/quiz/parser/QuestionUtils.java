package com.quiz.parser;

import com.quiz.model.Question;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class QuestionUtils {

    public static Map<String, Map<String, List<Question>>> loadQuestions() throws IOException, URISyntaxException {
        Map<String, Map<String, List<Question>>> questions = new HashMap<>();
        
        // Get the current working directory and construct the path
        String currentDir = System.getProperty("user.dir");

        ClassLoader classLoader = QuestionUtils.class.getClassLoader();
        URL questionsUrl = classLoader.getResource("questions");
        if (questionsUrl == null) {
            throw new IOException("Questions directory not found in classpath!");
        }

        Path basePath = Paths.get(questionsUrl.toURI());

        //Path basePath = Paths.get(currentDir, "src/main/resources/questions");
        
        if (!Files.exists(basePath)) {
            throw new IOException("Questions directory not found at: " + basePath.toAbsolutePath());
        }

        Files.walk(basePath)
                .filter(Files::isRegularFile)
                .filter(path -> path.toString().endsWith(".md"))
                .forEach(path -> {
                    try {
                        String stackName = basePath.relativize(path.getParent()).getName(0).toString();
                        String categoryName = basePath.relativize(path.getParent()).getName(1).toString();
                        String fileName = path.getFileName().toString().replace(".md", "");
                        String sectionName = categoryName + "-" + fileName;
                        String content = Files.readString(path);

                        List<String> questionBlocks = Arrays.stream(content.split("##"))
                                .skip(1)
                                .map(String::trim)
                                .toList();

                        List<Question> questionList = questionBlocks.stream()
                                .map(questionBlock -> parse(questionBlock, sectionName, stackName))
                                .toList();

                        //System.out.println("questions--->"+questions);
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
                        //System.out.println("-1-->" + path);
                    }
                });

        // order the questions map
        Map<String, Map<String, List<Question>>> sortedQuestions = questions.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().entrySet()
                                .stream()
                                .sorted(Map.Entry.comparingByKey((key1, key2) -> {
                                    try {
                                        // Try to extract numeric part from the beginning
                                        String[] parts1 = key1.split("\\.", 2);
                                        String[] parts2 = key2.split("\\.", 2);

                                        if (parts1.length > 1 && parts2.length > 1) {
                                            try {
                                                int num1 = Integer.parseInt(parts1[0]);
                                                int num2 = Integer.parseInt(parts2[0]);

                                                // Compare numeric parts first
                                                int numComparison = Integer.compare(num1, num2);
                                                if (numComparison != 0) {
                                                    return numComparison;
                                                }

                                                // If numeric parts are equal, compare the rest of the string
                                                return parts1[1].compareTo(parts2[1]);
                                            } catch (NumberFormatException e) {
                                                // If parsing fails, just do string comparison
                                                return key1.compareTo(key2);
                                            }
                                        } else {
                                            // If no dot found, just do string comparison
                                            return key1.compareTo(key2);
                                        }
                                    } catch (Exception e) {
                                        // Fallback to simple string comparison
                                        return key1.compareTo(key2);
                                    }
                                }))
                                .collect(Collectors.toMap(
                                        Map.Entry::getKey,
                                        Map.Entry::getValue,
                                        (e1, e2) -> e1,
                                        LinkedHashMap::new // Preserve the sorted order
                                )),
                        (e1, e2) -> e1,
                        LinkedHashMap::new // Preserve the order of the outer map
                ));

        ///sortedQuestions.get("kafka").forEach((s, questions1) -> System.out.println(s));
        return sortedQuestions;
    }

    private static String extractContent(String text, String regex, int group){
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(text);
        String content = "";
        if (matcher.find()) {
            content = matcher.group(group).trim();
            //System.out.println("Extracted Content: " + content);
        }
        return content;
    }

    private static Map<String,String> getAnswerOptions(String questionSection){
        String answerOptions = extractContent(questionSection.substring(questionSection.indexOf("**Options**")), "```markdown(.*?)```", 1);
        List<String> optionList = Arrays.stream(answerOptions.split("\n")).map(String::trim).filter(s -> !s.isBlank()).toList();
        Map<String,String> options = optionList
                .stream()
                //.peek(s -> System.out.println("--->"+s))
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
        String questionRegex = "(.*?)<details><summary>Response:</summary>";
        String questionSection = extractContent(questionMd,questionRegex, 1);

        //System.out.println("questionSection--->"+questionSection);
        String qId = questionSection.substring(0,questionSection.indexOf("```markdown")).replace("\s", "");
        //System.out.println("---->>"+qId);
        String question = extractContent(questionSection, "```markdown(.*?)```", 1);
        Map<String,String> options = getAnswerOptions(questionSection);

        String responseRegex = "<details><summary>Response:</summary>(.*?)</details>";
        String responseSection = extractContent(questionMd, responseRegex,1);
        String correctAnswer = responseSection.substring(
                responseSection.indexOf("**Answer:**")+"**Answer:**".length(),
                responseSection.indexOf("**Explanation:**")
        ).trim();
        //System.out.println("-->"+correctAnswer);
        //System.out.printf("___");
        String explanation = extractContent(responseSection, "```markdown(.*?)```", 1);

        Question q = Question.builder()
                .id(String.join("-", stackName, sectionName, qId))
                .questionText(question)
                .options(options)
                .correctAnswer(correctAnswer)
                .explanation(explanation)
                .build();
        //System.out.println(q);
        return q;
    }

    public static String readMarkdownFile(String filePath) throws IOException {
        return Files.readString(Path.of(filePath));
    }

}
