package com.example.northernvillagewaterandsewageapp.ObjectClasses;

public class DeliveryList {

    private String HouseNum;
    private String Service;
    private String TimeEstimate;

    public DeliveryList(String houseNum, String service, String timeEstimate) {
        HouseNum = houseNum;
        Service = service;
        TimeEstimate = timeEstimate;
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
}
