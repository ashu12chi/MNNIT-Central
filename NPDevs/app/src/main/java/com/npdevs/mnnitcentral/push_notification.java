package com.npdevs.mnnitcentral;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.legacy.content.WakefulBroadcastReceiver;

public class push_notification extends IntentService {
    String saved;
    public push_notification() {
        super("push_notification");
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("Info","Reached Here");
        WakefulBroadcastReceiver.completeWakefulIntent(intent);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference("eventsnotice");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                loadPreferences();
                String no=dataSnapshot.child("no").getValue(String.class);
                if(!saved.equals(no)) {
                    createNotificationChannel();
                    savePreferences(no);
                    notification();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        //TimeTable Alarm
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK)-1;
        SharedPreferences sharedPreferences=getSharedPreferences("tablesave"+day,MODE_PRIVATE);
        String timeStamp = new SimpleDateFormat("HH").format(new Date());
        int ty = Integer.parseInt(timeStamp);
        String gd = new SimpleDateFormat("mm").format(new Date());
        int gy = Integer.parseInt(gd);
        if (ty>=7 && ty<=16) {
            Log.i("info" , "Reached here in");
            if (gy >= 39 && gy <= 41) {
                Log.i("info" , "Reached here too");
                String need=sharedPreferences.getString(day+" "+(ty+1),"");
                if(need.isEmpty())
                    need="No Class";
                createNotificationChannelTT();
                notificationTT(need);
            }
        }
    }
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //CharSequence name = getString(R.string.channel_name);
            //String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("MyEvent", "MyEvent", importance);
            //channel.setDescription(description);
            channel.enableVibration(true);
            channel.enableLights(true);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void notification() {
        Intent intent = new Intent(this, Clubs.class);
        //TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        //stackBuilder.addParentStack(MainActivity.class);
        //stackBuilder.addNextIntent(intent);
        PendingIntent pendingintent = PendingIntent.getActivity(this, 0, intent, 0);
        //PendingIntent pendingintent = stackBuilder.getPendingIntent(0 , PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "MyEvent")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setAutoCancel(true)
                .setContentTitle("New Event Added")
                .setContentText("New Event Added")
                .setDefaults(NotificationCompat.DEFAULT_VIBRATE)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Tap to see..."))
                .setContentIntent(pendingintent);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
// notificationId is a unique int for each notification that you must define
        notificationManager.notify(12, builder.build());
    }
    private void loadPreferences()
    {
        SharedPreferences sharedPreferences=getSharedPreferences("CheckNPAlarm",MODE_PRIVATE);
        saved=sharedPreferences.getString("No","");
    }
    private void savePreferences(String value)
    {
        SharedPreferences sharedPreferences=getSharedPreferences("CheckNPAlarm",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("No", value);
        editor.apply();
    }


    private void createNotificationChannelTT() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //CharSequence name = getString(R.string.channel_name);
            //String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("MyTimeTable", "MyTimeTable", importance);
            //channel.setDescription(description);
            channel.enableVibration(true);
            channel.enableLights(true);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void notificationTT(String c) {
        Intent intent = new Intent(this, Clubs.class);
        //TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        //stackBuilder.addParentStack(MainActivity.class);
        //stackBuilder.addNextIntent(intent);
        PendingIntent pendingintent = PendingIntent.getActivity(this, 0, intent, 0);
        //PendingIntent pendingintent = stackBuilder.getPendingIntent(0 , PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "MyTimeTable")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setAutoCancel(true)
                .setContentTitle("Class Now")
                .setContentText(c)
                .setDefaults(NotificationCompat.DEFAULT_VIBRATE)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(c))
                .setContentIntent(pendingintent);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
// notificationId is a unique int for each notification that you must define
        notificationManager.notify(12, builder.build());
    }
}
