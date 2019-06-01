package com.example.seyoung.finalhhproject;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class tab1 extends Activity {
    ImageButton clearBtn;
    ImageButton searchBtn;
    EditText mountainSearch;

    ListView list;
    ArrayList<mountainTable> xmlParsingData = new ArrayList<mountainTable>();
    ArrayList<HashMap<String, String>> mntMapList = new ArrayList<HashMap<String, String>>();
    String search;

    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab1_frame);

        StrictMode.enableDefaults();

        clearBtn = (ImageButton) findViewById(R.id.clearButton);
        searchBtn = (ImageButton) findViewById(R.id.searchButton);
        mountainSearch = (EditText) findViewById(R.id.mountainSearch);

        parseXML dataLoad = new parseXML();
        xmlParsingData = dataLoad.createData();

        // listview에 적용하기 위해 HashMap화 시키기

        for (mountainTable mountainRespective : xmlParsingData) {
            HashMap map = new HashMap();
            map.put("산 이름", mountainRespective.getName());
            map.put("산 높이", mountainRespective.getHeight());
            map.put("지역", mountainRespective.getArea());
            map.put("100대명산 선정 이유", mountainRespective.getReason());
            mntMapList.add(map);
        }

        list = (ListView) findViewById(R.id.listView1);
        SimpleAdapter adapter = new SimpleAdapter(this, mntMapList, android.R.layout.simple_expandable_list_item_2, new String[]{"산 이름", "지역"},
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
                ((SimpleAdapter) list.getAdapter()).getFilter().filter("");
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search = mountainSearch.getText().toString();
                ((SimpleAdapter) list.getAdapter()).getFilter().filter(search);
                // 검색 결과가 없을 때 띄우고 싶은데..
            }
        });
    }
}