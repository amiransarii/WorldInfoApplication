package com.example.worldinfoapplication.enitity;

public class Sports {
    private int sportsID;
    private String sportsName;
    private double coachSalary;
    private String sportsLocation;

    public int getSportsID() {
        return sportsID;
    }

    public void setSportsID(int sportsID) {
        this.sportsID = sportsID;
    }

    public String getSportsName() {
        return sportsName;
    }

    public void setSportsName(String sportsName) {
        this.sportsName = sportsName;
    }

    public double getCoachSalary() {
        return coachSalary;
    }

    public void setCoachSalary(double coachSalary) {
        this.coachSalary = coachSalary;
    }

    public String getSportsLocation() {
        return sportsLocation;
    }

    public void setSportsLocation(String sportsLocation) {
        this.sportsLocation = sportsLocation;
    }


    public Sports(int sportsID, String sportsName, double coachSalary, String sportsLocation) {
        this.sportsID = sportsID;
        this.sportsName = sportsName;
        this.coachSalary = coachSalary;
        this.sportsLocation = sportsLocation;
    }


}
