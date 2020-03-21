package com.example.northernvillagewaterandsewageapp.ObjectClasses;

public class User {

    String userName;
    Integer PIN;
    Integer userType;
    Integer HouseNum;

    //constructor
    public User(String userName, Integer PIN, Integer userType, Integer houseNum) {
        this.userName = userName;
        this.PIN = PIN;
        this.userType = userType;
        HouseNum = houseNum;
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

    public Integer getHouseNum() {
        return HouseNum;
    }

    public void setHouseNum(Integer houseNum) {
        HouseNum = houseNum;
    }
}