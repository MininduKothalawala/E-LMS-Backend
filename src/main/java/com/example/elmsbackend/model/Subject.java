package com.example.elmsbackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("Subject")
public class Subject {

    @Id
    private String grade;
    private List<String> subjects;

    public Subject() {
    }

    public Subject(String grade, List<String> subjects) {
        this.grade = grade;
        this.subjects = subjects;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }
}
