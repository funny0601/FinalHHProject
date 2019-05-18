package com.example.seyoung.finalhhproject;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class MainTab extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainframe);

        TabHost tabHost = getTabHost();

        TabHost.TabSpec tabCurrentMyHikingStatus = tabHost.newTabSpec("currentMyHikingStatus").setIndicator("등산 현황");
        //tabCurrentMyHikingStatus.setContent(R.id.tabCurrentMyHikingStatus);
        tabCurrentMyHikingStatus.setContent(new Intent(this, tab1.class));
        tabHost.addTab(tabCurrentMyHikingStatus);

        TabHost.TabSpec tabRecommendation = tabHost.newTabSpec("recommendation").setIndicator("등산로 추천");
        //tabRecommendation.setContent(R.id.tabRecommendation);
        tabRecommendation.setContent(new Intent(this, tab2.class));
        tabHost.addTab(tabRecommendation);

        TabHost.TabSpec tabWeather = tabHost.newTabSpec("weather").setIndicator("날씨");
        tabWeather.setContent(new Intent(this, tab3.class));
        //tabWeather.setContent(R.id.tabWeather);
        tabHost.addTab(tabWeather);

        TabHost.TabSpec tabRestaurant = tabHost.newTabSpec("restaurant").setIndicator("주변 맛집");
        tabRestaurant.setContent(R.id.tabRestaurant);
        tabHost.addTab(tabRestaurant);

        TabHost.TabSpec tabReview = tabHost.newTabSpec("review").setIndicator("등산 후기");
        tabReview.setContent(R.id.tabReview);
        tabHost.addTab(tabReview);

        tabHost.setCurrentTab(2); // 등산로 추천 탭부터 시작
    }
}
