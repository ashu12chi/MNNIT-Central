package com.npdevs.mnnitcentral;

import java.text.*;
import java.util.*;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MessUserSkip extends Activity {
    DatabaseReference dbrs;
    CheckBox a, b, c;
    Button button;
    String regno;
    int c1=0, c2=0, c3=0;
    protected void onCreate(Bundle savedInstanceState) {
        //TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_user_skip);
        Intent k=getIntent();
        regno=k.getExtras().getString("regno");
        a=(CheckBox)findViewById(R.id.checkbox_breakf);
        b=(CheckBox)findViewById(R.id.checkbox_lunch);
        c=(CheckBox)findViewById(R.id.checkbox_dinner);


        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                addSkip();
            }
        });
    }
    public void onCheckboxClicked(View view) {

        Calendar cal = Calendar.getInstance();
        Date date=cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedDate=dateFormat.format(date);
        String s="12:00:00";
        String e="00:00:00";


        if (isTimeWith_in_Interval(formattedDate, s, e)==false) {
            boolean checked = ((CheckBox) view).isChecked();

            if (view.getId() == R.id.checkbox_breakf) {
                if (checked)
                    c1=1;
                else c1=0;
            }
            if (view.getId() == R.id.checkbox_lunch) {
                if (checked)
                    c2=1;
                else c2=0;
            }
            if (view.getId() == R.id.checkbox_dinner) {
                if (checked)
                    c3=1;
                else c3=0;
            }
        }
        else
        {
            Toast.makeText(this, "Timings to choose sessions is 16 hrs to 00 hrs", Toast.LENGTH_LONG).show();
        }
    }
    public void addSkip()
    {
        dbrs=FirebaseDatabase.getInstance().getReference("users/"+regno);
        dbrs.child("breakfast").setValue(c1+"");
        dbrs.child("lunch").setValue(c2+"");
        dbrs.child("dinner").setValue(c3+"");
        Toast.makeText(this, "Number of sessions selected :-\nBreakfast = "+c1+"\nLunch     = "+c2+"\nDinner    = "+c3, Toast.LENGTH_LONG).show();
    }
    public static boolean isTimeWith_in_Interval(String valueToCheck, String endTime, String startTime) {
        boolean isBetween = false;
        try {
            Date time1 = new SimpleDateFormat("HH:mm:ss").parse(endTime);

            Date time2 = new SimpleDateFormat("HH:mm:ss").parse(startTime);

            Date d = new SimpleDateFormat("HH:mm:ss").parse(valueToCheck);

            if (time1.after(d) && time2.before(d)) {
                isBetween = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return isBetween;
    }
}