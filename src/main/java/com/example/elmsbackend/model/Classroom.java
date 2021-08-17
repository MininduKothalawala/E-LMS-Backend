package com.example.elmsbackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Classroom")
public class Classroom {
    @Id
    private String id;
    private String grade;
    private String subject;
    private String topic;
    private String description;
    private String date;
    private String time;
    private String link;
    private String addedBy;
    private String lec_filename;
    private String lec_fileId;
    private String tute_filename;
    private String tute_fileId;
    private String img_filename;
    private String img_fileId;

    public Classroom() {
    }

    public Classroom(String id, String grade, String subject, String topic, String description, String date, String time, String link, String addedBy, String lec_filename, String lec_fileId, String tute_filename, String tute_fileId, String img_filename, String img_fileId) {
        this.id = id;
        this.grade = grade;
        this.subject = subject;
        this.topic = topic;
        this.description = description;
        this.date = date;
        this.time = time;
        this.link = link;
        this.addedBy = addedBy;
        this.lec_filename = lec_filename;
        this.lec_fileId = lec_fileId;
        this.tute_filename = tute_filename;
        this.tute_fileId = tute_fileId;
        this.img_filename = img_filename;
        this.img_fileId = img_fileId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public String getLec_filename() {
        return lec_filename;
    }

    public void setLec_filename(String lec_filename) {
        this.lec_filename = lec_filename;
    }

    public String getLec_fileId() {
        return lec_fileId;
    }

    public void setLec_fileId(String lec_fileId) {
        this.lec_fileId = lec_fileId;
    }

    public String getTute_filename() {
        return tute_filename;
    }

    public void setTute_filename(String tute_filename) {
        this.tute_filename = tute_filename;
    }

    public String getTute_fileId() {
        return tute_fileId;
    }

    public void setTute_fileId(String tute_fileId) {
        this.tute_fileId = tute_fileId;
    }

    public String getImg_filename() {
        return img_filename;
    }

    public void setImg_filename(String img_filename) {
        this.img_filename = img_filename;
    }

    public String getImg_fileId() {
        return img_fileId;
    }

    public void setImg_fileId(String img_fileId) {
        this.img_fileId = img_fileId;
    }
}
