package com.example.worldinfoapplication.enitity;

public class MenuData {
    private String activityName;
    private String activityKey;
    private int activityImage;

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityKey() {
        return activityKey;
    }

    public void setActivityKey(String activityKey) {
        this.activityKey = activityKey;
    }

    public int getActivityImage() {
        return activityImage;
    }

    public void setActivityImage(int activityImage) {
        this.activityImage = activityImage;
    }




    public MenuData(String activityName, String activityKey, int activityImage) {
        this.activityName = activityName;
        this.activityKey = activityKey;
        this.activityImage = activityImage;
    }

}
