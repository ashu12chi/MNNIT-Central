package com.npdevs.mnnitcentral;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MessMenu extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] sessions = { "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};
    Button button;
    String text;
    DatabaseReference dbrs;
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_mess_menu);
        dbrs= FirebaseDatabase.getInstance().getReference("messmenu");
        button=(Button)findViewById(R.id.button);
        et=(EditText)findViewById(R.id.editText2);
        et.setMovementMethod(new ScrollingMovementMethod());
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        final Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,sessions);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = spin.getSelectedItem().toString();
                addmen();
            }
        });

    }

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(),sessions[position] , Toast.LENGTH_LONG).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
    }
    public void addmen()
    {
        String name = et.getText().toString().trim();

        if (!TextUtils.isEmpty(name)) {

            dbrs.child(text).setValue(name);
            Toast.makeText(this, "Menu for "+text+" submitted", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Please enter the menu", Toast.LENGTH_LONG).show();
        }
    }
}




