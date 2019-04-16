package com.npdevs.drivermnnitcentral;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
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
    private TextInputEditText mob,pwd;
    private ProgressDialog progressDialog;
    private String loggedIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadPreferences();
        FirebaseApp.initializeApp(this);
        Button login = findViewById(R.id.logbtn);
        mob=findViewById(R.id.mob);
        pwd=findViewById(R.id.pwd);
        if(!loggedIn.isEmpty())
            mob.setText(loggedIn);
        progressDialog=new ProgressDialog(this);

        final TextInputLayout textInputLayout=findViewById(R.id.name_text_input1);
        textInputLayout.setErrorEnabled(true);
        Objects.requireNonNull(textInputLayout.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()!=10){
                    textInputLayout.setError("Enter 10 digit Mobile Number!");
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
        final TextInputLayout textInputLayout2=findViewById(R.id.name_text_input);
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

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Objects.requireNonNull(mob.getText()).toString().equals("98765432101") && Objects.requireNonNull(pwd.getText()).toString().equals("npdevs"))
                {
                    progressDialog.setMessage("Logging In...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    openAdminActivity();
                    progressDialog.cancel();
                }
                else
                {
                    String mobCheck=mob.getText().toString();
                    String pwdCheck=pwd.getText().toString();
                    final TextInputLayout textInputLayout=findViewById(R.id.name_text_input1);
                    final TextInputLayout textInputLayout2=findViewById(R.id.name_text_input);
                    if(mobCheck.length()!=10)
                    {
                        textInputLayout.setError("Enter 10 digit Mobile Number");
                        mob.requestFocus();
                        return;
                    }
                    if(pwdCheck.length()==0)
                    {
                        textInputLayout2.setError("Enter valid Password!");
                        pwd.requestFocus();
                        return;
                    }
                    progressDialog.setMessage("Logging In...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("drivers");
                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String mobile=mob.getText().toString();
                            MessageDigest digest = null;
                            try {
                                digest = MessageDigest.getInstance("SHA-256");
                            } catch (NoSuchAlgorithmException e) {
                                e.printStackTrace();
                            }
                            assert digest != null;
                            byte[] pasd = digest.digest(pwd.getText().toString().getBytes(StandardCharsets.UTF_8));
                            String password= Arrays.toString(pasd);
                            if(dataSnapshot.child(mobile).exists())
                            {
                                if(!mobile.isEmpty())
                                {
                                    Drivers login=dataSnapshot.child(mobile).getValue(Drivers.class);
                                    if(login.getPwd().equals(password))
                                    {
                                        Toast.makeText(getApplicationContext(), "Login success!", Toast.LENGTH_LONG).show();
                                        Intent k= new Intent(MainActivity.this,MapsActivity.class);
                                        k.putExtra("mobile",mobile);
                                        startActivity(k);
                                        progressDialog.cancel();
                                        savePreferences("STR",mobile);
                                        finish();
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(), "Wrong Password!", Toast.LENGTH_LONG).show();
                                        progressDialog.cancel();
                                    }
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(), "Enter mobile number!", Toast.LENGTH_LONG).show();
                                    progressDialog.cancel();
                                }
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "User is not registered!", Toast.LENGTH_LONG).show();
                                progressDialog.cancel();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(getApplicationContext(),
                                    "Process Cancelled!", Toast.LENGTH_LONG).show();
                            progressDialog.cancel();
                        }
                    });
                }
            }
        });
    }
    public void openAdminActivity()
    {
        Intent intent = new Intent(this,Admin.class);
        startActivity(intent);
    }
    private void savePreferences(String key, String value){
        SharedPreferences sharedPreferences = getSharedPreferences("Check",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }
    private void loadPreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences("Check",MODE_PRIVATE);
        loggedIn = sharedPreferences.getString("STR", "");
    }
}
