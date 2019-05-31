package com.example.seyoung.finalhhproject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class tab1 extends Activity {

    ImageButton clearBtn;
    ImageButton searchBtn;
    EditText mountainSearch;
    ListView list;
    ArrayList<mountainTable> xmlParsingData = new ArrayList<mountainTable>();
    ArrayList<String> mnt_name_list = new ArrayList<String>();

    ArrayList<HashMap<String, String>> mntMapList = new ArrayList<HashMap<String, String>>();
    String search;

    ArrayList<String> temp = new ArrayList<String>();
    // 검색한 데이터가 나올 리스트

    boolean initem = false;

    boolean inMntnm = false;
    String mntnm = null; // 산명
    boolean inAreanm = false;
    String areanm = null; // 산정보소재지
    boolean inMntheight = false;
    String mntheight = null; // 산정보높이
    boolean inAeatreason = false;
    String aeatreason = null; // 100대 명산 선정 이유
    boolean inTransport = false;
    String transport = null; // 대중교통정보설명
    boolean inTourisminf = false;
    String tourisminf = null; // 주변관광정보설명
    URL url;

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab1_frame);

        StrictMode.enableDefaults();

        clearBtn = (ImageButton) findViewById(R.id.clearButton);
        searchBtn = (ImageButton) findViewById(R.id.searchButton);
        mountainSearch = (EditText) findViewById(R.id.mountainSearch);

        parseXML dataLoad = new parseXML();
        xmlParsingData= dataLoad.createData();

        // listview에 적용하기 위해 HashMap화 시키기
        for(mountainTable mountainRespective : xmlParsingData) {
            HashMap map = new HashMap();
            map.put("산 이름", mountainRespective.getName());
            map.put("산 높이", mountainRespective.getHeight());
            map.put("지역", mountainRespective.getArea());
            map.put("100대명산 선정 이유", mountainRespective.getReason());
            mntMapList.add(map);
        }

        list = (ListView) findViewById(R.id.listView1);
        SimpleAdapter adapter= new SimpleAdapter(this, mntMapList, android.R.layout.simple_expandable_list_item_2, new String[] {"산 이름", "지역"},
                    new int[]{android.R.id.text1, android.R.id.text2});

        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        list.setAdapter(adapter);
        list.setTextFilterEnabled(true);

        Intent intent = getIntent();
        String nickname = intent.getExtras().getString("nickname");
        String profileImagePath = intent.getExtras().getString("profileImagePath");

        //Collections.sort(mnt_height_list);
        //int max = Collections.max(mnt_height_list); //1947
        //int min = Collections.min(mnt_height_list); //328


        // x버튼 누르면 입력창 초기화
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mountainSearch.setText("");
                ((SimpleAdapter)list.getAdapter()).getFilter().filter("");
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search = mountainSearch.getText().toString();
                ((SimpleAdapter)list.getAdapter()).getFilter().filter(search);
                // 검색 결과가 없을 때 띄우고 싶은데..
            }
        });


    }

}



