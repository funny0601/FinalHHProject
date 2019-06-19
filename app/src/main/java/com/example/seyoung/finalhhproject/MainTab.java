package com.example.seyoung.finalhhproject;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class MainTab extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainframe);

        Intent intent = getIntent();
        String nickname = intent.getExtras().getString("nickname");
        String profileImagePath = intent.getExtras().getString("profileImagePath");

        final TabHost tabHost = getTabHost();

        ImageView tw01 = new ImageView(this);
        tw01.setImageResource(R.drawable.tw_1);
        TabHost.TabSpec tabCurrentMyHikingStatus = tabHost.newTabSpec("currentMyHikingStatus").setIndicator(tw01);
        Intent int1 = new Intent(this, tab1.class);
        tabCurrentMyHikingStatus.setContent(int1);
        int1.putExtra("nickname", nickname);
        int1.putExtra("profileImagePath", profileImagePath);
        tabHost.addTab(tabCurrentMyHikingStatus);

        ImageView tw02 = new ImageView(this);
        tw02.setImageResource(R.drawable.tw_2);
        TabHost.TabSpec tabRecommendation = tabHost.newTabSpec("recommendation").setIndicator(tw02);
        //tabRecommendation.setContent(R.id.tabRecommendation);
        tabRecommendation.setContent(new Intent(this, tab2.class));
        tabHost.addTab(tabRecommendation);

        ImageView tw03 = new ImageView(this);
        tw03.setImageResource(R.drawable.tw_3);
        TabHost.TabSpec tabWeather = tabHost.newTabSpec("weather").setIndicator(tw03);
        tabWeather.setContent(new Intent(this, tab3.class));
        //tabWeather.setContent(R.id.tabWeather);
        tabHost.addTab(tabWeather);

        ImageView tw04 = new ImageView(this);
        tw04.setImageResource(R.drawable.tw_4);
        TabHost.TabSpec tabRestaurant = tabHost.newTabSpec("restaurant").setIndicator(tw04);
       // tabRestaurant.setContent(R.id.tabRestaurant);
        tabRestaurant.setContent(new Intent(this, tab4.class));
        tabHost.addTab(tabRestaurant);

        ImageView tw05 = new ImageView(this);
        tw05.setImageResource(R.drawable.tw_5);
        TabHost.TabSpec tabReview = tabHost.newTabSpec("review").setIndicator(tw05);
        //tabReview.setContent(R.id.tabReview);
        Intent int5 = new Intent(this, tab5.class);
        tabReview.setContent(int5);
        int5.putExtra("nickname", nickname);
        int5.putExtra("profileImagePath", profileImagePath);
        tabHost.addTab(tabReview);


        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                for(int i=0; i<tabHost.getTabWidget().getChildCount(); i++){
                    tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#ffffff"));
                }
                    tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#64A176"));
            }
        });

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenHeight = metrics.heightPixels;

        for(int i=0; i<tabHost.getTabWidget().getChildCount(); i++) {
            tabHost.getTabWidget().getChildAt(i).getLayoutParams().height = (screenHeight*15)/200;
        }
        tabHost.setCurrentTab(2); // 등산로 추천 탭부터 시작
    }
}
