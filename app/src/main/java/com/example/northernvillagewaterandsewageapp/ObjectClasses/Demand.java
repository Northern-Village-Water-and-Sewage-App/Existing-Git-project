package com.example.northernvillagewaterandsewageapp.ObjectClasses;

import java.sql.Time;

public class Demand {

    Integer DemandType;
    Time TimeOfDemand;
    String HouseNum;
    Integer workListNum; // to be generated by database

    //constructor
    public Demand(Integer demandType, Time timeOfDemand, String houseNum, int workListNum) {
        DemandType = demandType;
        TimeOfDemand = timeOfDemand;
        HouseNum = houseNum;
        this.workListNum = workListNum;
    }

    public Integer getDemandType() {
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

    public void setHouseNum(String houseNum) {
        HouseNum = houseNum;
    }

    public int getWorkListNum() {
        return workListNum;
    }

    public void setWorkListNum(int workListNum) {
        this.workListNum = workListNum;
    }
}
