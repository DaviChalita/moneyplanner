package com.dankook.moneyplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CashWithdrawActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_withdraw);

        Intent myintent2 = getIntent();

        ImageButton btnBack = (ImageButton) findViewById(R.id.btnBack);
        Button btncardwithdrawok = (Button) findViewById(R.id.btn_cardwithdraw_ok);

        final ImageButton imgbtnfood = (ImageButton) findViewById(R.id.imgbtnfood);
        final ImageButton imgbtnshopping = (ImageButton) findViewById(R.id.imgbtnshopping);
        final ImageButton imgbtnleisure = (ImageButton) findViewById(R.id.imgbtnleisure);
        final ImageButton imgbtntransport = (ImageButton) findViewById(R.id.imgbtntransport);
        final ImageButton imgbtnetc = (ImageButton) findViewById(R.id.imgbtnetc);
        final int[] CHECK_NUM = new int[1];
        CHECK_NUM[0] = 0;


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

//when the category is clicked, selected category's edge is marked.
        ImageButton.OnClickListener onClickListener = new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.imgbtnfood:

                        if (CHECK_NUM[0] == 0) {
                            imgbtnfood.setSelected(true);
                            CHECK_NUM[0] = 1;
                        } else {
                            imgbtnfood.setSelected(false);
                            CHECK_NUM[0] = 0;
                        }
                        break;

                    case R.id.imgbtnshopping:

                        if (CHECK_NUM[0] == 0) {
                            imgbtnshopping.setSelected(true);
                            CHECK_NUM[0] = 1;
                        } else {
                            imgbtnshopping.setSelected(false);
                            CHECK_NUM[0] = 0;
                        }
                        break;
                    case R.id.imgbtnleisure:

                        if (CHECK_NUM[0] == 0) {
                            imgbtnleisure.setSelected(true);
                            CHECK_NUM[0] = 1;
                        } else {
                            imgbtnleisure.setSelected(false);
                            CHECK_NUM[0] = 0;
                        }
                        break;
                    case R.id.imgbtntransport:

                        if (CHECK_NUM[0] == 0) {
                            imgbtntransport.setSelected(true);
                            CHECK_NUM[0] = 1;
                        } else {
                            imgbtntransport.setSelected(false);
                            CHECK_NUM[0] = 0;
                        }
                        break;
                    case R.id.imgbtnetc:

                        if (CHECK_NUM[0] == 0) {
                            imgbtnetc.setSelected(true);
                            CHECK_NUM[0] = 1;
                        } else {
                            imgbtnetc.setSelected(false);
                            CHECK_NUM[0] = 0;
                        }
                        break;
                }
            }
        };

        imgbtnfood.setOnClickListener(onClickListener);
        imgbtnshopping.setOnClickListener(onClickListener);
        imgbtnleisure.setOnClickListener(onClickListener);
        imgbtntransport.setOnClickListener(onClickListener);
        imgbtnetc.setOnClickListener(onClickListener);
    }
}



