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
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.dankook.moneyplanner.model.Account;
import com.dankook.moneyplanner.model.Alarm;
import com.dankook.moneyplanner.model.Category;
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
    Alarm alarmModel;
    float newBalance, newSpend, spentInCategory;
    EditText txtWithdraw;
    private RadioGroup radioGroup;
    RadioButton foodBtn, shopBtn, leisBtn, transpBtn, etcBtn;
    String selectedRadBtn;
    private String spentInCategorytxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_withdraw);

        ImageButton btnBack = (ImageButton) findViewById(R.id.btnBack);
        Button btncardwithdrawok = (Button) findViewById(R.id.btn_cardwithdraw_ok);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
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
        System.out.println("Alarm " + alarmModel.getLimit());
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
               /* switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.imgbtnfood:
                        selectedRadBtn = "food";
                        break;
                    case R.id.imgbtnshopping:
                        selectedRadBtn = "shopping";
                        break;
                    case R.id.imgbtnleisure:
                        selectedRadBtn = "leisure";
                        break;
                    case R.id.imgbtntransport:
                        selectedRadBtn = "transport";
                        break;
                    case R.id.imgbtnetc:
                        selectedRadBtn = "etc";
                        break;
                    default:
                        selectedRadBtn = "unselected";
                        break;
                }*/

                name = userModel.getName();
                withdraw = txtWithdraw.getText().toString().trim();
                id = accountModel.getId();

                try {
                    if (alarmModel.getLimit() > (accountModel.getSpend() + Float.parseFloat(withdraw))) {
                        newBalance = accountModel.getBalance() - Float.parseFloat(withdraw);
                        System.out.println("accountModel.getSpend() "+accountModel.getSpend());
                        newSpend = accountModel.getSpend() + Float.parseFloat(withdraw);
                        newSpendTxt = Float.toString(newSpend);
                        accountModel.setSpend(newSpendTxt);
                        newBalancetxt = Float.toString(newBalance);
                        accountModel.setBalance(Float.toString(newBalance));
                        /*try {
                            mDatabase.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot userSnap : dataSnapshot.getChildren()) {
                                        User userSnapshot = userSnap.getValue(User.class);
                                        if (userModel.getEmail().equals(userSnapshot.getEmail())) {
                                            Category category = userSnap.getValue(Category.class);
                                            switch (selectedRadBtn) {
                                                case "food":
                                                    spentInCategory = category.getFood() + Float.parseFloat(withdraw);
                                                    break;
                                                case "shopping":
                                                    spentInCategory = category.getShopping() + Float.parseFloat(withdraw);
                                                    break;
                                                case "leisure":
                                                    spentInCategory = category.getLeisure() + Float.parseFloat(withdraw);
                                                    break;
                                                case "transport":
                                                    spentInCategory = category.getTransport() + Float.parseFloat(withdraw);
                                                    break;
                                                case "etc":
                                                    spentInCategory = category.getEtc() + Float.parseFloat(withdraw);
                                                    break;
                                                default:
                                                    break;
                                            }
                                            spentInCategorytxt = Float.toString(spentInCategory);
                                            mDatabase.child(id).child(selectedRadBtn).setValue(spentInCategorytxt);*/
                                            mDatabase
                                                    .child(id)
                                                    .child("balance")
                                                    .setValue(newBalancetxt);
                                            mDatabase.child(id).child("spend").setValue(newSpendTxt);
                                            finish();
                                            /* }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        } catch (Exception e) {
                        }*/
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
              //  finish();
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