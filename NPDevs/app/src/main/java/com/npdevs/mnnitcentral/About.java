package com.npdevs.mnnitcentral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class About extends AppCompatActivity {

    TextView textView1,textView2,textView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        textView1 = findViewById(R.id.textView93);
        textView2 = findViewById(R.id.textView94);
        textView3 = findViewById(R.id.textView95);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInstitutionActivity();
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDevelopersActivity();
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openResourcesActivity();
            }
        });
    }
    public void openInstitutionActivity()
    {
        Intent intent = new Intent(this,Institution.class);
        startActivity(intent);
    }
    public void openDevelopersActivity()
    {
        Intent intent = new Intent(this,Devlopers.class);
        startActivity(intent);
    }
    public void openResourcesActivity()
    {
        Intent intent = new Intent(this,Resources.class);
        startActivity(intent);
    }
}
