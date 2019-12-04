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

import com.dankook.moneyplanner.model.Account;
import com.dankook.moneyplanner.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class CashDepositActivity extends AppCompatActivity implements Serializable {

    InputMethodManager imm;
    EditText et_card_deposit, txtSpend;
    Button btncardok;
    LinearLayout ll;
    String name, id, newBalancetxt, spend;
    float newBalance;
    private DatabaseReference mDatabase;
    User userModel;
    Account accountModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_deposit);

        txtSpend = findViewById(R.id.Cash_Deposit);

        ll = (LinearLayout) findViewById(R.id.ll);
        btncardok = (Button) findViewById(R.id.btn_cash_ok);
        et_card_deposit = (EditText) findViewById(R.id.Cash_Deposit);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        ImageButton btnBack = (ImageButton) findViewById(R.id.btnBack);
        Button btncardOK = (Button) findViewById(R.id.btn_cash_ok);

        mDatabase = FirebaseDatabase.getInstance().getReference("user");

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        userModel = (User) extras.getSerializable("user");
        accountModel = (Account) extras.getSerializable("account");

        ll.setOnClickListener(myClickListener);
        btncardok.setOnClickListener(myClickListener);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Back", Toast.LENGTH_LONG).show();
                Intent myintent = new Intent(CashDepositActivity.this, MainActivity.class);
                startActivity(myintent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

            }
        });

        btncardOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = userModel.getName();
                spend = txtSpend.getText().toString().trim();
                newBalance = accountModel.getBalance() + Float.parseFloat(spend);
                newBalancetxt = String.valueOf(newBalance);
                id = accountModel.getId();
                accountModel.setBalance(Float.toString(newBalance));

                try {
                    mDatabase.child(id).child("balance").setValue(newBalancetxt);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

                case R.id.btn_cash_ok:
                    break;
            }
        }
    };

    private void hideKeyboard() {
        imm.hideSoftInputFromWindow(et_card_deposit.getWindowToken(), 0);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
