package com.npdevs.mnnitcentral;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Electromania extends AppCompatActivity {

    ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electromania);
        imageView1 = findViewById(R.id.imageView5);
        imageView2 = findViewById(R.id.imageView9);
        imageView3 = findViewById(R.id.imageView10);
        imageView4 = findViewById(R.id.imageView44);
        imageView5 = findViewById(R.id.imageView45);
        imageView6 = findViewById(R.id.imageView47);
        imageView7 = findViewById(R.id.imageView58);
        imageView8 = findViewById(R.id.imageView91);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCircuitOfTheDayActivity();
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCodotronActivity();
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openComboMagicActivity();
            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEmbeddedDesignActivity();
            }
        });
        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFgpaActivity();
            }
        });
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImpromptuActivity();
            }
        });
        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInnodevActivity();
            }
        });
        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQuintathlonActivity();
            }
        });
    }
    public  void openCircuitOfTheDayActivity()
    {
        BlankFragment circuitOfTheDay = new BlankFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container,circuitOfTheDay).commit();
    }
    public void openCodotronActivity()
    {
        Intent intent = new Intent(this,Codotron.class);
        startActivity(intent);
    }
    public void openComboMagicActivity()
    {
        Intent intent = new Intent(this,ComboMagic.class);
        startActivity(intent);
    }
    public void openEmbeddedDesignActivity()
    {
        Intent intent = new Intent(this,EmbeddedDesign.class);
        startActivity(intent);
    }
    public void openFgpaActivity()
    {
        Intent intent = new Intent(this,fgpa.class);
        startActivity(intent);
    }
    public void openImpromptuActivity()
    {
        Intent intent = new Intent(this,ImpromptuActivity.class);
        startActivity(intent);
    }
    public void openInnodevActivity()
    {
        Intent intent = new Intent(this,Innodev.class);
        startActivity(intent);
    }
    public void openQuintathlonActivity()
    {
        Intent intent = new Intent(this,Quintathlon.class);
        startActivity(intent);
    }
}
