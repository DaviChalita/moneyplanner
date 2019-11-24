package com.dankook.moneyplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CategoryActivity extends AppCompatActivity {

    AnyChartView anyChartView;
    Scanner scan = new Scanner(System.in);

    String[] category = {"Traffic", "Food", "Leisure", "shopping", "etc"};
    int[] value = {15, 20, 40, 50, 35};
    //  the value must be downloaded from firebase data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        anyChartView = findViewById(R.id.any_chart_view);

        setupPieChart();
    }

    public void setupPieChart() {

        Pie pie = AnyChart.pie();
        List<DataEntry> dataEntries = new ArrayList<>();

/*
        for(int i = 0; i<value.length; i++) {
            value[i] = scan.nextInt();
        }
*/

        for (int i = 0; i < category.length; i++) {
            dataEntries.add(new ValueDataEntry(category[i], value[i]));
        }

        pie.data(dataEntries);
        anyChartView.setChart(pie);

    }

    public void clickAccount(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void clickCalendar(View view) {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);

    }
}

