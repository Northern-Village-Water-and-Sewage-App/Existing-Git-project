package com.example.northernvillagewaterandsewageapp.ObjectClasses;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.example.northernvillagewaterandsewageapp.R;

public class WorkList {

    private String TimeStamp;
    //private String Resident;
    private String HouseNum;
    private String TankType;
    private String TimeEstimate;

    // constructor
    public WorkList(String timeStamp, String houseNum, String tankType, String timeEstimate) {
        TimeStamp = timeStamp;
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

    public int getResourceID() {

        int id = 0;
        if (TankType.equals("Water")) {
            id = R.drawable.water_image2;
        }
        else if (TankType.equals("Sewage")) {
            id = R.drawable.sewage;
        }


        return id;
    }

}
