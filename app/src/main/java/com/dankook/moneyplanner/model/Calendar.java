package com.dankook.moneyplanner.model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.TextView;

import com.dankook.moneyplanner.MainActivity;
import com.dankook.moneyplanner.R;

public class Calendar extends AppCompatActivity {

    private String year;
    private String month;
    private String day;
    private String dayOfWeek;
    private float totalTheDay;
    private float totalTheMonth;
    private float totalTheSpend;
    private float totalTheIncome;



    public Calendar(){

    }

    public Calendar(
            String year, String month, String day,
            String dayOfWeek, float totalTheDay, float totalTheMonth,
            float totalTheSpend,float totalTheIncome){

        this.year = year;
        this.month = month;
        this.day = day;
        this.dayOfWeek = dayOfWeek;
        this.totalTheDay = totalTheDay;
        this.totalTheMonth = totalTheMonth;
        this.totalTheSpend = totalTheSpend;
        this.totalTheIncome = totalTheIncome;
    }


     CalendarView calendarView;
     TextView spend;
    TextView income;
    TextView total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        spend = (TextView) findViewById(R.id.spend);
        income = (TextView) findViewById(R.id.income);
        total = (TextView) findViewById(R.id.total);

       // User userModel = userSnapshot.getValue(User.class);

      //  spend.setText();
      //  income.setText();
      //  total.setText(userModel.getBalance());



    }
}
