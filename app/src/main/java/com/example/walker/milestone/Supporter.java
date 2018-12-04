package com.example.walker.milestone;

public class Supporter {
    String uid, displayName, email, supporterCode;
    boolean isUser;
    int iconImage;

    public Supporter(String uid, String displayName, String email, String supporterCode,
                     boolean isUser, int iconImage) {
        this.uid = uid;
        this.displayName = displayName;
        this.email = email;
        this.supporterCode = supporterCode;
        this.isUser = isUser;
        this.iconImage = iconImage;
    }

    public String getUid() {return uid;}
    public String getDisplayName() {return displayName;}
    public void setDisplayName(String displayName) {this.displayName = displayName;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getSupporterCode() {return supporterCode;}
    public void setSupporterCode(String supporterCode) {this.supporterCode = supporterCode;}

    public boolean getIsUser() {return isUser;}

    public int getIconImage() {return iconImage;}
    public void setIconImage(int iconImage) {this.iconImage = iconImage;}
}
