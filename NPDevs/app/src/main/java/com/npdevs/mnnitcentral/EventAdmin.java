package com.npdevs.mnnitcentral;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class EventAdmin extends Activity {
    private DatabaseReference dbrs;
    private Button b;
    private EditText et;
    private ProgressDialog progressDialog;
    int n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_admin);
        dbrs = FirebaseDatabase.getInstance().getReference("eventsnotice");
        b=findViewById(R.id.submit);
        progressDialog=new ProgressDialog(this);
        et=findViewById(R.id.form);
        et.setMovementMethod(new ScrollingMovementMethod());
        dbrs.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("no").exists())
                    n=Integer.parseInt(Objects.requireNonNull(dataSnapshot.child("no").getValue()).toString());
                else
                {
                    n=0;
                    dbrs.child("no").setValue(n+"");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(EventAdmin.this, "Process Cancelled", Toast.LENGTH_LONG).show();
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Adding Message...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                addFeedback();
            }
        });
    }
    private void addFeedback() {
        String form = et.getText().toString().trim();
        if (!form.isEmpty()) {
            dbrs.child("no").setValue(++n+"");
            dbrs.child(n+"").setValue(form);
            Toast.makeText(this, "Message added", Toast.LENGTH_LONG).show();
            progressDialog.cancel();
        }
        else
        {
            Toast.makeText(this, "Please enter the Message", Toast.LENGTH_LONG).show();
            progressDialog.cancel();
        }
    }
}
