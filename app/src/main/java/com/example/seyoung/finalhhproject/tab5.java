package com.example.seyoung.finalhhproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

@SuppressWarnings("deprecation")
public class tab5 extends Activity {
    ImageView imgView;
    ImageButton save;
    EditText review;

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab5_frame);

        Intent intent = getIntent();
        String nickname = intent.getExtras().getString("nickname");
        String profileImagePath = intent.getExtras().getString("profileImagePath");
        //String profileImagePath ="";

        TextView username = (TextView) findViewById(R.id.name);
        username.setText(nickname);

        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        imgView = (ImageView) findViewById(R.id.profile);
        save = (ImageButton) findViewById(R.id.buttonSave);
        review = (EditText) findViewById(R.id.review);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        try {
            URL url = new URL(profileImagePath);
            URLConnection conn = url.openConnection();
            conn.connect();
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
            Bitmap bm = BitmapFactory.decodeStream(bis);
            bis.close();
            imgView.setImageBitmap(bm);
        } catch (Exception e) {
            imgView.setImageDrawable(getResources().getDrawable(R.drawable.dd2));
            // 프사 없을 경우에 기존에 저장되어있는 대체 사진으로 변경 
        }


    }

}
