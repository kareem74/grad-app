package com.example.project;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class API_page extends AppCompatActivity {
    Button Wind_btn ;
    TextView Wind_tv;
    EditText city_et;
    int humidity ,pressure;
    String description;
    double Speed , degree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Wind_btn = findViewById(R.id.wind_btn);
        Wind_tv =findViewById(R.id.wind_view_tv);
        city_et = findViewById(R.id.city_et);
        Wind_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String strtxt =city_et.getText().toString();
                connectToAPI(strtxt);
                Wind_tv.setVisibility(View.VISIBLE);
            }
        });

    }
    private  void  connectToAPI(String cityname)
    {
        String API_Link = "https://samples.openweathermap.org/data/2.5/weather?q="+cityname+"&appid=439d4b804bc8187953eb36d2a8c26a02";

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, API_Link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject root = new JSONObject(response);
                            JSONObject wind , main ,weather;
                            wind = root.getJSONObject("wind");
                            main = root.getJSONObject("main");
                            weather = root.getJSONObject("weather");
                             Speed = wind.getDouble("speed");
                            degree = wind.getDouble("deg");
                            pressure = main.getInt("pressure");
                            humidity = main.getInt("humidity");
                            description = weather.getString("description");

                            // String name;
                            Log.i("weather","hey hello");
                            //Toast.makeText(MainActivity.this,"Speed: "+speed+"\t"+"Degree: "+degree,Toast.LENGTH_LONG).show();
                            Wind_tv.setText("The Speed of Wind : "+Speed+"\t"+"The Degree Of Wind : "+degree +"\t"+"Pressure :" +pressure+"\t"+"Humidity :"+humidity+"\t"+"Description : "+description);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }
}
