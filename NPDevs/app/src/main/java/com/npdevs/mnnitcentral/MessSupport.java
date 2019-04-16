package com.npdevs.mnnitcentral;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MessSupport extends AppCompatActivity {

    private Button b1;
    private Button b2;
    private Button b3;
    String regno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_support);
        Intent k=getIntent();
        regno=k.getExtras().getString("regno");

        b1 = (Button) findViewById(R.id.menu);
        b2 = (Button) findViewById(R.id.skip);
        b3 = (Button) findViewById(R.id.feedback);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                launchActivity1();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start your second activity
                launchActivity2();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start your second activity
                launchActivity3();
            }
        });
    }
    public void launchActivity1()
    {
        Intent intent1 = new Intent(this, MessUserMenu.class);
        startActivity(intent1);
    }
    public void launchActivity2()
    {
        Intent intent2 = new Intent(this, MessUserSkip.class);
        intent2.putExtra("regno",regno);
        startActivity(intent2);
    }
    public void launchActivity3()
    {
        Intent intent3 = new Intent(this, MessUserFeedback.class);
        startActivity(intent3);
    }


}
