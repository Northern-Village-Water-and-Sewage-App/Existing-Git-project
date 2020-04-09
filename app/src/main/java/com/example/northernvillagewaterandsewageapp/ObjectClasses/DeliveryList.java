package com.example.northernvillagewaterandsewageapp.ObjectClasses;

import com.example.northernvillagewaterandsewageapp.R;

import java.sql.Time;


public class DeliveryList {

    private String TimeStamp;
    private String HouseNum;
    private String Service;
    private String TimeEstimate;

    public DeliveryList(String timeStamp, String houseNum, String service, String timeEstimate) {
        TimeStamp = timeStamp;
        HouseNum = houseNum;
        Service = service;
        TimeEstimate = timeEstimate;
    }
    public String getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        TimeStamp = timeStamp;
    }

    public String getHouseNum() {
        return HouseNum;
    }

    public void setHouseNum(String houseNum) {
        HouseNum = houseNum;
    }

    public String getTimeEstimate() {
        return TimeEstimate;
    }

    public void setTimeEstimate(String timeEstimate) {
        TimeEstimate = timeEstimate;
    }

    public String getService() {
        return Service;
    }

    public void setService(String service) {
        Service = service;
    }

    public int getResourceID() {
        int id = 0;
        if (Service.equals("Water"))
            id = R.drawable.water_icon_latest;
        else if (Service.equals("Sewage"))
            id = R.drawable.sewage_icon;
        return id;
    }

}
