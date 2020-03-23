package com.example.northernvillagewaterandsewageapp.ObjectClasses;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.northernvillagewaterandsewageapp.R;

public class User {

    String userName;
    int PIN;
    int userType;
    int HouseNum;

    // Constructor that initialises the userName and userPin
    public User(String name, int pin) {
        userName = name;
        PIN = pin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        userName = username;
    }

    public int getPIN() {
        return PIN;
    }

    public void setPIN(int pin) {
        PIN = pin;
    }

    public int getUserType() {

        // If user is a manager, set userType = 0
        if (userName.equals("Matt") && (PIN == 111)){
            userType = 0;            return userType;
        }
        // If user is a resident, set userType = 1
        else if (userName.equals("Red") && (PIN == 222)) {
            userType = 1;            return userType;
        }
        // If user is a driver, set userType = 2
        else if (userName.equals("Dean") && (PIN == 333)) {
            userType = 2;            return userType;
        }
        // If wrong login is entered, set userType = 3 to set an error at login
        else {
            userType = 3;
            return userType;
        }
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getHouseNum() {
        return HouseNum;
    }

    public void setHouseNum(int houseNum) {
        HouseNum = houseNum;
    }
}
