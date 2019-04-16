package com.npdevs.mnnitcentral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Renaissance extends AppCompatActivity {

    ImageView imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renaissance);
        imageView1 = findViewById(R.id.imageView94);
        imageView2 = findViewById(R.id.imageView93);
        imageView3 = findViewById(R.id.imageView92);
        imageView4 = findViewById(R.id.imageView90);
        imageView5 = findViewById(R.id.imageView89);
        imageView6 = findViewById(R.id.imageView88);
        imageView7 = findViewById(R.id.imageView87);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDeaAndDesignActivity();
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEplanActivity();
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEtalkActivity();
            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMockIPLAuctionActivity();
            }
        });
        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPanelDiscussionActivity();
            }
        });
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStallMartActivity();
            }
        });
        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStartupFairActivity();
            }
        });
    }

    public void openDeaAndDesignActivity() {
        Intent intent = new Intent(this,DeaAndDesign.class);
        startActivity(intent);
    }

    public void openEplanActivity() {
        Intent intent = new Intent(this,Eplan.class);
        startActivity(intent);
    }

    public void openEtalkActivity() {
        Intent intent = new Intent(this,Etalk.class);
        startActivity(intent);
    }

    public void openMockIPLAuctionActivity() {
        Intent intent = new Intent(this,MockIplAuction.class);
        startActivity(intent);
    }
    public void openPanelDiscussionActivity()
    {
        Intent intent = new Intent(this,PanelDiscussion.class);
        startActivity(intent);
    }
    public void openStallMartActivity()
    {
        Intent intent = new Intent(this,StallMart.class);
        startActivity(intent);
    }
    public void openStartupFairActivity()
    {
        Intent intent = new Intent(this,StartupFair.class);
        startActivity(intent);
    }
}
