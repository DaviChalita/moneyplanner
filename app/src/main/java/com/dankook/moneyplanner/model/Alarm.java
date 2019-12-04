package com.dankook.moneyplanner.model;

import java.io.Serializable;

public class Alarm implements Serializable {
    private String idAlarm;
    private float limit;

    public Alarm() {

    }

    public Alarm(String idAlarm, float limit) {
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

    public void setLimit(String limit) {
        this.limit = Float.parseFloat(limit);
    }

    public boolean warnTheUser(float balance, float limit) {
        if (balance <= limit) {
            return true;
        }
        return false;
    }
}