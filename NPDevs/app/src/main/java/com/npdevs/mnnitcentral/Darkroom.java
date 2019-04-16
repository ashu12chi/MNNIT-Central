package com.npdevs.mnnitcentral;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Darkroom extends AppCompatActivity {

    ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_darkroom);
        imageView1 = findViewById(R.id.imageView110);
        imageView2 = findViewById(R.id.imageView112);
        imageView3 = findViewById(R.id.imageView113);
        imageView4 = findViewById(R.id.imageView114);
        imageView5 = findViewById(R.id.imageView115);
        imageView6 = findViewById(R.id.imageView116);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHrsMakingActivity();
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openExhibitionActivity();
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIdentifyStrokeActivity();
            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOnceUponATimeActivity();
            }
        });
        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPhotowalkActivity();
            }
        });
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStandstillActivity();
            }
        });
    }
    public void openHrsMakingActivity()
    {
        Intent intent = new Intent(this,Making.class);
        startActivity(intent);
    }
    public void openExhibitionActivity()
    {
        Intent intent = new Intent(this,Exhibition.class);
        startActivity(intent);
    }
    public void openIdentifyStrokeActivity()
    {
        Intent intent = new Intent(this,IdentifyStroke.class);
        startActivity(intent);
    }
    public void openOnceUponATimeActivity()
    {
        Intent intent = new Intent(this,OnceUpon.class);
        startActivity(intent);
    }
    public void openPhotowalkActivity()
    {
        Intent intent = new Intent(this,PhotoWalk.class);
        startActivity(intent);
    }
    public void openStandstillActivity()
    {
        Intent intent = new Intent(this,StandStill.class);
        startActivity(intent);
    }
}
