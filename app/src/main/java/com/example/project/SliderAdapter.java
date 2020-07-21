package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
   // private Object RelativeLayout;

    public SliderAdapter (Context context)
    {
        this.context = context;
    }
    public  int[] slide_images=
            {
                    R.drawable.paddle_pic,
                    R.drawable.paddle_pic,
                    R.drawable.food_pic,
                    R.drawable.ph_pic

            };
    public  String[] slide_headings =
            {
                    "Paddle",
                    "Heater",
                    "Food",
                    "CaCo3"
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
        return view ==(RelativeLayout) object;
    }



    @NotNull
    @Override
     public  Object instantiateItem (@NonNull ViewGroup container, int position) throws  NullPointerException
     {
         layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         assert layoutInflater != null;
         View view = layoutInflater.inflate(R.layout.slide_layout, container,false);
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
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((RelativeLayout) object);
    }
}
