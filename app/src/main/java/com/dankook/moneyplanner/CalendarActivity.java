package com.dankook.moneyplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CalendarActivity extends AppCompatActivity {

    CalendarView calendarView;
    TextView spend, income, total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarView = findViewById(R.id.calendarView);
        spend = findViewById(R.id.spend);
        income = findViewById(R.id.income);
        total = findViewById(R.id.total);
    }

    public void clickCategories(View view) {
        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);
    }

    public void clickAccount(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
