package com.example.walker.milestone;

public class User {
    String uid, displayName, email, school;
    boolean isUser;

    public User(String uid, String displayName, String email, String school, boolean isUser) {
        this.uid = uid;
        this.displayName = displayName;
        this.email = email;
        this.school = school;
        this.isUser = isUser;
    }

    public String getUid() {return uid;}
    public String getDisplayName() {return displayName;}
    public void setDisplayName(String displayName) {this.displayName = displayName;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getSchool() {return school;}
    public void setSchool(String school) {this.school = school;}

    public boolean isUser() {return isUser;}
}
