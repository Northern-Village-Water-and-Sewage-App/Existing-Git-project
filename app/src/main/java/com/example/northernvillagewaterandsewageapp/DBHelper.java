package com.example.northernvillagewaterandsewageapp;

import android.provider.ContactsContract;
import android.util.Log;

import androidx.room.Insert;

import com.example.northernvillagewaterandsewageapp.ObjectClasses.Complaint;
import com.example.northernvillagewaterandsewageapp.ObjectClasses.Demand;
import com.example.northernvillagewaterandsewageapp.ObjectClasses.DisableUser;
import com.example.northernvillagewaterandsewageapp.ObjectClasses.TankStatus;
import com.example.northernvillagewaterandsewageapp.ObjectClasses.User;

import java.sql.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DBHelper {

    private static final String TAG = "DBhelper";

    //Constructor
    public DBHelper() {
        Log.d(TAG, "dbHelper constructor.");

    }

    //For login Activity
    public int returnUserType(String username, int userPin){

        User user = new User(username, userPin);
        user.setUserName(username); user.setPIN(userPin);
        int userType = user.getUserType();

        return userType;
    }

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

    //Manager and driver get worklist
    /*public List<Demand> getWorkList(){
        List<Demand> demandList = new ArrayList<>();
        demandList.add(new Demand(int DemandType, TimeOfDemand, HouseNum, workListNum);// from the database workList not marked as completed;
        return demandList;
    }*/

    //Managers and drivers update the worklist
    /*updateWorkList(int workListNum, int timeEstimate){
        Insert timeEstimate into database, finding the right position with the workListNum;
        Return void;
    }*/

    //Managers add residents and drivers
    /*public void addUser(User newUser){
        Insert newUser.userName into usersList table;
        Insert newUser.userType into usersList table;
        Insert newUser.PIN into usersList table;
        return;
    }*/

    //Managers remove driver or resident
    /*public void removeUser(String username){
        //Remove user from the table using username;
        return;
    }*/

    //Managers can disable residents
    public void disableResident(String username, int disableTime, int timeDuration){
        DisableUser disableUser = null;
        disableUser.setUserName(username);
        disableUser.setDisableTime(disableTime);
        disableUser.setTimeDuration(timeDuration);
        return;
    }

    //Managers can get the reports drivers made
    /*public void getReports(int company){
        If no company selected get all reports;
        Else get all reports by filtering for company;
        return;
    }*/

    //drivers can make a report
    /*public void makeReport(Complaint complaint){
        complaint.getCompany() = Use house number to find company, in table_1;
        Insert company into reports table;
        Insert HouseNumber into reports table;
        Insert complaintType into reports table;
        Insert complaint into reports table;
        return;
    }*/


}
