package com.example.elmsbackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Notice")
public class Notice {

    @Id
    private String noticeId;
    private String noticeSubject;
    private String noticeGrade;
    private String noticeTopic;
    private String noticeBody;
    private String enteredDate;
    private String enteredTime;

    public Notice() {
    }

    public Notice(String noticeId, String noticeSubject, String noticeGrade, String noticeTopic, String noticeBody, String enteredDate, String enteredTime) {
        this.noticeId = noticeId;
        this.noticeSubject = noticeSubject;
        this.noticeGrade = noticeGrade;
        this.noticeTopic = noticeTopic;
        this.noticeBody = noticeBody;
        this.enteredDate = enteredDate;
        this.enteredTime = enteredTime;
    }

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeSubject() {
        return noticeSubject;
    }

    public void setNoticeSubject(String noticeSubject) {
        this.noticeSubject = noticeSubject;
    }

    public String getNoticeGrade() {
        return noticeGrade;
    }

    public void setNoticeGrade(String noticeGrade) {
        this.noticeGrade = noticeGrade;
    }

    public String getNoticeTopic() {
        return noticeTopic;
    }

    public void setNoticeTopic(String noticeTopic) {
        this.noticeTopic = noticeTopic;
    }

    public String getNoticeBody() {
        return noticeBody;
    }

    public void setNoticeBody(String noticeBody) {
        this.noticeBody = noticeBody;
    }

    public String getEnteredDate() {
        return enteredDate;
    }

    public void setEnteredDate(String enteredDate) {
        this.enteredDate = enteredDate;
    }

    public String getEnteredTime() {
        return enteredTime;
    }

    public void setEnteredTime(String enteredTime) {
        this.enteredTime = enteredTime;
    }
}
