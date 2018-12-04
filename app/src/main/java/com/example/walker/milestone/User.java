package com.example.walker.milestone;

public class User {
    String uid, displayName, email, school, vice;
    boolean isUser;
    int iconImage;


    public User(String uid, String displayName, String email, String school,
                int iconImage, String vice, boolean isUser) {
        this.uid = uid;
        this.displayName = displayName;
        this.email = email;
        this.school = school;
        this.iconImage = iconImage;
        this.vice = vice;
        this.isUser = isUser;
    }

    public String getUid() {return uid;}
    public String getDisplayName() {return displayName;}
    public void setDisplayName(String displayName) {this.displayName = displayName;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getSchool() {return school;}
    public void setSchool(String school) {this.school = school;}

    public int getIconImage() {return iconImage;}
    public void setIconImage(int iconImage) {this.iconImage = iconImage;}

    public String getVice() {return vice;}
    public void setVice(String vice) {this.vice = vice;}

    public boolean getIsUser() {return isUser;}
}
