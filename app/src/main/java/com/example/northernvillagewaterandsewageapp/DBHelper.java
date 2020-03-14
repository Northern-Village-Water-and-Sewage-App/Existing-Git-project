package com.example.northernvillagewaterandsewageapp;

import android.util.Log;

public class DBHelper {

    private static final String TAG = "DBhelper";

    //Constructor
    public DBHelper() {
        Log.d(TAG, "dbHelper constructor.");

    }

    //For login Activity
    /*public void GetUser(String Username){
        Integer Pin = from database using username;
        Integer UserType = from database using username;
        return new User(Username, Pin, UserType);
    }*/

    //For resident Activity
    /*public void GetTankInfo(settings.TankId){
        Integer WaterHeight = from database;
        Integer WaterAlarm = from database;
        Integer SewageAlarm = from database;
        return new TankStatus(WaterHeight, WaterAlarm, SewageAlarm);
    }*/

    //Used by resident, manager and driver
    /*public void ServiceDemand(Demand demand){
        Insert demand.DemandType into database;
        Insert demand.HouseNum into database;
        Insert demand.TimeOfDemand into database;
        return null;
    }*/


}
