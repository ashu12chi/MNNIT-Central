package com.npdevs.mnnitcentral;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Kreedomania extends AppCompatActivity {

    ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kreedomania);
        imageView1 = findViewById(R.id.imageView128);
        imageView2 = findViewById(R.id.imageView129);
        imageView3 = findViewById(R.id.imageView130);
        imageView4 = findViewById(R.id.imageView131);
        imageView5 = findViewById(R.id.imageView132);
        imageView6 = findViewById(R.id.imageView133);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCounterStrikeActivity();
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDota2Activity();
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFifaActivity();
            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMostWantedActivity();
            }
        });
        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPubgMobileActivity();
            }
        });
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRubixCubeActivity();
            }
        });
    }
    public void openCounterStrikeActivity()
    {
        Intent intent = new Intent(this,CounterStrike.class);
        startActivity(intent);
    }
    public void openDota2Activity()
    {
        Intent intent = new Intent(this,Dota2.class);
        startActivity(intent);
    }
    public void openFifaActivity()
    {
        Intent intent = new Intent(this,Fifa.class);
        startActivity(intent);
    }
    public void openMostWantedActivity()
    {
        Intent intent = new Intent(this,MostWanted.class);
        startActivity(intent);
    }
    public void openPubgMobileActivity()
    {
        Intent intent = new Intent(this,PubgMobile.class);
        startActivity(intent);
    }
    public void openRubixCubeActivity()
    {
        Intent intent = new Intent(this,RubixCube.class);
        startActivity(intent);
    }
}
