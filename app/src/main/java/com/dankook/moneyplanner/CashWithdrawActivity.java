package com.dankook.moneyplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.dankook.moneyplanner.model.Account;
import com.dankook.moneyplanner.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CashWithdrawActivity extends AppCompatActivity {

    String name, withdraw, balance, id, newBalancetxt, email, newSpendTxt;
    private DatabaseReference mDatabase;
    User userModel;
    Account accountModel;
    float newBalance, newSpend;
    EditText txtWithdraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_withdraw);

        Intent myintent2 = getIntent();

        ImageButton btnBack = (ImageButton) findViewById(R.id.btnBack);
        Button btncardwithdrawok = (Button) findViewById(R.id.btn_cardwithdraw_ok);
        txtWithdraw = findViewById(R.id.Cash_Withdraw);
        final ImageButton imgbtnfood = (ImageButton) findViewById(R.id.imgbtnfood);
        final ImageButton imgbtnshopping = (ImageButton) findViewById(R.id.imgbtnshopping);
        final ImageButton imgbtnleisure = (ImageButton) findViewById(R.id.imgbtnleisure);
        final ImageButton imgbtntransport = (ImageButton) findViewById(R.id.imgbtntransport);
        final ImageButton imgbtnetc = (ImageButton) findViewById(R.id.imgbtnetc);
        final int[] CHECK_NUM = new int[1];
        CHECK_NUM[0] = 0;

        mDatabase = FirebaseDatabase.getInstance().getReference("user");

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        userModel = (User) extras.getSerializable("user");
        accountModel = (Account) extras.getSerializable("account");
        System.out.println("foi pra tela de withdraw");
        System.out.println(userModel.getEmail());
        System.out.println(accountModel.getBalance());

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

                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println(userModel.getEmail());
                name = userModel.getName();
                withdraw = txtWithdraw.getText().toString().trim();
                newBalance = accountModel.getBalance() - Float.parseFloat(withdraw);
                System.out.println("))))))))))))))))))))))))))))))))))))))");
                newSpend = accountModel.getSpend()+Float.parseFloat(withdraw);
                newSpendTxt = Float.toString(newSpend);
                System.out.println(newSpend);
                accountModel.setSpend(newSpendTxt);
                newBalancetxt = Float.toString(newBalance);
                id = accountModel.getId();
                accountModel.setBalance(Float.toString(newBalance));
                System.out.println(accountModel.getBalance());

                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                            User userSnapshotValue = userSnapshot.getValue(User.class);
                            if (userSnapshotValue.getEmail().equals(userModel.getEmail())) {
                                System.out.println(userSnapshotValue.getEmail());
                                System.out.println(email);
                                System.out.println(userSnapshotValue.getId());
                                mDatabase
                                        .child(userSnapshotValue.getId())
                                        .child("balance")
                                        .setValue(newBalancetxt);
                                mDatabase.child(userSnapshotValue.getId()).child("spend").setValue(newSpendTxt);
                                return;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

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



