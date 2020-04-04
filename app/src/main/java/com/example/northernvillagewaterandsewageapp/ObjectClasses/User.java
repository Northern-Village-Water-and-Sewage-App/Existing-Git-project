package com.example.northernvillagewaterandsewageapp.ObjectClasses;

public class User {

    private String UserName;
    private int PIN;
    //0: manager, 1: resident, 2: driver
    private int userType;
    private String HouseNum;

    private String EstimatedUsage;
    private String Dishes;
    private String Washes;
    private String Showers;

    //constructor
    public User (String username, int pin, int usertype, String houseNum){
        UserName = username;
        PIN = pin;
        userType = usertype;
        HouseNum = houseNum;
    }
    // Constructor for resident analytics
    public User (String estimatedUse, String dishes, String washes, String showers){
        EstimatedUsage = estimatedUse;
        Dishes = dishes;
        Washes = washes;
        Showers = showers;
    }

    public User (String username, int pin){
        UserName = username;
        PIN = pin;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        this.UserName = userName;
    }

    public int getPIN() {
        return PIN;
    }

    public void setPIN(int PIN) {
        this.PIN = PIN;
    }

    public int getUserType() {

        return userType;
    }

    public void setUserType(int usertype) {
        userType = usertype;
    }

    public String getHouseNum() {
        return HouseNum;
    }

    public void setHouseNum(String houseNum) {
        HouseNum = houseNum;
    }

    // For resident analytics

    public String getEstimatedUsage() {
        return EstimatedUsage;
    }

    public void setEstimatedUsage(String estimatedUsage) {
        EstimatedUsage = estimatedUsage;
    }

    public String getDishes() {
        return Dishes;
    }

    public void setDishes(String dishes) {
        Dishes = dishes;
    }

    public String getWashes() {
        return Washes;
    }

    public void setWashes(String washes) {
        Washes = washes;
    }

    public String getShowers() {
        return Showers;
    }

    public void setShowers(String showers) {
        Showers = showers;
    }
}
