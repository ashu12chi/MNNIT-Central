package com.npdevs.mnnitcentral;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Cyberquest extends AppCompatActivity {

    ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,imageView10,imageView11,imageView12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cyberquest);
        imageView1 = findViewById(R.id.imageView42);
        imageView2 = findViewById(R.id.imageView41);
        imageView3 = findViewById(R.id.imageView40);
        imageView4 = findViewById(R.id.imageView39);
        imageView5 = findViewById(R.id.imageView38);
        imageView6 = findViewById(R.id.imageView43);
        imageView7 = findViewById(R.id.imageView26);
        imageView8 = findViewById(R.id.imageView25);
        imageView9 = findViewById(R.id.imageView24);
        imageView10 = findViewById(R.id.imageView23);
        imageView11 = findViewById(R.id.imageView22);
        imageView12 = findViewById(R.id.imageView21);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContrihubActivity();
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDroidrustActivity();
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInsomaniaActivity();
            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogicalRythumActivity();
            }
        });
        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOperamaniaActivity();
            }
        });
        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRevenggActivity();
            }
        });
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCodeOfTheDayActivity();
            }
        });
        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSoftablizActivity();
            }
        });
        imageView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSoftathalonActivity();
            }
        });
        imageView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSphinxActivity();
            }
        });
        imageView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTuxwarsActivity();
            }
        });
        imageView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebsterActivity();
            }
        });
    }
    public void openContrihubActivity()
    {
        Intent intent = new Intent(this,Contrihub.class);
        startActivity(intent);
    }
    public void openCodeOfTheDayActivity()
    {
        Intent intent = new Intent(this,CodeOfTheDay.class);
        startActivity(intent);
    }
    public void openDroidrustActivity()
    {
        Intent intent = new Intent(this,Droidrust.class);
        startActivity(intent);
    }
    public void openInsomaniaActivity()
    {
        Intent intent = new Intent(this,Insomania.class);
        startActivity(intent);
    }
    public void openLogicalRythumActivity()
    {
        Intent intent = new Intent(this,LogicalRythum.class);
        startActivity(intent);
    }
    public void openOperamaniaActivity()
    {
        Intent intent = new Intent(this,Operamania.class);
        startActivity(intent);
    }
    public void openRevenggActivity()
    {
        Intent intent = new Intent(this,Revengg.class);
        startActivity(intent);
    }
    public void openSoftablizActivity()
    {
        Intent intent = new Intent(this,Softabliz.class);
        startActivity(intent);
    }
    public void openSoftathalonActivity()
    {
        Intent intent = new Intent(this,Softathalon.class);
        startActivity(intent);
    }
    public void openSphinxActivity()
    {
        Intent intent = new Intent(this,Sphinx.class);
        startActivity(intent);
    }
    public void openTuxwarsActivity()
    {
        Intent intent = new Intent(this,Tuxwars.class);
        startActivity(intent);
    }
    public void openWebsterActivity()
    {
        Intent intent = new Intent(this,Webster.class);
        startActivity(intent);
    }
}
