package com.example.seyoung.finalhhproject;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class tab1 extends Activity {
    ImageButton clearBtn;
    ImageButton searchBtn;
    EditText mountainSearch;
    SimpleAdapter adapter;
    ListView list;
    ArrayList<mountainTable> xmlParsingData = new ArrayList<mountainTable>();
    ArrayList<HashMap<String, String>> mntMapList = new ArrayList<HashMap<String, String>>();
    String search;
    boolean initem = false;
    boolean inMntnm = false;
    boolean inAreanm = false;
    int count =0;

    String mnt = "";
    String area = "";

    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab1_frame);

        StrictMode.enableDefaults();

        clearBtn = (ImageButton) findViewById(R.id.clearButton);
        searchBtn = (ImageButton) findViewById(R.id.searchButton);
        mountainSearch = (EditText) findViewById(R.id.mountainSearch);

        System.out.println("hello");
        String serviceUrl = "http://openapi.forest.go.kr/openapi/service/cultureInfoService/gdTrailInfoOpenAPI";
        //본인 키값!
        String serviceKey = "cg57liprV33JjaeFy1LJgzsD6EYcgoaVf9Du7P2W8P47pfco85kGJPMrOhESrZluVfW1D2k%2BgX7yxn%2F40U6VWA%3D%3D"
                //"cg57liprV33JjaeFy1LJgzsD6EYcgoaVf9Du7P2W8P47pfco85kGJPMrOhESrZluVfW1D2k%2BgX7yxn%2F40U6VWA%3D%3D"
                ;

        // listview에 적용하기 위해 HashMap화 시키기
        list = (ListView) findViewById(R.id.listView1);

        adapter = new SimpleAdapter(tab1.this, mntMapList, android.R.layout.simple_expandable_list_item_2, new String[]{"산 이름", "지역"},
                new int[]{android.R.id.text1, android.R.id.text2});


        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        list.setAdapter(adapter);
        list.setTextFilterEnabled(true);


        for(int i=1; i<6; i++) {
            String strUrl = serviceUrl + "?ServiceKey=" + serviceKey + "&pageNo=" + i + "&numOfRows=20";
            System.out.println(strUrl);
            new DownloadWebpageTask().execute(strUrl);
        }



        clearBtn.setOnClickListener(new View.OnClickListener() {
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
            }
        });
    }

    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                return (String) downloadUrl((String) urls[0]);
            } catch (IOException e) {
                return "==>다운로드 실패";
            }
        }

        protected void onPostExecute(String result) {
            try {
                System.out.println("들어옴?");
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput(new StringReader(result));
                int eventType = xpp.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT) {

                    switch (eventType) {

                        case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행

                            if (xpp.getName().equals("mntnm")) { //mntnm 만나면 내용을 받을수 있게 하자

                                inMntnm = true;

                            }

                            if (xpp.getName().equals("areanm")) { //areanm 만나면 내용을 받을수 있게 하자

                                inAreanm = true;

                            }

                            break;


                        case XmlPullParser.TEXT://parser가 내용에 접근했을때
                            if (inMntnm) { //inMntnm이 true일 때 태그의 내용을 저장.
                                mnt = xpp.getText();
                                inMntnm = false;
                            }
                            if (inAreanm) { //inAreanm이 true일 때 태그의 내용을 저장.
                                area = xpp.getText();
                                inAreanm = false;
                            }
                            break;
                        case XmlPullParser.END_TAG: //end tag는 곧 </item> 태그를 의미
                            if (xpp.getName().equals("item")) {
                                mountainTable mt = new mountainTable(mnt, area);

                                HashMap map = new HashMap();
                                map.put("산 이름", mnt);
                                map.put("지역", area);

                                xmlParsingData.add(mt);
                                mntMapList.add(map);
                                initem = false;
                                adapter.notifyDataSetChanged();
                            }
                            break;
                    }

                    eventType = xpp.next();
                }
                // while
                count = count+1;
                System.out.println("개수"+count);

                if(count==5)
                    Toast.makeText(tab1.this, "등산 목록이 모두 갱신되었습니다.", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                System.out.println("어딨니");
            }
        }

        private String downloadUrl(String myurl) throws IOException {

            HttpURLConnection conn = null;
            try {
                URL url = new URL(myurl);
                conn = (HttpURLConnection) url.openConnection();
                BufferedInputStream buf = new BufferedInputStream(conn.getInputStream());
                BufferedReader bufreader = new BufferedReader(new InputStreamReader(buf, "utf-8"));
                String line = null;
                String page = "";
                while ((line = bufreader.readLine()) != null) {
                    page += line;
                }
                return page;
            } catch (Exception e) {
                return " ";
            } finally {
                conn.disconnect();
            }
        }

    }
}