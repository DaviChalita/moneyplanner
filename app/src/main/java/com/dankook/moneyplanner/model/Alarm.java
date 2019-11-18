package com.dankook.moneyplanner.model;

public class Alarm {
    private String idAlarm;
    private float limit;

    public Alarm(){

    }

    public Alarm(String idAlarm, float limit){
        this.idAlarm = idAlarm;
        this.limit = limit;
    }

    public String getIdAlarm() {
        return idAlarm;
    }

    public void setIdAlarm(String idAlarm) {
        this.idAlarm = idAlarm;
    }

    public float getLimit() {
        return limit;
    }

    public void setLimit(float limit) {
        this.limit = limit;
    }
}