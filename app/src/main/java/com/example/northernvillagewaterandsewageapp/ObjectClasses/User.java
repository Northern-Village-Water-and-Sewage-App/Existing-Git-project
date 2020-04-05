package com.example.northernvillagewaterandsewageapp.ObjectClasses;

public class User {

    private String UserName;
    private int PIN;
    //0: manager, 1: resident, 2: driver
    private int userType;
    private String HouseNum;

    private int EstimatedUsage;
    private int Dishes;
    private int Washes;
    private int Showers;

    //constructor
    public User (String username, int pin, int usertype, String houseNum){
        UserName = username;
        PIN = pin;
        userType = usertype;
        HouseNum = houseNum;
    }
    // Constructor for resident analytics
    public User (int estimatedUse, int dishes, int washes, int showers){
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

    public int getEstimatedUsage() {
        return EstimatedUsage;
    }

    public void setEstimatedUsage(int estimatedUsage) {
        EstimatedUsage = estimatedUsage;
    }

    public int getDishes() {
        return Dishes;
    }

    public void setDishes(int dishes) {
        Dishes = dishes;
    }

    public int getWashes() {
        return Washes;
    }

    public void setWashes(int washes) {
        Washes = washes;
    }

    public int getShowers() {
        return Showers;
    }

    public void setShowers(int showers) {
        Showers = showers;
    }
}
