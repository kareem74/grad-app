package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.ref.PhantomReference;

public class Live_view_page extends AppCompatActivity  implements ValueEventListener,View.OnClickListener{

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FloatingActionButton floatingActionButton;

    FloatingActionButton fabmain;
    Float TranslationY = 100f;
    boolean isMenuOpen = false;
    double NH3_VALUE;
    OvershootInterpolator interpolator;

    EditText PH_connection,TEMP_connection,NH3_connection;
    Button show,okay;
    int id=0;
    String PH ,Temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_view_page);
        initFabMenu();
        PH_connection = (EditText) findViewById(R.id.ph__read_txt);
        TEMP_connection = (EditText) findViewById(R.id.temp_read_txt);
        NH3_connection = findViewById(R.id.ammonia_read_txt);
        floatingActionButton = findViewById(R.id.fabmain);
        show = findViewById(R.id.show);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                databaseReference =FirebaseDatabase.getInstance().getReference();
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        PH = dataSnapshot.child("PH").getValue().toString();


                        PH_connection.setText(PH);
                         Temp = dataSnapshot.child("Temp").getValue().toString();
                        TEMP_connection.setText(Temp);

                    }
                   // NH3_VALUE =Double.parseDouble(PH +Temp)*0.0007;
                      // NH3_connection.setText((int) NH3_VALUE);


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                NH3_VALUE = (Double.parseDouble(PH + Temp)*0.0007);
                NH3_connection.setText((int) NH3_VALUE);
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Live_view_page.this,slideshow.class);
                startActivity(intent);
            }
        });
        /*

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String phhh =dataSnapshot.child("PH").getValue().toString();

                if (phhh.isEmpty()){
                    Toast.makeText(getApplicationContext(),"wait ",Toast.LENGTH_LONG).show();
                }else {
                    notification_PH();
                }


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        }
    private void notification_PH () {
        String PHH = dataSnapshot.child("PH").getValue().toString();

        String message = "the value of ph is ";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel =
                    new NotificationChannel("n", "n", NotificationManager.IMPORTANCE_HIGH);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "n")
                .setContentText("Ph value")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setAutoCancel(true)
                .setContentText(message + PHH);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(999, builder.build());

    */}
    private void initFabMenu() {
        fabmain = findViewById(R.id.fabmain);


        fabmain.setOnClickListener( this);

    }

    private void openMenu() {
        isMenuOpen = !isMenuOpen;
        fabmain.animate().setInterpolator(interpolator).rotation(45f).setDuration(300).start();

    }

    private void closeMenu() {
        isMenuOpen = !isMenuOpen;
        fabmain.animate().setInterpolator(interpolator).rotation(0f).setDuration(300).start();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabmain:
                Intent intent = new Intent(Live_view_page.this, setting_page.class);
                startActivity(intent);
                if (isMenuOpen) {
                    closeMenu();
                } else {
                    openMenu();
                }
                break;

        }
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}


