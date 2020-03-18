package com.example.northernvillagewaterandsewageapp.ObjectClasses;

import java.sql.Time;

public class DisableUser {

    String userName;
    int disableTime;
    int timeDuration; // for e.g. Hours, days, weeks, months

    //constructor
    public DisableUser(String userName, int disableTime, int timeDuration) {
        this.userName = userName;
        this.disableTime = disableTime;
        this.timeDuration = timeDuration;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getDisableTime() {
        return disableTime;
    }

    public void setDisableTime(int disableTime) {
        this.disableTime = disableTime;
    }

    public int getTimeDuration() {
        return timeDuration;
    }

    public void setTimeDuration(int timeDuration) {
        this.timeDuration = timeDuration;
    }
}
