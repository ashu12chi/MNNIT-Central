package com.npdevs.mnnitcentral;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Genesis extends AppCompatActivity {

    ImageView imageView1,imageView2,imageView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genesis);
        imageView1 = findViewById(R.id.imageView55);
        imageView2 = findViewById(R.id.imageView57);
        imageView3 = findViewById(R.id.imageView124);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPalladinActivity();
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCentralDogmaActivity();
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openClueQuestActivity();
            }
        });
    }
    public void openPalladinActivity()
    {
        Intent intent = new Intent(this,Palladin.class);
        startActivity(intent);
    }
    public void openCentralDogmaActivity()
    {
        Intent intent = new Intent(this,CentralDogma.class);
        startActivity(intent);
    }
    public void openClueQuestActivity()
    {
        Intent intent = new Intent(this,ClueQuest.class);
        startActivity(intent);
    }
}
