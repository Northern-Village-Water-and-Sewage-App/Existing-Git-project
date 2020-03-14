package com.example.northernvillagewaterandsewageapp.ObjectClasses;

public class User {

    String userName;
    Integer PIN;
    Integer userType;

    //constructor
    public User(String userName, Integer PIN, Integer userType) {
        this.userName = userName;
        this.PIN = PIN;
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getPIN() {
        return PIN;
    }

    public void setPIN(Integer PIN) {
        this.PIN = PIN;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}
