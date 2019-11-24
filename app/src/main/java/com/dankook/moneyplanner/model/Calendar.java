package com.dankook.moneyplanner.model;

import androidx.appcompat.app.AppCompatActivity;

public class Calendar extends AppCompatActivity {

    private String year;
    private String month;
    private String day;
    private String dayOfWeek;
    private float totalTheDay;
    private float totalTheMonth;
    private float totalTheIncome;
    private float totalTheSpend;


    public Calendar() {

    }

    public Calendar(
            String year, String month, String day,
            String dayOfWeek, float totalTheDay, float totalTheMonth,
            float totalTheSpend, float totalTheIncome) {

        this.setYear(year);
        this.setMonth(month);
        this.setDay(day);
        this.setDayOfWeek(dayOfWeek);
        this.setTotalTheDay(totalTheDay);
        this.setTotalTheMonth(totalTheMonth);
        this.setTotalTheSpend(totalTheSpend);
        this.setTotalTheIncome(totalTheIncome);
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public float getTotalTheDay() {
        return totalTheDay;
    }

    public void setTotalTheDay(float totalTheDay) {
        this.totalTheDay = totalTheDay;
    }

    public float getTotalTheMonth() {
        return totalTheMonth;
    }

    public void setTotalTheMonth(float totalTheMonth) {
        this.totalTheMonth = totalTheMonth;
    }

    public float getTotalTheIncome() {
        return totalTheIncome;
    }

    public void setTotalTheIncome(float totalTheIncome) {
        this.totalTheIncome = totalTheIncome;
    }

    public float getTotalTheSpend() {
        return totalTheSpend;
    }

    public void setTotalTheSpend(float totalTheSpend) {
        this.totalTheSpend = totalTheSpend;
    }
}
