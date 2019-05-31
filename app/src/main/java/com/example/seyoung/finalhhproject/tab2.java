package com.example.seyoung.finalhhproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class tab2 extends Activity {
    TextView result;
    ArrayList<mountainTable> mt = new ArrayList<mountainTable>();

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab2_frame);

        // 앞 클래스에서 파싱한 데이터 저장하는 클래스
        parseXML dataLoad = new parseXML();
        mt= dataLoad.createData();

        // 제대로 받아왔는지 체크용 코드
        result = (TextView)findViewById(R.id.result);
        result.setText(mt.get(0).getName()+mt.get(0).getArea());

    }
}

