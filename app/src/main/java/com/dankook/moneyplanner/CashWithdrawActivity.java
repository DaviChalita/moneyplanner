package com.dankook.moneyplanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.RadioGroup;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

public class CashWithdrawActivity extends AppCompatActivity {

    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_withdraw);

        Intent myintent2 = getIntent();

        ImageButton btnBack = (ImageButton) findViewById(R.id.btnBack);
        Button btncardwithdrawok = (Button) findViewById(R.id.btn_cardwithdraw_ok);


        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(radioGroupButtonChangeListener);



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Back", Toast.LENGTH_LONG).show();
                Intent myintent = new Intent(CashWithdrawActivity.this, MainActivity.class);
                startActivity(myintent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();
            }
        });

        btncardwithdrawok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_LONG).show();
                Intent myintent = new Intent(CashWithdrawActivity.this, MainActivity.class);
                startActivity(myintent);
                finish();
            }
        });

    }

    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            if(i == R.id.imgbtnetc){
                Toast.makeText(CashWithdrawActivity.this, "clicked ect", Toast.LENGTH_SHORT).show();
            }
            else if(i == R.id.imgbtnfood){
                Toast.makeText(CashWithdrawActivity.this, "clicked food", Toast.LENGTH_SHORT).show();
            }
            else if(i == R.id.imgbtnleisure){
                Toast.makeText(CashWithdrawActivity.this, "clicked leisure", Toast.LENGTH_SHORT).show();
            }
            else if(i == R.id.imgbtnshopping){
                Toast.makeText(CashWithdrawActivity.this, "clicked shopping", Toast.LENGTH_SHORT).show();
            }
            else if(i == R.id.imgbtntransport){
                Toast.makeText(CashWithdrawActivity.this, "clicked transportation", Toast.LENGTH_SHORT).show();
            }

        }
    };



}



