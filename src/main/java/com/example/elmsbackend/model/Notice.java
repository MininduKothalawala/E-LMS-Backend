package com.example.elmsbackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Notice {

    @Id
    private String noticeId;
    private String subject;
    private String grade;
    private String topic;
    private String body;

    public Notice() {
    }

    public Notice(String noticeId, String subject, String grade, String topic, String body) {
        this.noticeId = noticeId;
        this.subject = subject;
        this.grade = grade;
        this.topic = topic;
        this.body = body;
    }

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
