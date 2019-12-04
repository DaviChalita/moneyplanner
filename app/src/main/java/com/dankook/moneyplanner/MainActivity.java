package com.dankook.moneyplanner;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.dankook.moneyplanner.model.Account;
import com.dankook.moneyplanner.model.Alarm;
import com.dankook.moneyplanner.model.Category;
import com.dankook.moneyplanner.model.User;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Serializable {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    public static final int RC_SIGN_IN = 1;
    private TextView txtWelcome, txtCash;
    private FirebaseUser user;
    private TextView txtTotalSpent;
    User userModel;
    private DatabaseReference mDatabase;
    Account accountModel;
    Switch switchAlarm;
    Alarm alarmModel;

    List<AuthUI.IdpConfig> providers = Arrays.asList(
            new AuthUI.IdpConfig.EmailBuilder().build()
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtWelcome = findViewById(R.id.txtViewUser);
        switchAlarm = findViewById(R.id.Alarm_switch);
        txtCash = findViewById(R.id.Cash_Account);
        txtTotalSpent = findViewById(R.id.textViewTotalSpent);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("user");
        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    userModel = new User();
                    userModel.setId(mDatabase.push().getKey());
                    userModel.setEmail(user.getEmail());
                    userModel.setName(user.getDisplayName());
                } else {
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setAvailableProviders(providers)
                                    .setIsSmartLockEnabled(false)
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };
    }


    public void clickDeposit(View view) {
        Toast.makeText(getApplicationContext(), "Cash Deposit page", Toast.LENGTH_LONG).show();
        Intent myIntent = new Intent(MainActivity.this, CashDepositActivity.class);
        Bundle extras = new Bundle();
        extras.putSerializable("user", userModel);
        extras.putSerializable("account", accountModel);
        myIntent.putExtras(extras);
        startActivity(myIntent);

    }

    public void clickWithdraw(View view) {
        System.out.println("account model balance "+accountModel.getBalance());
        Toast.makeText(getApplicationContext(), "Cash Withdraw page", Toast.LENGTH_LONG).show();
        Intent myIntent = new Intent(MainActivity.this, CashWithdrawActivity.class);
        Bundle extras = new Bundle();
        extras.putSerializable("user", userModel);
        extras.putSerializable("account", accountModel);
        extras.putSerializable("alarm", alarmModel);
        myIntent.putExtras(extras);
        startActivity(myIntent);
    }

    public void logout(View view) {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(MainActivity.this, "Logged out sucessfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        userModel = null;
        accountModel = null;
        alarmModel = null;
        mFirebaseAuth.addAuthStateListener(mAuthListener);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (user != null) {
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        User userModel = userSnapshot.getValue(User.class);
                        if (userModel.getEmail().equals(user.getEmail())) {
                            accountModel = userSnapshot.getValue(Account.class);
                            txtWelcome.setText(userModel.getName() + "'s");
                            txtCash.setText(Float.toString(accountModel.getBalance()));
                            txtTotalSpent.setText("  Total Spent: "+Float.toString(accountModel.getSpend()));
                            try {
                                alarmModel = userSnapshot.getValue(Alarm.class);
                                switchAlarm.setChecked(true);
                            } catch (Exception e) {
                                switchAlarm.setChecked(false);
                            }
                            return;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mFirebaseAuth.removeAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    public void clickCategories(View view) {
        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);

    }

    public void clickCalendar(View view) {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);

    }

    public void clickAlarmSwitch(View view) {
        if (switchAlarm.isChecked()) {
            Intent myIntent = new Intent(MainActivity.this, AlarmActivity.class);
            Bundle extras = new Bundle();
            System.out.println("Indo para tela alarme");
            System.out.println(userModel.getEmail());
            System.out.println(accountModel.getBalance());
            extras.putSerializable("user", userModel);
            extras.putSerializable("account", accountModel);
            myIntent.putExtra("user", userModel);
            myIntent.putExtra("account", accountModel);
            myIntent.putExtras(extras);
            startActivity(myIntent);
        } else {

            new AlertDialog.Builder(MainActivity.this).setTitle("Alarm off")
                    .setMessage("Do you want to turn the alarm off?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            switchAlarm.setChecked(false);
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    switchAlarm.setChecked(true);
                }
            }).show();
        }
    }
}