package com.npdevs.mnnitcentral;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Objects;

public class Front extends AppCompatActivity {

    private ImageView textview1,textview2,textview3;
    private String regno;
    private Button buton1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent k=getIntent();
        regno= Objects.requireNonNull(k.getExtras()).getString("regno");
        setContentView(R.layout.activity_front);
        textview1 = findViewById(R.id.imageView6);
        textview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMessSupportActivity();
            }
        });
        textview2 = findViewById(R.id.imageView7);
        textview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEventSupportActivity();
            }
        });
        textview3 = findViewById(R.id.imageView8);
        textview3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRideSupportActivity();
            }
        });
        buton1 = findViewById(R.id.button5);
        buton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAboutActivity();
            }
        });
    }
    public void openMessSupportActivity()
    {
        Intent intent = new Intent(this,MessSupport.class);
        intent.putExtra("regno",regno);
        startActivity(intent);
    }
    public void openEventSupportActivity()
    {
        Intent intent = new Intent(this,EventSupport.class);
        startActivity(intent);
    }
    public void openRideSupportActivity()
    {
        Intent intent = new Intent(this,MapsActivity.class);
        intent.putExtra("regno",regno);
        startActivity(intent);
    }
    public void openAboutActivity()
    {
        Intent intent = new Intent(this,About.class);
        startActivity(intent);
    }
}
