package com.example.northernvillagewaterandsewageapp.ObjectClasses;

public class Demand {

    String DemandType;
    Integer TimeOfDemand;
    User HouseNum;

    //constructor

    public Demand(String demandType, Integer timeOfDemand, User houseNum) {
        DemandType = demandType;
        TimeOfDemand = timeOfDemand;
        HouseNum = houseNum;
    }

    public String getDemandType() {
        return DemandType;
    }

    public void setDemandType(String demandType) {
        DemandType = demandType;
    }

    public Integer getTimeOfDemand() {
        return TimeOfDemand;
    }

    public void setTimeOfDemand(Integer timeOfDemand) {
        TimeOfDemand = timeOfDemand;
    }

    public User getHouseNum() {
        return HouseNum;
    }

    public void setHouseNum(User houseNum) {
        HouseNum = houseNum;
    }
}
