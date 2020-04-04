package com.example.northernvillagewaterandsewageapp.ObjectClasses;

public class User {

    private String UserName;
    private int PIN;
    //0: manager, 1: resident, 2: driver
    private int userType;
    private String HouseNum;

    //constructor
    public User (String username, int pin, int usertype, String houseNum){
        UserName = username;
        PIN = pin;
        userType = usertype;
        HouseNum = houseNum;
    }

    public User (String username, int pin, int usertype){
        UserName = username;
        PIN = pin;
        userType = usertype;
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
        /*if (UserName.equals("Matt") && PIN == 111) {
            return userType = 0;
        }
        else if (UserName.equals("Red") && PIN == 222) {
            return userType = 1;
        }
        else if (UserName.equals("Dean") && PIN == 333) {
            return userType = 2;
        }
        else {
            return userType = 3;
        }*/
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
}
