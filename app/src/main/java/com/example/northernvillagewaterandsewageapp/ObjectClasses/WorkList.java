package com.example.northernvillagewaterandsewageapp.ObjectClasses;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class WorkList {

    private String TimeStamp;
    private String Resident;
    private String HouseNum;
    private String TankType;
    private String TimeEstimate;
    private String waterID = "@drawable/water_image2";
    private int sewageID;

    // constructor
    public WorkList(String timeStamp, String resident, String houseNum, String tankType, String timeEstimate) {
        TimeStamp = timeStamp;
        Resident = resident;
        HouseNum = houseNum;
        TankType = tankType;
        TimeEstimate = timeEstimate;
    }

    public String getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        TimeStamp = timeStamp;
    }

    public String getResident() {
        return Resident;
    }

    public void setResident(String resident) {
        Resident = resident;
    }

    public String getHouseNum() {
        return HouseNum;
    }

    public void setHouseNum(String houseNum) {
        HouseNum = houseNum;
    }

    public String getTankType() {
        return TankType;
    }

    public void setTankType(String tankType) {
        TankType = tankType;
    }

    public String getTimeEstimate() {
        return TimeEstimate;
    }

    public void setTimeEstimate(String timeEstimate) {
        this.TimeEstimate = timeEstimate;
    }

    public String getResourceID() {
        String resourceID = "0";

        switch (getTankType()) {
            case "Water":
                resourceID = "@drawable/water_image2";
                break;
            case "Sewage":
                resourceID = "@drawable/water_image1";
                break;
        }
        return resourceID;
    }

}
