package com.npdevs.mnnitcentral;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Monopoly extends AppCompatActivity {

    ImageView imageView1,imageView2,imageView3,imageView4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monopoly);
        imageView1 = findViewById(R.id.imageView140);
        imageView2 = findViewById(R.id.imageView141);
        imageView3 = findViewById(R.id.imageView142);
        imageView4 = findViewById(R.id.imageView143);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChankyaNeetiActivity();
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNavacharActivity();
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNetritvaActivity();
            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStallmart1Activity();
            }
        });
    }
    public void openChankyaNeetiActivity()
    {
        Intent intent = new Intent(this,ChanakyaNeeti.class);
        startActivity(intent);
    }
    public void openNavacharActivity()
    {
        Intent intent = new Intent(this,Navachar.class);
        startActivity(intent);
    }
    public void openNetritvaActivity()
    {
        Intent intent = new Intent(this,Netritva.class);
        startActivity(intent);
    }
    public void openStallmart1Activity()
    {
        Intent intent = new Intent(this,Stallmart1.class);
        startActivity(intent);
    }
}
