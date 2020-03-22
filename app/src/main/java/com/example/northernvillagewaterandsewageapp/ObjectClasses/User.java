package com.example.northernvillagewaterandsewageapp.ObjectClasses;

import android.content.Context;
import android.content.SharedPreferences;

import static com.example.northernvillagewaterandsewageapp.LoginActivity.Name;
import static com.example.northernvillagewaterandsewageapp.LoginActivity.userInfo;
import com.example.northernvillagewaterandsewageapp.R;

public class User {

    String userName;
    int PIN;
    int userType;
    int HouseNum;

    //constructor
    public User() {
        //SharedPreferences sharedPreferences = getSharedPreferences(userInfo, Context.MODE_PRIVATE);
        //String name = sharedPreferences.getString(Name, "");

        userName = "name";
        PIN = 111;
        userType = 1;
        HouseNum = 12;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
