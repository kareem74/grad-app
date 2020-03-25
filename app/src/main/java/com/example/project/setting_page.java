package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

public class setting_page extends AppCompatActivity {
CheckBox Notify_checbox;
ElegantNumberButton extreme_value_btn,measuring_period_btn;
TextView extreme_txt,measuring_txt,minute_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_page);
Notify_checbox=findViewById(R.id.notify_checkbox);
extreme_txt=findViewById(R.id.extreme_value_txt);
measuring_txt=findViewById(R.id.measure_txt);
extreme_value_btn=findViewById(R.id.extreme_btn);
measuring_period_btn=findViewById(R.id.measure_btn);
minute_txt=findViewById(R.id.minute_txt);
Notify_checbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
extreme_txt.setVisibility(View.VISIBLE);
measuring_txt.setVisibility(View.VISIBLE);
extreme_value_btn.setVisibility(View.VISIBLE);
measuring_period_btn.setVisibility(View.VISIBLE);
minute_txt.setVisibility(View.VISIBLE);
        }
        else{
            extreme_txt.setVisibility(View.INVISIBLE);
            measuring_txt.setVisibility(View.INVISIBLE);
            extreme_value_btn.setVisibility(View.INVISIBLE);
            measuring_period_btn.setVisibility(View.INVISIBLE);
            minute_txt.setVisibility(View.INVISIBLE);
        }
    }
});

    }
}