package com.example.northernvillagewaterandsewageapp.ObjectClasses;

public class User {

    String UserName;
    int PIN;
    //0: manager, 1: resident, 2: driver
    int Usertype;
    String HouseNum;

    //constructor
    public User() {
        //SharedPreferences sharedPreferences = getSharedPreferences(userInfo, Context.MODE_PRIVATE);
        //String name = sharedPreferences.getString(Name, "");

        UserName = "name";
        PIN = 111;
        Usertype = 1;
        HouseNum = "12";
    }

    public User (String username, int pin, int userType, String houseNum){
        UserName = username;
        PIN = pin;
        Usertype = userType;
        HouseNum = houseNum;
    }

    public User (String username, int pin, int userType){
        UserName = username;
        PIN = pin;
        Usertype = userType;
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

    public int getUsertype() {
        return Usertype;
    }

    public void setUsertype(int usertype) {
        this.Usertype = usertype;
    }

    public String getHouseNum() {
        return HouseNum;
    }

    public void setHouseNum(String houseNum) {
        HouseNum = houseNum;
    }
}
