package com.npdevs.mnnitcentral;


import android.app.ProgressDialog;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MessAdmin extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private long breakfast,lunch,dinner,totalChildren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_admin);
        Button b1 = (Button) findViewById(R.id.button3);
        Button b2 = (Button) findViewById(R.id.button4);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        displayCount();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity1();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity2();
            }
        });
        findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                final DatabaseReference myRef= FirebaseDatabase.getInstance().getReference("users");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        totalChildren=dataSnapshot.getChildrenCount();
                        Log.e("Count ", "" + totalChildren);
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            String key=postSnapshot.getKey();
                            myRef.child(key).child("breakfast").setValue("0");
                            myRef.child(key).child("lunch").setValue("0");
                            myRef.child(key).child("dinner").setValue("0");
                        }
                        TextView breakdisp=findViewById(R.id.breakdisp),
                                lunchdisp=findViewById(R.id.lunchdisp),
                                dinnerdisp=findViewById(R.id.dinnerdisp);
                        breakfast=totalChildren;
                        lunch=totalChildren;
                        dinner=totalChildren;
                        breakdisp.setText(breakfast+"");
                        lunchdisp.setText(lunch+"");
                        dinnerdisp.setText(dinner+"");
                        progressDialog.cancel();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    public void launchActivity1() {
        Intent intent1 = new Intent(this, MessMenu.class);
        startActivity(intent1);
    }

    public void launchActivity2() {
        Intent intent2 = new Intent(this, Feedback.class);
        startActivity(intent2);
    }
    private void displayCount()
    {
        final DatabaseReference myRef= FirebaseDatabase.getInstance().getReference("users");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                breakfast=0;
                lunch=0;
                dinner=0;
                Log.e("Count ", "" + dataSnapshot.getChildrenCount());
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    if(postSnapshot.child("breakfast").getValue().toString().equals("0")) {
                        breakfast++;
                    }
                    if(postSnapshot.child("lunch").getValue().toString().equals("0")) {
                        lunch++;
                    }
                    if(postSnapshot.child("dinner").getValue().toString().equals("0")) {
                        dinner++;
                    }
                }
                TextView breakdisp=findViewById(R.id.breakdisp),
                        lunchdisp=findViewById(R.id.lunchdisp),
                        dinnerdisp=findViewById(R.id.dinnerdisp);
                breakdisp.setText(breakfast+"");
                lunchdisp.setText(lunch+"");
                dinnerdisp.setText(dinner+"");
                progressDialog.cancel();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}