package com.example.northernvillagewaterandsewageapp.ObjectClasses;

public class Complaint {

    String complain;
    String complaintType;
    String company;
    User HouseNum;

    //constructor
    public Complaint(String complain, String complaintType, User houseNum, String company) {
        this.complain = complain;
        this.complaintType = complaintType;
        HouseNum = houseNum;
        this.company = company;
    }

    public String getComplain() {
        return complain;
    }

    public void setComplain(String complain) {
        this.complain = complain;
    }

    public String getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(String complaintType) {
        this.complaintType = complaintType;
    }

    public User getHouseNum() {
        return HouseNum;
    }

    public void setHouseNum(User houseNum) {
        HouseNum = houseNum;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
