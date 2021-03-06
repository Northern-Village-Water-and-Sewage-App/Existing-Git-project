package com.example.northernvillagewaterandsewageapp.ObjectClasses;

import java.sql.Time;

public class Demand {

    int DemandType;
    Time TimeOfDemand;
    String HouseNum;
    int workListNum; // to be generated by database

    //constructor
    public Demand(int demandType, Time timeOfDemand, String houseNo, int worklistNum) {
        DemandType = demandType;
        TimeOfDemand = timeOfDemand;
        HouseNum = houseNo;
        workListNum = worklistNum;
    }

    public int getDemandType() {
        return DemandType;
    }

    public void setDemandType(int demandType) {
        DemandType = demandType;
    }

    public Time getTimeOfDemand() {
        return TimeOfDemand;
    }

    public void setTimeOfDemand(Time timeOfDemand) {
        TimeOfDemand = timeOfDemand;
    }

    public String getHouseNum() {
        return HouseNum;
    }

    public void setHouseNum(String houseNo) {
        HouseNum = houseNo;
    }

    public int getWorkListNum() {
        return workListNum;
    }

    public void setWorkListNum(int worklistNum) {
        workListNum = worklistNum;
    }
}
