package com.example.elmsbackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("User")
public class User {

    @Id
    private String indexno;
    private String password;
    private String name;
    private String mobile_no;
    private String email;
    private String role;

    public User() {
    }

    public User(String indexno, String password, String name, String mobile_no, String email, String role) {
        this.indexno = indexno;
        this.password = password;
        this.name = name;
        this.mobile_no = mobile_no;
        this.email = email;
        this.role = role;
    }

    public String getIndexno() {
        return indexno;
    }

    public void setIndexno(String indexno) {
        this.indexno = indexno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
