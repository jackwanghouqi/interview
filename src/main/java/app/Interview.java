package app;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Interview {

    String name;
    List<Category> categories = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public static class Category{
        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Topic> getTopics() {
            return topics;
        }

        public void setTopics(List<Topic> topics) {
            this.topics = topics;
        }

        List<Topic> topics = new ArrayList<>();
    }

    public static class Topic{
        String title;
        String warning;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getWarning() {
            return warning;
        }

        public void setWarning(String warning) {
            this.warning = warning;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        String content;
    }
}
