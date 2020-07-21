package com.example.project;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class slideshow extends AppCompatActivity {
    private ViewPager mSlideViewPager;
    private LinearLayout nDotLinear;
    private TextView[] nDots;
    private Button prevButton , nextButton;
    private  int CurrentPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideshow);
        nDotLinear = findViewById(R.id.dotsLayout);
        mSlideViewPager = (ViewPager)findViewById(R.id.viewpager);
        prevButton = findViewById(R.id.prevbtn);
        nextButton = findViewById(R.id.nextbtn);
        SliderAdapter sliderAdapter = new SliderAdapter(this);
        mSlideViewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideViewPager.setCurrentItem(CurrentPage + 1);
            }
        });
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideViewPager.setCurrentItem(CurrentPage -1);
            }
        });

    }

    public void addDotsIndicator(int position) {
        nDots = new TextView[4];
        for (int i = 0; i < nDots.length; i++) {
            nDots[i] = new TextView(this);
            nDots[i].setText(Html.fromHtml("&#8226;"));
            nDots[i].setTextSize(35);
            nDots[i].setTextColor(getResources().getColor(R.color.transparent_white));
            nDotLinear.addView(nDots[i]);
        }
        if(nDots.length>0)
        {
            nDots[position].setTextColor(getResources().getColor(R.color.transparent_white));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
          addDotsIndicator(position);
          CurrentPage = position;
          if(position ==0)
          {
              nextButton.setEnabled(true);
              prevButton.setEnabled(false);
              prevButton.setVisibility(View.INVISIBLE);
              nextButton.setText(R.string.next);
              prevButton.setText("");
          }
          else if (position== nDots.length -1)
          {
              nextButton.setEnabled(true);
              prevButton.setEnabled(true);
              prevButton.setVisibility(View.VISIBLE);
              nextButton.setText(R.string.finish);
              prevButton.setText(R.string.previous);
          }
          else {
              nextButton.setEnabled(true);
              prevButton.setEnabled(true);
              prevButton.setVisibility(View.VISIBLE);
              nextButton.setText(R.string.next);
              prevButton.setText(R.string.previous);
          }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}