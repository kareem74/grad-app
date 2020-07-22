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
                    R.drawable.heater_pic,
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
                    "Please turn on paddle if the temperature is larger than 25 degrees or ammonia is larger than 0.02 ppm.\n" +
                            "Please check your farm if ammonia has extreme value.  ",
                    "Please turn on the heater controller if the temperature below 20 degrees.",
                    "Please open the caco3 tank if the PH value is not normal (7-8).\n" +
                            "Please check your farm if PH has extreme value.",
                    "Please open the food tank 6 times in the normal conditions (normal PH-normal temperature).\n" +
                            "In the abnormal conditions, please open the food tank 3 times."
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
