package com.npdevs.mnnitcentral;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Clubs extends AppCompatActivity {
    Button button;
    DatabaseReference dbrs;
    int n;
    TextView display;
    private ProgressDialog progressDialog;
    String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubs);
        progressDialog=new ProgressDialog(this);
        display=findViewById(R.id.ftv);
        display.setMovementMethod(new ScrollingMovementMethod());
        progressDialog.setMessage("Retrieving Messages...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        dbrs=FirebaseDatabase.getInstance().getReference("eventsnotice");
        dbrs.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                n=Integer.parseInt(dataSnapshot.child("no").getValue().toString());
                value="";
                for(int i=n;i>=1;i--) {
                    value = value + "\n\n" +(n-i+1)+".  "+ dataSnapshot.child(i + "").getValue().toString();
                }
                display.setText(value);
                progressDialog.cancel();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Clubs.this, "Process Cancelled", Toast.LENGTH_LONG).show();
            }
        });
    }
}
