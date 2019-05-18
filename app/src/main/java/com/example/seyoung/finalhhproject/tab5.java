package com.example.seyoung.finalhhproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class tab5 extends Activity {
    ImageView imgView;

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab5_frame);

        Intent intent = getIntent();
        String nickname = intent.getExtras().getString("nickname");
        String profileImagePath = intent.getExtras().getString("profileImagePath");

        TextView username = (TextView) findViewById(R.id.name);
        username.setText(nickname);


        imgView = (ImageView) findViewById(R.id.profile);


        try {
            URL url = new URL(profileImagePath);
            URLConnection conn = url.openConnection();
            conn.connect();
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
            Bitmap bm = BitmapFactory.decodeStream(bis);
            bis.close();
            imgView.setImageBitmap(bm);
        } catch (Exception e) {
        }


    }

}
