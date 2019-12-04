package com.dankook.moneyplanner;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.dankook.moneyplanner.model.Account;
import com.dankook.moneyplanner.model.Alarm;
import com.dankook.moneyplanner.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CashWithdrawActivity extends AppCompatActivity {

    String name, withdraw, id, newBalancetxt, newSpendTxt;
    private DatabaseReference mDatabase;
    User userModel;
    Account accountModel;
    Alarm alarmModel;
    float newBalance, newSpend;
    EditText txtWithdraw;
    private RadioGroup radioGroup;
    RadioButton foodBtn, shopBtn, leisBtn, transpBtn, etcBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_withdraw);

        ImageButton btnBack = findViewById(R.id.btnBack);
        Button btncardwithdrawok = findViewById(R.id.btn_cardwithdraw_ok);

        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(radioGroupButtonChangeListener);
        txtWithdraw = findViewById(R.id.Cash_Withdraw);
        foodBtn = findViewById(R.id.imgbtnfood);
        shopBtn = findViewById(R.id.imgbtnshopping);
        leisBtn = findViewById(R.id.imgbtnleisure);
        transpBtn = findViewById(R.id.imgbtntransport);
        etcBtn = findViewById(R.id.imgbtnetc);

        mDatabase = FirebaseDatabase.getInstance().getReference("user");

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        userModel = (User) extras.getSerializable("user");
        accountModel = (Account) extras.getSerializable("account");
        alarmModel = (Alarm) extras.getSerializable("alarm");
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
                name = userModel.getName();
                withdraw = txtWithdraw.getText().toString().trim();
                id = accountModel.getId();

                try {
                    if ((alarmModel.warnTheUser((accountModel.getSpend() + Float.parseFloat(withdraw)),
                            alarmModel.getLimit()))) {
                        newBalance = accountModel.getBalance() - Float.parseFloat(withdraw);
                        newSpend = accountModel.getSpend() + Float.parseFloat(withdraw);
                        newSpendTxt = Float.toString(newSpend);
                        accountModel.setSpend(newSpendTxt);
                        newBalancetxt = Float.toString(newBalance);
                        accountModel.setBalance(Float.toString(newBalance));
                        mDatabase.child(id).child("balance").setValue(newBalancetxt);
                        mDatabase.child(id).child("spend").setValue(newSpendTxt);
                        finish();
                    } else {
                        new AlertDialog.Builder(CashWithdrawActivity.this).setTitle("Not enough money")
                                .setMessage("The money spent surpass the limit setted!")
                                .setPositiveButton("I understand", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                    }
                                }).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            if (i == R.id.imgbtnetc) {
                Toast.makeText(CashWithdrawActivity.this, "clicked etc", Toast.LENGTH_SHORT).show();
            } else if (i == R.id.imgbtnfood) {
                Toast.makeText(CashWithdrawActivity.this, "clicked food", Toast.LENGTH_SHORT).show();
            } else if (i == R.id.imgbtnleisure) {
                Toast.makeText(CashWithdrawActivity.this, "clicked leisure", Toast.LENGTH_SHORT).show();
            } else if (i == R.id.imgbtnshopping) {
                Toast.makeText(CashWithdrawActivity.this, "clicked shopping", Toast.LENGTH_SHORT).show();
            } else if (i == R.id.imgbtntransport) {
                Toast.makeText(CashWithdrawActivity.this, "clicked transportation", Toast.LENGTH_SHORT).show();
            }
        }
    };
}