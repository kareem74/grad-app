package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class slider_Adapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
   // private Object RelativeLayout;

    public slider_Adapter (Context context)
    {
        this.context = context;
    }
    public  int[] slide_images=
            {
             R.drawable.livemonitor,
             R.drawable.wea,
                    R.drawable.setting
            };
    public  String[] slide_headings =
            {
                    "Live Monitor",
                    "Weather",
                    "Setting"
            };
    public  String[] slide_decs =
            {
                    "This Page Help You to Know  The Values of Parameters Which Affect in The Quality of Water in The Farm... ",
                    "This Page Help you to Know Updated of Weather Everyday and All Time , to Inform The Description of Clouds , Humidity , " +
                            "pressure and Wind (Degree and Speed ",
                    "This Page Help You to Make Decision That Make You Have Full Control on Your Farm Remotely , by controlling in Paddle , " +
                            "Heater , CaCo3 and Food Tank"
            };
    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }
     public  Object instantiateItem (ViewGroup container,int position) throws  NullPointerException
     {
         layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
         View view = layoutInflater.inflate(R.layout.slide_show, container,false);
         ImageView slideImageView = view.findViewById(R.id.slide_image);
         TextView slideHeading = view.findViewById(R.id.slide_heading);
         TextView slideDescription = view.findViewById(R.id.slide_desc);

         slideImageView.setImageResource(slide_images[position]);
         slideHeading.setText(slide_headings[position]);
         slideDescription.setText(slide_decs[position]);
         container.addView(view);
         return  view;
     }
     @Override
    public  void destroyItem (ViewGroup container,int position , Object object)
     {
         container.removeView((RelativeLayout)object);
     }
}
