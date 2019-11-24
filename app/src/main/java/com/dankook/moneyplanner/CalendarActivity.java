package com.dankook.moneyplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CalendarActivity extends AppCompatActivity {

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

    public void clickCategories(View view) {
        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);

    }

    public void clickAccount(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }


}
