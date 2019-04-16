package com.npdevs.drivermnnitcentral;

import android.app.ProgressDialog;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
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

public class Admin extends AppCompatActivity {

    TextInputEditText name,mob,cab,pwd;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        FirebaseApp.initializeApp(this);
        progressDialog=new ProgressDialog(this);

        final TextInputLayout textInputLayout=findViewById(R.id.name_text_input3);
        textInputLayout.setErrorEnabled(true);
        Objects.requireNonNull(textInputLayout.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==0){
                    textInputLayout.setError("Enter valid Name!");
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

        final TextInputLayout textInputLayout2=findViewById(R.id.name_text_input1);
        textInputLayout2.setErrorEnabled(true);
        Objects.requireNonNull(textInputLayout2.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()<6){
                    textInputLayout2.setError("Enter password of least length 6!");
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

        final TextInputLayout textInputLayout3=findViewById(R.id.name_text_input2);
        textInputLayout3.setErrorEnabled(true);
        Objects.requireNonNull(textInputLayout3.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==0){
                    textInputLayout3.setError("Enter valid Cab Number!");
                }
                else
                {
                    textInputLayout3.setError(null);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        final TextInputLayout textInputLayout4=findViewById(R.id.name_text_input4);
        textInputLayout4.setErrorEnabled(true);
        Objects.requireNonNull(textInputLayout4.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()!=10){
                    textInputLayout4.setError("Enter 10 digit Mobile Number!");
                }
                else
                {
                    textInputLayout4.setError(null);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference myRef= FirebaseDatabase.getInstance().getReference("drivers");
                name=findViewById(R.id.name);
                mob=findViewById(R.id.mob);
                cab=findViewById(R.id.cab);
                pwd=findViewById(R.id.pwd);
                String n=name.getText().toString(),
                        p=pwd.getText().toString(),
                        c=cab.getText().toString(),
                        m=mob.getText().toString();
                if(n.isEmpty())
                {
                    textInputLayout.setError("Enter valid Name!");
                    return;
                }
                if(p.length()<6)
                {
                    textInputLayout2.setError("Enter password of least length 6!");
                    return;
                }
                if(c.isEmpty())
                {
                    textInputLayout3.setError("Enter valid Cab Number!");
                    return;
                }
                if(m.length()!=10)
                {
                    textInputLayout4.setError("Enter 10 digit Mobile Number!");
                    return;
                }
                MessageDigest digest = null;
                try {
                    digest = MessageDigest.getInstance("SHA-256");
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                assert digest != null;
                byte[] pp = digest.digest(p.getBytes(StandardCharsets.UTF_8));
                p= Arrays.toString(pp);
                progressDialog.setMessage("Registering...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                final Drivers driver=new Drivers(n,p,c,m,"0","0","false");
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(driver.getMob()).exists())
                        {
                            Toast.makeText(getApplicationContext(),"User already registered!",Toast.LENGTH_LONG).show();
                            progressDialog.cancel();
                        }
                        else
                        {
                            myRef.child(driver.getMob()).setValue(driver);
                            Toast.makeText(getApplicationContext(),
                                    "Registration Successful!", Toast.LENGTH_LONG).show();
                            progressDialog.cancel();
                            finish();
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
        });
    }
}
