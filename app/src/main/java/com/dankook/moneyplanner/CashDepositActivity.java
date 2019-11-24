package com.dankook.moneyplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class CashDepositActivity extends AppCompatActivity {

    InputMethodManager imm;
    EditText et_cash_deposit;
    Button btncashok;
    LinearLayout ll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_deposit);

        ll = (LinearLayout)findViewById(R.id.ll);
        btncashok = (Button)findViewById(R.id.btn_cash_ok);
        et_cash_deposit = (EditText)findViewById(R.id.Cash_Deposit);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);


        Intent myintent4 = getIntent();

        ImageButton btnBack = (ImageButton) findViewById(R.id.btnBack);
        Button btncashok = (Button) findViewById(R.id.btn_cash_ok);

        ll.setOnClickListener(myClickListener);
        btncashok.setOnClickListener(myClickListener);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Back", Toast.LENGTH_LONG).show();
                Intent myintent = new Intent(CashDepositActivity.this, MainActivity.class);
                startActivity(myintent);
                finish();
            }
        });

        btncashok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Back", Toast.LENGTH_LONG).show();
                Intent myintent = new Intent(CashDepositActivity.this, MainActivity.class);
                startActivity(myintent);
                finish();
            }
        });

    }

    View.OnClickListener myClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            hideKeyboard();
            switch (v.getId())
            {
                case R.id.ll :
                    break;

                case R.id.btn_cash_ok :
                    break;
            }
        }
    };

    private void hideKeyboard()
    {
        imm.hideSoftInputFromWindow(et_cash_deposit.getWindowToken(), 0);
    }


}
