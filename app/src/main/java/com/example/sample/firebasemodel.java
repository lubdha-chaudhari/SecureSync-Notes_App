package com.example.sample;

public class firebasemodel {
    private String title;
    private String content;

    // Required public no-argument constructor
    public firebasemodel() {
        // Default constructor required for Firestore
    }

    public firebasemodel(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // Getter and setter methods for title and content

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
