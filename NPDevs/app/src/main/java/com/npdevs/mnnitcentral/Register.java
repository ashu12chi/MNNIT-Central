package com.npdevs.mnnitcentral;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

public class Register extends AppCompatActivity {
    private EditText name,regno,hostel,ac,pwd;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        FirebaseApp.initializeApp(this);
        progressDialog=new ProgressDialog(this);
        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=findViewById(R.id.name);
                regno=findViewById(R.id.regno);
                hostel=findViewById(R.id.hostel);
                ac=findViewById(R.id.ac);
                pwd=findViewById(R.id.pwd);
                String n,r,h,a,p;
                n=name.getText().toString();
                r=regno.getText().toString();
                h=hostel.getText().toString();
                p=pwd.getText().toString();
                a=ac.getText().toString();
                MessageDigest digest = null;
                try {
                    digest = MessageDigest.getInstance("SHA-256");
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                assert digest != null;
                byte[] pp = digest.digest(p.getBytes(StandardCharsets.UTF_8));
                p= Arrays.toString(pp);
                if(n.isEmpty())
                {
                    name.setError("Enter valid name!");
                    name.requestFocus();
                    return;
                }
                if(r.isEmpty())
                {
                    regno.setError("Enter 8 length registration number!");
                    regno.requestFocus();
                    return;
                }
                if(r.length()!=8)
                {
                    regno.setError("Enter 8 length registration number!");
                    regno.requestFocus();
                    return;
                }
                if(h.isEmpty())
                {
                    hostel.setError("Enter valid name!");
                    hostel.requestFocus();
                    return;
                }
                if(a.isEmpty())
                {
                    ac.setError("Enter 16 digits!");
                    ac.requestFocus();
                    return;
                }
                if(a.length()!=2)
                {
                    ac.setError("Enter valid Group Name!");
                    ac.requestFocus();
                    return;
                }
                if(p.isEmpty())
                {
                    pwd.setError("Enter at least 6 length password!");
                    pwd.requestFocus();
                    return;
                }
                if(p.length()<6)
                {
                    pwd.setError("Enter at least 6 length password!");
                    pwd.requestFocus();
                    return;
                }
                progressDialog.setMessage("Registering...");
                progressDialog.show();
                final DatabaseReference myRef=FirebaseDatabase.getInstance().getReference("users");
                final Users user=new Users(n,r,h,a,p,"0","0","","false","0","true");
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(user.getRegno()).exists())
                        {
                            Toast.makeText(getApplicationContext(),"User already registered!",Toast.LENGTH_LONG).show();
                            progressDialog.cancel();
                        }
                        else
                        {
                            myRef.child(user.getRegno()).setValue(user);
                            Toast.makeText(getApplicationContext(),"Registration Successful!",Toast.LENGTH_LONG).show();
                            progressDialog.cancel();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(),"Process Cancelled!",Toast.LENGTH_LONG).show();
                        progressDialog.cancel();
                    }
                });
            }
        });
    }
}
