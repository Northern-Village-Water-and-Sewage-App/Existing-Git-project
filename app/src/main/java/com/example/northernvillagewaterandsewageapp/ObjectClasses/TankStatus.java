package com.example.northernvillagewaterandsewageapp.ObjectClasses;

public class TankStatus {

    Integer WaterHeight;
    Integer WaterAlarm;
    Integer SewageAlarm;

    //constructor
    public TankStatus(Integer waterHeight, Integer waterAlarm, Integer sewageAlarm) {
        WaterHeight = waterHeight;
        WaterAlarm = waterAlarm;
        SewageAlarm = sewageAlarm;
    }

    public Integer getWaterHeight() {
        return WaterHeight;
    }

    public void setWaterHeight(Integer waterHeight) {
        WaterHeight = waterHeight;
    }

    public Integer getWaterAlarm() {
        return WaterAlarm;
    }

    public void setWaterAlarm(Integer waterAlarm) {
        WaterAlarm = waterAlarm;
    }

    public Integer getSewageAlarm() {
        return SewageAlarm;
    }

    public void setSewageAlarm(Integer sewageAlarm) {
        SewageAlarm = sewageAlarm;
    }
}
