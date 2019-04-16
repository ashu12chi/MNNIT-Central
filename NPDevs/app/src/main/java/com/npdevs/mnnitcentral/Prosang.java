package com.npdevs.mnnitcentral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Prosang extends AppCompatActivity {

    ImageView imageView1,imageView2,imageView3,imageView4,imageView5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prosang);
        imageView1 = findViewById(R.id.imageView85);
        imageView2 = findViewById(R.id.imageView84);
        imageView3 = findViewById(R.id.imageView83);
        imageView4 = findViewById(R.id.imageView82);
        imageView5 = findViewById(R.id.imageView81);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCodeWarzActivity();
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCurosityActivity();
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openExtemporeActivity();
            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNarcActivity();
            }
        });
        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPosidenActivity();
            }
        });
    }
    public void openCodeWarzActivity()
    {
        Intent intent = new Intent(this,CodeWarz.class);
        startActivity(intent);
    }
    public void openCurosityActivity()
    {
        Intent intent = new Intent(this,Curosity.class);
        startActivity(intent);
    }
    public void openExtemporeActivity()
    {
        Intent intent = new Intent(this,Extempore.class);
        startActivity(intent);
    }
    public void openNarcActivity()
    {
        Intent intent = new Intent(this,Narc.class);
        startActivity(intent);
    }
    public void openPosidenActivity()
    {
        Intent intent = new Intent(this,Posiden.class);
        startActivity(intent);
    }
}
