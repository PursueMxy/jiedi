package com.icarexm.jiediuser.bean;

public class pointsBean {
   private String location;
   private String locatetime;
   private String speed;
   private String direction;
   private String height;
   private String accuracy;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getlocatetime() {
        return locatetime;
    }

    public void setlocatetime(String locatetime) {
        this.locatetime = locatetime;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public pointsBean(String location, String locatetime, String speed, String direction, String height, String accuracy) {
        this.location = location;
        this.locatetime = locatetime;
        this.speed = speed;
        this.direction = direction;
        this.height = height;
        this.accuracy = accuracy;
    }
}
