package com.npdevs.mnnitcentral;

import android.app.Activity;
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

public class MessUserFeedback extends Activity {
    DatabaseReference dbrs;
    Button b;
    EditText et;
    int n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_user_feedback);
        b=findViewById(R.id.submit);
        et=findViewById(R.id.form);
        et.setMovementMethod(new ScrollingMovementMethod());
        dbrs=FirebaseDatabase.getInstance().getReference("messfeedback");
        dbrs.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("no").exists())
                    n=Integer.parseInt(dataSnapshot.child("no").getValue().toString());
                else
                {
                    n=0;
                    dbrs.child("no").setValue(n+"");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFeedback();
            }
        });
    }
    private void addFeedback() {
        String form = et.getText().toString().trim();
        if (!form.isEmpty()) {
            dbrs.child("no").setValue(++n+"");
            dbrs.child(n+"").setValue(form);
            Toast.makeText(this, "Feedback submitted", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "Please enter the feedback", Toast.LENGTH_LONG).show();
        }
    }
}
