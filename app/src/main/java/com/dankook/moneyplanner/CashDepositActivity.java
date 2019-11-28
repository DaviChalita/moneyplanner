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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.dankook.moneyplanner.model.Account;
import com.dankook.moneyplanner.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;

public class CashDepositActivity extends AppCompatActivity implements Serializable {

    InputMethodManager imm;
    EditText et_card_deposit, txtSpend;
    Button btncardok;
    LinearLayout ll;
    String email, name, balance, id, newBalancetxt, spend;
    private FirebaseUser user;
    float newBalance;
    private DatabaseReference mDatabase;
    private FirebaseAuth mFirebaseAuth, fbAuth;
    User userModel;
    Account accountModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_deposit);

        txtSpend = findViewById(R.id.Cash_Deposit);
        Intent myintent = getIntent();

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
        System.out.println(userModel.getEmail());
        System.out.println(accountModel.getBalance());
        //userModel = (User) intent.getSerializableExtra("user");
        //accountModel = (Account) intent.getSerializableExtra("account");

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
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println(userModel.getEmail());
                name = userModel.getName();
                spend = txtSpend.getText().toString().trim();
                newBalance = accountModel.getBalance() + Float.parseFloat(spend);
                newBalancetxt = String.valueOf(newBalance);
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
                                return;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_LONG).show();
                Intent myintent = new Intent(CashDepositActivity.this, MainActivity.class);
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
        mFirebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser userStart = FirebaseAuth.getInstance().getCurrentUser();

    }

}
