package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class action_page extends AppCompatActivity {
    SwitchCompat paddle_switch , heater_switch , food_switch, caco3_switch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_page);
        paddle_switch =findViewById(R.id.paddle_switch);
        heater_switch = findViewById(R.id.heater_switch);
        food_switch =findViewById(R.id.food_tank_switch);
        caco3_switch = findViewById(R.id.caco_tank_switch);
        final SharedPreferences paddle_sharedPreferences = getSharedPreferences("save",MODE_PRIVATE);
        paddle_switch.setChecked(paddle_sharedPreferences.getBoolean("value",true));
        paddle_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paddle_switch.isChecked())
                {
                    SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    paddle_switch.setChecked(true);
                }
                else
                {
                    SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",false);
                    editor.apply();
                    paddle_switch.setChecked(false);
                }
            }
        });
        final SharedPreferences heater_sharedPreferences = getSharedPreferences("save",MODE_PRIVATE);
        heater_switch.setChecked(heater_sharedPreferences.getBoolean("value",true));
        heater_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(heater_switch.isChecked())
                {
                    SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    heater_switch.setChecked(true);
                }
                else
                {
                    SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",false);
                    editor.apply();
                    heater_switch.setChecked(false);
                }
            }
        });
        final SharedPreferences food_sharedPreferences = getSharedPreferences("save",MODE_PRIVATE);
        food_switch.setChecked(food_sharedPreferences.getBoolean("value",true));
        food_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(food_switch.isChecked())
                {
                    SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    food_switch.setChecked(true);
                }
                else
                {
                    SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",false);
                    editor.apply();
                    food_switch.setChecked(false);
                }
            }
        });
        final SharedPreferences caco_sharedPreferences = getSharedPreferences("save",MODE_PRIVATE);
        caco3_switch.setChecked(caco_sharedPreferences.getBoolean("value",true));
        caco3_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(caco3_switch.isChecked())
                {
                    SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    caco3_switch.setChecked(true);
                }
                else
                {
                    SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",false);
                    editor.apply();
                    caco3_switch.setChecked(false);
                }
            }
        });

    }
}