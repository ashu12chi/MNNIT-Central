package com.npdevs.mnnitcentral;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Annunad extends AppCompatActivity {

    ImageView imageView1,imageView2,imageView3,imageView4,imageView5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annunad);
        imageView1 = findViewById(R.id.imageView98);
        imageView2 = findViewById(R.id.imageView99);
        imageView3 = findViewById(R.id.imageView100);
        imageView4 = findViewById(R.id.imageView101);
        imageView5 = findViewById(R.id.imageView102);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEuphonyActivity();
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGoatActivity();
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHarmonyActivity();
            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRocktaveActivity();
            }
        });
        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVoiceofculravActivity();
            }
        });
    }
    public void openEuphonyActivity()
    {
        Intent intent = new Intent(this,Euphony.class);
        startActivity(intent);
    }
    public void openGoatActivity()
    {
        Intent intent = new Intent(this,Goat.class);
        startActivity(intent);
    }
    public void openHarmonyActivity()
    {
        Intent intent = new Intent(this,Harmony.class);
        startActivity(intent);
    }
    public void openRocktaveActivity()
    {
        Intent intent = new Intent(this,Rocktave.class);
        startActivity(intent);
    }
    public void openVoiceofculravActivity()
    {
        Intent intent = new Intent(this,VoiceOfCulrav.class);
        startActivity(intent);
    }
}
