package com.npdevs.mnnitcentral;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private EditText regno,pwd;
    private ProgressDialog progressDialog;
    private String registrationNumber,password,loggedIn,group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadPreferences();
        regno=findViewById(R.id.editText);
        pwd=findViewById(R.id.editText3);
        schedulealarm();
        group="000";
        if(!loggedIn.isEmpty())
            regno.setText(loggedIn);
        final TextInputLayout textInputLayout=findViewById(R.id.name_text_input1);
        textInputLayout.setErrorEnabled(true);
        Objects.requireNonNull(textInputLayout.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()!=8){
                    textInputLayout.setError("Enter 8 digit Registration Number!");
                }
                else
                {
                    textInputLayout.setError(null);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        final TextInputLayout textInputLayout2=findViewById(R.id.name_text_input2);
        textInputLayout2.setErrorEnabled(true);
        Objects.requireNonNull(textInputLayout2.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==0){
                    textInputLayout2.setError("Enter valid password!");
                }
                else
                {
                    textInputLayout2.setError(null);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        Button register = (Button) findViewById(R.id.button2);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterActivity();
            }
        });
        Button login = (Button) findViewById(R.id.button);
        FirebaseApp.initializeApp(this);
        progressDialog=new ProgressDialog(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrationNumber=regno.getText().toString();
                password=pwd.getText().toString();
                if(registrationNumber.isEmpty())
                {
                    textInputLayout.setError("Enter valid Registration Number!");
                    regno.requestFocus();
                    return;
                }
                if(password.isEmpty())
                {
                    textInputLayout2.setError("Enter valid Password!");
                    pwd.requestFocus();
                    return;
                }
                if(registrationNumber.equals("M987654321") && password.equals("npdevs"))
                {
                    Intent intent = new Intent(MainActivity.this,MessAdmin.class);
                    startActivity(intent);
                }
                else if(registrationNumber.equals("E987654321") && password.equals("npdevs"))
                {
                    Intent intent = new Intent(MainActivity.this,EventAdmin.class);
                    startActivity(intent);
                }
                else if(registrationNumber.equals("T987654321")&&password.equals("npdevs"))
                {
                    Intent intent = new Intent(MainActivity.this,TimeTableAdd.class);
                    startActivity(intent);
                }
                else {
                    progressDialog.setMessage("Logging In...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    openFrontActivity();
                }
            }
        });
    }
    public void openRegisterActivity()
    {
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
    }
    public void openFrontActivity()
    {
        DatabaseReference myRef= FirebaseDatabase.getInstance().getReference("users");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String registrationNumber=regno.getText().toString();
                String password=pwd.getText().toString();
                MessageDigest digest = null;
                try {
                    digest = MessageDigest.getInstance("SHA-256");
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                assert digest != null;
                byte[] pasd = digest.digest(password.getBytes(StandardCharsets.UTF_8));
                password= Arrays.toString(pasd);
                if(dataSnapshot.child(registrationNumber).exists())
                {
                    if(!registrationNumber.isEmpty())
                    {
                        Users login=dataSnapshot.child(registrationNumber).getValue(Users.class);
                        if(login.getPwd().equals(password))
                        {
                            Toast.makeText(getApplicationContext(),"Login Success!",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this,Front.class);
                            intent.putExtra("regno",regno.getText().toString());
                            startActivity(intent);
                            progressDialog.cancel();
                            savePreferences("STR",registrationNumber);
                            group=login.getGrp();
                            progressDialog.setMessage("Saving Timetable...");
                            progressDialog.setCancelable(false);
                            progressDialog.show();
                            clearTable();
                            saveTable();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Wrong Password!",Toast.LENGTH_LONG).show();
                            progressDialog.cancel();
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Enter Registration number!",Toast.LENGTH_LONG).show();
                        progressDialog.cancel();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"User is not registered!",Toast.LENGTH_LONG).show();
                    progressDialog.cancel();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Process Cancelled!",Toast.LENGTH_LONG).show();
                progressDialog.cancel();
            }
        });

    }
    private void savePreferences(String key,String value)
    {
        SharedPreferences sharedPreferences=getSharedPreferences("CheckNP",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }
    private void schedulealarm() {

        // Construct an intent that will execute the AlarmReceiver
        Intent intent = new Intent(this, AlarmReciever.class);
        // Create a PendingIntent to be triggered when the alarm goes off
        final PendingIntent pIntent = PendingIntent.getBroadcast(this, AlarmReciever.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        // Setup periodic alarm every every half hour from this point onwards
        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        // First parameter is the type: ELAPSED_REALTIME, ELAPSED_REALTIME_WAKEUP, RTC_WAKEUP
        // Interval can be INTERVAL_FIFTEEN_MINUTES, INTERVAL_HALF_HOUR, INTERVAL_HOUR, INTERVAL_DAY
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime(),
                1000*40, pIntent);
    }
    private void loadPreferences()
    {
        SharedPreferences sharedPreferences=getSharedPreferences("CheckNP",MODE_PRIVATE);
        loggedIn=sharedPreferences.getString("STR","");
        if(loggedIn==null)
            loggedIn="no";
    }
    private void clearTable()
    {
        for(int day=1;day<=5;day++) {
            SharedPreferences preferences = getSharedPreferences("tablesave"+day, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();
        }
    }
    private void saveTable()
    {
        final DatabaseReference myRef=FirebaseDatabase.getInstance().getReference("timetable/"+group);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(int day=1;day<=5;day++) {
                    SharedPreferences sharedPreferences=getSharedPreferences("tablesave"+day,MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    for(int hour=8;hour<=17;hour++) {
                        if(dataSnapshot.child("" + day).child(hour+"").child("class").exists()
                                && dataSnapshot.child("" + day).child(hour+"").child("room").exists()) {
                            editor.putString(day + " " + hour, dataSnapshot.child("" + day).child(hour + "").child("class").getValue().toString() + " " +
                                    dataSnapshot.child("" + day).child(hour + "").child("room").getValue().toString());
                            editor.apply();
                            Toast.makeText(getApplicationContext(),"Time Table Saved for "+group,Toast.LENGTH_LONG).show();
                        }
                        if(hour==11)
                            hour=13;
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        progressDialog.cancel();
    }
}
