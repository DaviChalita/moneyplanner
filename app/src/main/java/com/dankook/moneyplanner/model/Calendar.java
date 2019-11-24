package com.dankook.moneyplanner.model;

import androidx.appcompat.app.AppCompatActivity;

public class Calendar extends AppCompatActivity {

    private String year;
    private String month;
    private String day;
    private String dayOfWeek;
    private float totalTheDay;
    private float totalTheMonth;
    private float totalTheSpend;
    private float totalTheIncome;


    public Calendar() {

    }

    public Calendar(
            String year, String month, String day,
            String dayOfWeek, float totalTheDay, float totalTheMonth,
            float totalTheSpend, float totalTheIncome) {

        this.year = year;
        this.month = month;
        this.day = day;
        this.dayOfWeek = dayOfWeek;
        this.totalTheDay = totalTheDay;
        this.totalTheMonth = totalTheMonth;
        this.totalTheSpend = totalTheSpend;
        this.totalTheIncome = totalTheIncome;
    }

}
