package com.example.project;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    ImageButton live_btn, setting_btn , weather_btn, Logout_btn;
    Animation Topanim , Bottomanim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        live_btn = findViewById(R.id.live_icon);
        setting_btn = findViewById(R.id.setting_icon);
        weather_btn = findViewById(R.id.weather_icon);
        Logout_btn = findViewById(R.id.logout_icon);
        Topanim = AnimationUtils.loadAnimation(this, R.anim.top_anim);
        Bottomanim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);
        live_btn.setAnimation(Topanim);
        weather_btn.setAnimation(Topanim);
        setting_btn.setAnimation(Bottomanim);
        Logout_btn.setAnimation(Bottomanim);
        live_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Live_view_page.class);
                startActivity(intent);
            }
        });

        setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, setting_page.class);
                startActivity(intent);
            }
        });
        weather_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,API_page.class);
                startActivity(intent);
            }
        });


        Logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainActivity.this,"Logged out",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }
        });
    }
}

