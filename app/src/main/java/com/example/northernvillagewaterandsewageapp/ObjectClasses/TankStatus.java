package com.example.northernvillagewaterandsewageapp.ObjectClasses;

import java.util.Random;

public class TankStatus {

    int WaterHeight;
    int WaterAlarm;
    int SewageAlarm;

    //constructor
    public TankStatus() {
        int randomWater = new Random().nextInt(101);
        int randomSewage = new Random().nextInt(3);

        WaterHeight = randomWater;
        WaterAlarm = randomWater;
        SewageAlarm = randomSewage;
    }

    public int getWaterHeight() {
        return WaterHeight;
    }

    public void setWaterHeight(int waterHeight) {
        WaterHeight = waterHeight;
    }

    public int getWaterAlarm() {
        return WaterAlarm;
    }

    public void setWaterAlarm(int waterAlarm) {
        WaterAlarm = waterAlarm;
    }

    public int getSewageAlarm() {
        return SewageAlarm;
    }

    public void setSewageAlarm(int sewageAlarm) {
        SewageAlarm = sewageAlarm;
    }
}
