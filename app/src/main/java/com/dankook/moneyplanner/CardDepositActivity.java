package com.dankook.moneyplanner;

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

import androidx.appcompat.app.AppCompatActivity;

public class CardDepositActivity extends AppCompatActivity {

    InputMethodManager imm;
    EditText et_card_deposit;
    Button btncardok;
    LinearLayout ll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_deposit);

        Intent myintent = getIntent();

        ll = (LinearLayout) findViewById(R.id.ll);
        btncardok = (Button) findViewById(R.id.btn_card_ok);
        et_card_deposit = (EditText) findViewById(R.id.Card_Deposit);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);


        ImageButton btnBack = (ImageButton) findViewById(R.id.btnBack);
        Button btncardOK = (Button) findViewById(R.id.btn_card_ok);

        ll.setOnClickListener(myClickListener);
        btncardok.setOnClickListener(myClickListener);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Back", Toast.LENGTH_LONG).show();
                Intent myintent = new Intent(CardDepositActivity.this, MainActivity.class);
                startActivity(myintent);
                finish();
            }
        });

        btncardOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_LONG).show();
                Intent myintent = new Intent(CardDepositActivity.this, MainActivity.class);
                startActivity(myintent);
                finish();
            }
        });

    }

    View.OnClickListener myClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            hideKeyboard();
            switch (v.getId()) {
                case R.id.ll:
                    break;

                case R.id.btn_card_ok:
                    break;
            }
        }
    };

    private void hideKeyboard() {
        imm.hideSoftInputFromWindow(et_card_deposit.getWindowToken(), 0);
    }


}
