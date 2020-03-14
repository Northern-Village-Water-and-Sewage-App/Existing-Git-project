package com.example.northernvillagewaterandsewageapp.ObjectClasses;

public class Demand {

    String DemandType;
    Integer HouseNum;
    Integer TimeOfDemand;

    //constructor

    public Demand(String demandType, Integer houseNum, Integer timeOfDemand) {
        DemandType = demandType;
        HouseNum = houseNum;
        TimeOfDemand = timeOfDemand;
    }

    public String getDemandType() {
        return DemandType;
    }

    public void setDemandType(String demandType) {
        DemandType = demandType;
    }

    public Integer getHouseNum() {
        return HouseNum;
    }

    public void setHouseNum(Integer houseNum) {
        HouseNum = houseNum;
    }

    public Integer getTimeOfDemand() {
        return TimeOfDemand;
    }

    public void setTimeOfDemand(Integer timeOfDemand) {
        TimeOfDemand = timeOfDemand;
    }
}
