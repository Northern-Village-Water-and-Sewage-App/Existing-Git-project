package com.example.northernvillagewaterandsewageapp.ObjectClasses;

public class WorkList {

    private String TimeStamp;
    private String Resident;
    private String HouseNum;
    private String TankType;
    private String TimeEstimate;

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

}
