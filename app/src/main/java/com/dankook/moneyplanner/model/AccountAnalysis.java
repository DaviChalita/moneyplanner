package com.dankook.moneyplanner.model;

public class AccountAnalysis {
    private String year;
    private String month;
    private String day;
    private String dayOfWeek;
    private float totalTheDay;
    private float totalTheMonth;
    private float totalTheSpend;
    private float totalTheIncome;

    public AccountAnalysis() {
    }

    public AccountAnalysis(String year, String month, String day, String dayOfWeek,
                           float totalTheDay, float totalTheMonth, float totalTheSpend,
                           float totalTheIncome) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.dayOfWeek = dayOfWeek;
        this.totalTheDay = totalTheDay;
        this.totalTheMonth = totalTheMonth;
        this.totalTheSpend = totalTheSpend;
        this.totalTheIncome = totalTheIncome;
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

    public float getTotalTheSpend() {
        return totalTheSpend;
    }

    public void setTotalTheSpend(float totalTheSpend) {
        this.totalTheSpend = totalTheSpend;
    }

    public float getTotalTheIncome() {
        return totalTheIncome;
    }

    public void setTotalTheIncome(float totalTheIncome) {
        this.totalTheIncome = totalTheIncome;
    }
}
