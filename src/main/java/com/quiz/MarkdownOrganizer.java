package com.quiz;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class MarkdownOrganizer {

    public static void main(String[] args) throws IOException {
        Path kafkaFolder = Paths.get("src/main/resources/questions/kafka/13.schema-registry");

        Files.walkFileTree(kafkaFolder, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.toString().endsWith(".md")) {
                    Path parentFolder = file.getParent();
                    String markdownFileName = file.getFileName().toString().replace(".md", "");
                    Path newSubFolder = parentFolder.resolve(parentFolder.getFileName() + "-" + markdownFileName);

                    // Create sub-folder
                    Files.createDirectories(newSubFolder);

                    // Move markdown file into the sub-folder
                    Files.copy(file, newSubFolder.resolve(file.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }
}