package com.npdevs.mnnitcentral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Litmuse extends AppCompatActivity {

    ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_litmuse);
        imageView1 = findViewById(R.id.imageView104);
        imageView2 = findViewById(R.id.imageView105);
        imageView3 = findViewById(R.id.imageView118);
        imageView4 = findViewById(R.id.imageView106);
        imageView5 = findViewById(R.id.imageView107);
        imageView6 = findViewById(R.id.imageView109);
        imageView7 = findViewById(R.id.imageView111);
        imageView8 = findViewById(R.id.imageView117);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCulravDebateActivity();
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHasykoshActivity();
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKavyanjaliActivity();
            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLacuzziActivity();
            }
        });
        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMotleyActivity();
            }
        });
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPoetrySlamActivity();
            }
        });
        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opentalktillyoudropActivity();
            }
        });
        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVaktvyaActivity();
            }
        });
    }
    public void openCulravDebateActivity()
    {
        Intent intent = new Intent(this,CulravDebate.class);
        startActivity(intent);
    }
    public void openHasykoshActivity()
    {
        Intent intent = new Intent(this,Hasykosh.class);
        startActivity(intent);
    }
    public void openKavyanjaliActivity()
    {
        Intent intent = new Intent(this,Kavyanjali.class);
        startActivity(intent);
    }
    public void openLacuzziActivity()
    {
        Intent intent = new Intent(this,Lacuzzi.class);
        startActivity(intent);
    }
    public void openMotleyActivity()
    {
        Intent intent = new Intent(this,Motley.class);
        startActivity(intent);
    }
    public void openPoetrySlamActivity()
    {
        Intent intent = new Intent(this,PoetrySlam.class);
        startActivity(intent);
    }
    public void opentalktillyoudropActivity()
    {
        Intent intent = new Intent(this,Talktillyou.class);
        startActivity(intent);
    }
    public void openVaktvyaActivity()
    {
        Intent intent = new Intent(this,Vaktavya.class);
        startActivity(intent);
    }
}
