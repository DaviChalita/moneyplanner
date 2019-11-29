package com.dankook.moneyplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.dankook.moneyplanner.model.Account;
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
    private EditText txtBalance, txtSpends;
    Button btBalance, btSpends;
    private FirebaseUser user;
    User userModel;
    private DatabaseReference mDatabase;
    String email, name, balance, id, newBalancetxt, spend;
    float newBalance;
    Account accountModel;
    Switch switchAlarm;

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
        /*txtBalance = findViewById(R.id.editTextBalance);
        btBalance = findViewById(R.id.buttonBalance);
        txtSpends = findViewById(R.id.editTextSpends);
        btSpends = findViewById(R.id.buttonSpends); */

        Button Cash_Deposit = (Button) findViewById(R.id.Cash_Deposit);
        Button Cash_Withdraw = (Button) findViewById(R.id.Cash_Withdraw);

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

       /* mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (user != null) {
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        User userModel = userSnapshot.getValue(User.class);
                        if (userModel.getEmail().equals(user.getEmail())) {
                            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                            System.out.println(userModel.getId());
                            System.out.println(userModel.getEmail());
                            System.out.println(userModel.getName());
                            accountModel = userSnapshot.getValue(Account.class);
                            System.out.println(accountModel.getId());
                            System.out.println(accountModel.getBalance());

                            txtWelcome.setText(userModel.getName() + "'s");
                            txtCash.setText(Float.toString(accountModel.getBalance()));
                            return;
                        }
                    }

                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

        //  addUser();

     /*   Cash_Withdraw.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Cash Withdraw page", Toast.LENGTH_LONG).show();
                Intent myintent = new Intent(MainActivity.this, CashWithdrawActivity.class);
                startActivity(myintent);
            }
        });*/

        /*btBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();
            }
        });
        btSpends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSpends();
            }
        });*/

    }


    public void clickDeposit(View view) {
        Toast.makeText(getApplicationContext(), "Cash Deposit page", Toast.LENGTH_LONG).show();
        //addExtra, save the user in firebase with balance = 0 if null
        Intent myIntent = new Intent(MainActivity.this, CashDepositActivity.class);
        System.out.println("###############################");
        System.out.println(userModel.getEmail());
        System.out.println(userModel.getName());
        System.out.println(accountModel.getBalance());
        //System.out.println(accountModel.getBalance());
        Bundle extras = new Bundle();
        extras.putSerializable("user", userModel);
        extras.putSerializable("account", accountModel);
        //myIntent.putExtra("user", userModel);
        //myIntent.putExtra("account", accountModel);
        myIntent.putExtras(extras);
        startActivity(myIntent);

    }

    public void clickWithdraw(View view) {
        Toast.makeText(getApplicationContext(), "Cash Withdraw page", Toast.LENGTH_LONG).show();
        //addExtra, save the user in firebase with balance = 0 if null
        Intent myIntent = new Intent(MainActivity.this, CashWithdrawActivity.class);
        System.out.println("$$$$$$$$$$$$$$$$");
        System.out.println(userModel);
        System.out.println(userModel.getEmail());
        System.out.println(userModel.getName());
        System.out.println(accountModel.getBalance());
        Bundle extras = new Bundle();
        extras.putSerializable("user", userModel);
        extras.putSerializable("account", accountModel);
        myIntent.putExtra("user", userModel);
        myIntent.putExtra("account", accountModel);
        myIntent.putExtras(extras);
        startActivity(myIntent);
    }


 /*   private void addUser() {
        email = user.getEmail();
        name = user.getDisplayName();
        balance = "0";
        id = mDatabase.push().getKey();
        userModel = new User(id, email, name);
        accountModel = new Account(id, Float.parseFloat(balance), userModel);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    User userSnapshotValue = userSnapshot.getValue(User.class);
                    if (userSnapshotValue.getEmail().equals(email)) {
                        //System.out.println(userSnapshotValue.getEmail());
                        //System.out.println(email);
                        //System.out.println(userSnapshotValue.getId());
                        //  mDatabase.child(userSnapshotValue.getId()).child("balance").setValue(balance);
                        //txtBalance.getText().clear();
                        return;
                    }
                }
                mDatabase.child(id).setValue(userModel);
                mDatabase.child(id).child("balance").setValue(balance);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }*/

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
        mFirebaseAuth.addAuthStateListener(mAuthListener);
        //txtCash.setText(Float.toString(accountModel.getBalance()));
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (user != null) {
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        User userModel = userSnapshot.getValue(User.class);
                        if (userModel.getEmail().equals(user.getEmail())) {
                            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                            System.out.println(userModel.getId());
                            System.out.println(userModel.getEmail());
                            System.out.println(userModel.getName());
                            accountModel = userSnapshot.getValue(Account.class);
                            System.out.println(accountModel.getId());
                            System.out.println(accountModel.getBalance());
                            System.out.println(accountModel.getSpend());
                            txtWelcome.setText(userModel.getName() + "'s");
                            txtCash.setText(Float.toString(accountModel.getBalance()));
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
        /*mFirebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser userStart = FirebaseAuth.getInstance().getCurrentUser();
        System.out.println("#################################################");
        System.out.println(userStart.getEmail());
        if (userStart != null) {
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        User userModel = userSnapshot.getValue(User.class);
                        accountModel = userSnapshot.getValue(Account.class);
                        if (userModel.getEmail().equals(userStart.getEmail())) {

                            txtWelcome.setText(userModel.getName() + "'s Total Account");

                            txtCash.setText(Float.toString(accountModel.getBalance()));
                        }
                    }
                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } else {
            //dont retrieve the balance
        }*/
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
            Intent intent = new Intent(this, AlarmActivity.class);
            startActivity(intent);
        }

    }

}

