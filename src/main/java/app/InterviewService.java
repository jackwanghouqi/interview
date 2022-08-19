package app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class InterviewService {
    @Value("${spring.resources.static-locations}")
    String resourcePath;

    public Interview getInterview(String name) throws IOException {
        System.out.println("service call");
        Interview interview = new Interview();
        File folder = new File(resourcePath.replaceAll("file:",""));
        for (File file:folder.listFiles()) {
            if(file.getName().equalsIgnoreCase(name)) {
                folder = file;
                interview.name = name;
            }
        }
        if(interview.name != null) {
            File[] categorieFolders = folder.listFiles();
            if(categorieFolders!= null) {
                for (File category:categorieFolders) {
                    if(category.isDirectory()) {
                        File[] topics = category.listFiles();
                        Interview.Category categoryView = new Interview.Category();
                        categoryView.name = ("【"+category.getName()+"】");
                        interview.categories.add(categoryView);
                        for (File topic:topics) {
                            String fileName = topic.getName();
                            Path path = Paths.get(topic.getAbsolutePath());
                            List<String> read = Files.readAllLines(path);
                            StringBuilder sb = new StringBuilder();
                            Interview.Topic topicView = new Interview.Topic();
                            for (String line:read) {
                                if(line.startsWith("package ") || line.startsWith("import ")) {
                                    continue;
                                }
                                sb.append(line).append("\n");
                                if(line.contains("TODO")) {
                                    String todo = line.split("TODO")[1];
                                    topicView.warning = (todo);
                                }
                            }

                            topicView.title = (fileName.split("\\.")[0]);
                            topicView.content = (sb.toString().trim());
                            categoryView.topics.add(topicView);
                        }
                    }
                }
            }

        }
        return interview;
    }
}
