package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pusher.pushnotifications.PushNotifications;

import java.lang.ref.PhantomReference;

public class Live_view_page extends AppCompatActivity  implements ValueEventListener{

    TextView ph_read_txt , temp_read_txt , nh3_read_txt;
    private  FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private  DatabaseReference ph_read = databaseReference.child("ph_status");
    private DatabaseReference temp_read = databaseReference.child("temp_status");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_view_page);
        ph_read_txt = findViewById(R.id.ph__read_txt);
        temp_read_txt = findViewById(R.id.temp_read_txt);
        nh3_read_txt = findViewById(R.id.ammonia_read_txt);
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if (snapshot.getValue(String.class) != null){
            String key = snapshot.getKey();
            if (key.equals("ph_status"))
            {
                String PH = snapshot.getValue(String.class);
                double D=Double.parseDouble(PH);
                ph_read_txt.setText(PH);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(Live_view_page.this);
                builder.setSmallIcon(R.drawable.notification);
                builder.setContentTitle("New notification");
                builder.setContentText("Current PH Value : "+D)
                        .setAutoCancel(true);
                Intent ph_intent = new Intent(Live_view_page.this,setting_page.class);
                ph_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                ph_intent.putExtra("PH value :",D);
                PendingIntent pendingIntent =   PendingIntent.getActivity(Live_view_page.this,0,ph_intent,PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);
                NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0,builder.build());
            }

        }
        if (snapshot.getValue(String.class) != null){
            String key = snapshot.getKey();
            if (key.equals("temp_status"))
            {
                String Temp = snapshot.getValue(String.class);
                double D_temp=Double.parseDouble(Temp);
                temp_read_txt.setText(Temp);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(Live_view_page.this)
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("New notification")
                .setContentText("Current Temp Value : "+ D_temp)
                        .setAutoCancel(true);
                Intent Temp_intent = new Intent(Live_view_page.this,setting_page.class);
                Temp_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Temp_intent.putExtra("Temp value :",D_temp);
                PendingIntent pendingIntent =   PendingIntent.getActivity(Live_view_page.this,0,Temp_intent,PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);
                NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0,builder.build());

            }

        }

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
    @Override
    protected  void  onStart()
    {
        super.onStart();
        ph_read.addValueEventListener(this);
        temp_read.addValueEventListener(this);

    }
}


