package com.npdevs.mnnitcentral;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MessUserMenu extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private String[] sessions = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};
    private String text;
    private TextView menuset;
    private ProgressDialog progressDialog;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_mess_user_menu);
        final Spinner spin = (Spinner) findViewById(R.id.day);
        button=(Button)findViewById(R.id.button);
        spin.setOnItemSelectedListener(this);
        menuset=findViewById(R.id.menuset);
        progressDialog=new ProgressDialog(this);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sessions);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
    }


    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        text=sessions[position];
        Toast.makeText(getApplicationContext(), sessions[position], Toast.LENGTH_LONG).show();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("messmenu/"+text);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                menuset.setText(dataSnapshot.getValue(String.class));
                progressDialog.cancel();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Process Cancelled!", Toast.LENGTH_LONG).show();
                progressDialog.cancel();
            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
    }
}
