package com.npdevs.mnnitcentral;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Avishkar extends AppCompatActivity {

    ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,imageView10,imageView11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avishkar);
        imageView1 = findViewById(R.id.imageView37);
        imageView2 = findViewById(R.id.imageView36);
        imageView3 = findViewById(R.id.imageView35);
        imageView4 = findViewById(R.id.imageView34);
        imageView5 = findViewById(R.id.imageView33);
        imageView6 = findViewById(R.id.imageView32);
        imageView7 = findViewById(R.id.imageView31);
        imageView8 = findViewById(R.id.imageView30);
        imageView9 = findViewById(R.id.imageView29);
        imageView10 = findViewById(R.id.imageView28);
        imageView11 = findViewById(R.id.imageView27);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAerodynamixActivity();
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCyberquestActivity();
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openElectromaniaActiity();
            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGenesisActiity();
            }
        });
        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKreedomaniaActivity();
            }
        });
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMonopolyActivity();
            }
        });
        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNirmanActivity();
            }
        });
        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOligopolyActiity();
            }
        });
        imageView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPowersurgeActivity();
            }
        });
        imageView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRasayanActivity();
            }
        });
        imageView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRobomaniaActivity();
            }
        });
    }
    public void openAerodynamixActivity()
    {
        Intent intent = new Intent(this,Aerdynamix.class);
        startActivity(intent);
    }
    public void openCyberquestActivity()
    {
        Intent intent = new Intent(this,Cyberquest.class);
        startActivity(intent);
    }
    public void openElectromaniaActiity()
    {
        Intent intent = new Intent(this,Electromania.class);
        startActivity(intent);
    }
    public void openGenesisActiity()
    {
        Intent intent = new Intent(this,Genesis.class);
        startActivity(intent);
    }
    public void openKreedomaniaActivity()
    {
        Intent intent = new Intent(this,Kreedomania.class);
        startActivity(intent);
    }
    public void openMonopolyActivity()
    {
        Intent intent = new Intent(this,Monopoly.class);
        startActivity(intent);
    }
    public void openNirmanActivity()
    {
        Intent intent = new Intent(this,Nirman.class);
        startActivity(intent);
    }
    public void openOligopolyActiity()
    {
        Intent intent = new Intent(this,Oligopoly.class);
        startActivity(intent);
    }
    public void openPowersurgeActivity()
    {
        Intent intent = new Intent(this,Powersurge.class);
        startActivity(intent);
    }
    public void openRasayanActivity()
    {
        Intent intent = new Intent(this,Rasayan.class);
        startActivity(intent);
    }
    public void openRobomaniaActivity()
    {
        Intent intent = new Intent(this,Robomania.class);
        startActivity(intent);
    }
}
