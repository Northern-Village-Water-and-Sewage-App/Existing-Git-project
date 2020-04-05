package com.example.northernvillagewaterandsewageapp.ObjectClasses;

public class User {

    private String UserName;
    private int PIN;
    //0: manager, 1: resident, 2: driver
    private int userType;
    private String HouseNum;

    private String EstimatedUsage;
    private String usageType;


    //constructor
    public User (String username, int pin, int usertype, String houseNum){
        UserName = username;
        PIN = pin;
        userType = usertype;
        HouseNum = houseNum;
    }
    // Constructor for resident analytics
    public User (String useType, String estimatedUse){
        EstimatedUsage = estimatedUse;
        usageType = useType;

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

    public void setEstimatedUsage(String estimatedUse) {
        EstimatedUsage = estimatedUse;
    }

    public String getUsageType() {
        return usageType;
    }

    public void setUsageType(String useType) {
        usageType = useType;
    }

}
