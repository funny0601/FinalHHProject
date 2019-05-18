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

        Intent intent = getIntent();
        String nickname = intent.getExtras().getString("nickname");
        String profileImagePath = intent.getExtras().getString("profileImagePath");

        TabHost tabHost = getTabHost();

        TabHost.TabSpec tabCurrentMyHikingStatus = tabHost.newTabSpec("currentMyHikingStatus").setIndicator("등산 현황");
        Intent int1 = new Intent(this, tab1.class);
        tabCurrentMyHikingStatus.setContent(int1);
        int1.putExtra("nickname", nickname);
        int1.putExtra("profileImagePath", profileImagePath);
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
        //tabReview.setContent(R.id.tabReview);
        Intent int5 = new Intent(this, tab5.class);
        tabReview.setContent(int5);
        int5.putExtra("nickname", nickname);
        int5.putExtra("profileImagePath", profileImagePath);
        tabHost.addTab(tabReview);

        tabHost.setCurrentTab(3); // 등산로 추천 탭부터 시작
    }
}
