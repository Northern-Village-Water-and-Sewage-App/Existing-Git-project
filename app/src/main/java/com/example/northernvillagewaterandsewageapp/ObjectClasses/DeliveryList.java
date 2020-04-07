package com.example.northernvillagewaterandsewageapp.ObjectClasses;

public class DeliveryList {

    private String Resident;
    private String Service;
    private String TimeEstimate;

    public DeliveryList(String resident, String service, String timeEstimate) {
        Resident = resident;
        Service = service;
        TimeEstimate = timeEstimate;
    }

    public String getResident() {
        return Resident;
    }

    public void setResident(String resident) {
        Resident = resident;
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
