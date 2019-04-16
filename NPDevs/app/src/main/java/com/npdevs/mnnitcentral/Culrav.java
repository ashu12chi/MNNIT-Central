package com.npdevs.mnnitcentral;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Culrav extends AppCompatActivity {

    ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culrav);
        imageView1 = findViewById(R.id.imageView67);
        imageView2 =findViewById(R.id.imageView66);
        imageView3 = findViewById(R.id.imageView65);
        imageView4 =findViewById(R.id.imageView64);
        imageView5 = findViewById(R.id.imageView63);
        imageView6 =findViewById(R.id.imageView62);
        imageView7 = findViewById(R.id.imageView61);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAnnunadActivity();
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDarkroomActivity();
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLitmuseActivity();
            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRangmanchActivity();
            }
        });
        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRangsazziActivity();
            }
        });
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRazzmatazzActivity();
            }
        });
        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSpandanActivity();
            }
        });
    }
    public void openAnnunadActivity()
    {
        Intent intent = new Intent(this,Annunad.class);
        startActivity(intent);
    }
    public void openDarkroomActivity()
    {
        Intent intent = new Intent(this,Darkroom.class);
        startActivity(intent);
    }
    public void openLitmuseActivity()
    {
        Intent intent = new Intent(this,Litmuse.class);
        startActivity(intent);
    }
    public void openRangmanchActivity()
    {
        Intent intent = new Intent(this,Rangmanch.class);
        startActivity(intent);
    }
    public void openRangsazziActivity()
    {
        Intent intent = new Intent(this,Rangsazzi.class);
        startActivity(intent);
    }
    public void openRazzmatazzActivity()
    {
        Intent intent = new Intent(this,Razzmatazz.class);
        startActivity(intent);
    }
    public void openSpandanActivity()
    {
        Intent intent = new Intent(this,Spandan.class);
        startActivity(intent);
    }
}
