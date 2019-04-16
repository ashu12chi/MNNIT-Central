package com.npdevs.mnnitcentral;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class EventSupport extends AppCompatActivity {

    ImageView avishkar,culrav,eloquence,prosang,renaissance,annual,clubs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_support);
        avishkar = findViewById(R.id.imageView14);
        culrav = findViewById(R.id.imageView13);
        eloquence = findViewById(R.id.imageView15);
        prosang = findViewById(R.id.imageView16);
        renaissance = findViewById(R.id.imageView17);
        annual = findViewById(R.id.imageView18);
        clubs = findViewById(R.id.imageView19);
        avishkar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAvishkarActivity();
            }
        });
        culrav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCulravActivity();
            }
        });
        eloquence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEloquenceActivity();
            }
        });
        prosang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProsangActivity();
            }
        });
        renaissance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRenaissanceActivity();
            }
        });
        annual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAnnualActivity();
            }
        });
        clubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openClubsActivity();
            }
        });
    }
    public void openAvishkarActivity()
    {
        Intent intent = new Intent(this,Avishkar.class);
        startActivity(intent);
    }
    public void openCulravActivity()
    {
        Intent intent = new Intent(this,Culrav.class);
        startActivity(intent);
    }
    public void openEloquenceActivity()
    {
        Intent intent = new Intent(this,Eloquence.class);
        startActivity(intent);
    }
    public void openProsangActivity()
    {
        Intent intent = new Intent(this,Prosang.class);
        startActivity(intent);
    }
    public void openRenaissanceActivity()
    {
        Intent intent = new Intent(this,Renaissance.class);
        startActivity(intent);
    }
    public void openAnnualActivity()
    {
        Intent intent = new Intent(this,Annual.class);
        startActivity(intent);
    }
    public void openClubsActivity()
    {
        Intent intent = new Intent(this,Clubs.class);
        startActivity(intent);
    }
}
