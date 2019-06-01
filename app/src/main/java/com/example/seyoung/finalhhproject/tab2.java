package com.example.seyoung.finalhhproject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class tab2 extends Activity {

    /*
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

    }*/


    //큰 체크박스들
    CheckBox chkAgree;//"지역"
    CheckBox chkAgree2;//"높이"
    CheckBox chkAgree3;//"산정보 주제"

    //chAgree2("높이")에 들어갈 checkBox3개
    CheckBox high1;//상
    CheckBox high2;//중
    CheckBox high3;//하

    //"지역" 체크박스 선택시 스피너
    private Spinner spinner2;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    //"산정보 주제" 체크박스 선택시 스피너
    private Spinner spinner3;
    ArrayList<String> arrayList2;
    ArrayAdapter<String> arrayAdapter2;

    //선택완료 버튼
    Button btSearch;

    // String logt= "mountain";


    //리스트뷰
    ListView listview;
    ListViewAdapter adapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab2_frame);

        chkAgree = (CheckBox) findViewById(R.id.ChkAgree);
        chkAgree2 = (CheckBox) findViewById(R.id.ChkAgree2);
        chkAgree3 = (CheckBox) findViewById(R.id.ChkAgree3);


        high1 = (CheckBox) findViewById(R.id.high1);
        high2 = (CheckBox) findViewById(R.id.high2);
        high3 = (CheckBox) findViewById(R.id.high3);



        //"지역" 체크박스 선택시 스피너 안에 들어갈 arrayList
        arrayList = new ArrayList<>();

        arrayList.add("서울특별시");arrayList.add("부산광역시");arrayList.add("대구광역시");arrayList.add("인천광역시");
        arrayList.add("광주광역시");arrayList.add("대전광역시");arrayList.add("울산광역시");arrayList.add("경기도");
        arrayList.add("강원도");arrayList.add("충청북도");arrayList.add("충청남도");arrayList.add("전라북도");
        arrayList.add("전라남도");arrayList.add("경상북도");arrayList.add("경상남도");arrayList.add("제주도");



        arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                arrayList);



        spinner2 = (Spinner)findViewById(R.id.spinner2);
        spinner2.setAdapter(arrayAdapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),arrayList.get(i)+"가 선택되었습니다.",
                        Toast.LENGTH_SHORT).show();

            }

            @Override

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        spinner2.setVisibility(android.view.View.INVISIBLE);

        chkAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                // "지역" 체크박스  체크되면 해당 스피너 보이도록 설정
                if (chkAgree.isChecked() == true) {
                    spinner2.setVisibility(android.view.View.VISIBLE);
                } else {
                    spinner2.setVisibility(android.view.View.INVISIBLE);
                }
            }
        });


        //여기까지     "지역" 체크박스 선택시 스피너




        //이제부터  "산정보 주제" 체크박스 선택시 스피너에 들어갈 어레이리스트

        arrayList2 = new ArrayList<>();

        arrayList2.add("계곡");arrayList2.add("단풍");arrayList2.add("억새");arrayList2.add("바다");
        arrayList2.add("문화유적");arrayList2.add("일출/일몰");arrayList2.add("가족산행");arrayList2.add("바위");
        arrayList2.add("봄꽃");arrayList2.add("조망");arrayList2.add("설경");


        arrayAdapter2 = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                arrayList2);



        spinner3 = (Spinner)findViewById(R.id.spinner3);
        spinner3.setAdapter(arrayAdapter2);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),arrayList2.get(i)+"가 선택되었습니다.",
                        Toast.LENGTH_SHORT).show();

            }

            @Override

            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });



        //여기까지  "산정보 주제" 체크박스 선택시 스피너


        high1.setVisibility(android.view.View.INVISIBLE);//처음에는 "높이" 상중하 체크박스 3개 모두 안보이게
        high2.setVisibility(android.view.View.INVISIBLE);
        high3.setVisibility(android.view.View.INVISIBLE);



        chkAgree2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                // "높이" 체크되면 모두 보이도록 설정
                if (chkAgree2.isChecked() == true) {
                    high1.setVisibility(android.view.View.VISIBLE);
                    high2.setVisibility(android.view.View.VISIBLE);
                    high3.setVisibility(android.view.View.VISIBLE);

                } else {
                    high1.setVisibility(android.view.View.INVISIBLE);
                    high2.setVisibility(android.view.View.INVISIBLE);
                    high3.setVisibility(android.view.View.INVISIBLE);
                }
            }
        });




        spinner3.setVisibility(android.view.View.INVISIBLE);

        chkAgree3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                // "산정보 주제" 체크되면 스피너 보이도록 설정
                if (chkAgree3.isChecked() == true) {

                    spinner3.setVisibility(android.view.View.VISIBLE);

                } else {
                    spinner3.setVisibility(android.view.View.INVISIBLE);
                }
            }
        });




        btSearch= (Button) findViewById(R.id.btnSearch);
        //tv= (TextView) findViewById(R.id.data);


        listview = (ListView)findViewById(R.id.listview1);

/*


        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String serviceUrl= "http://openapi.forest.go.kr/openapi/service/trailInfoService/getforeststoryservice";
                String serviceKey="AH9qYYkdDabmHdMVNVZt4viR7E2TclJYSbjCck2jgrsVTe%2FcBC7lyWLbEBMoUo3gtUrixKaUpRRBM%2BeVwGJIrQ%3D%3D";
                //String strSrch= etBus.getText().toString();
                //String strUrl= serviceUrl+"?ServiceKey="+serviceKey +"&strSrch="+strSrch;




                //실제 넣어야할것 예시
                //String strUrl=serviceUrl+"?mntnInfoAraCd="+"01"+"?mntnHght="+"1561"+"?mntnInfoThmCd="+"02"+"&serviceKey="+serviceKey;
                String strUrl=serviceUrl+"?mntnNm="+"가리왕산"+"&serviceKey="+serviceKey;
                new DownloadWebpageTask().execute(strUrl);
        }
        });



지금 버튼 눌러도 아무일도 안일어나서 주석처리..
*/



    }

    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                return (String)downloadUrl((String)urls[0]);
            } catch (IOException e) {
                return "==>다운로드 실패";
            }
        }

        protected void onPostExecute(String result) {
            //Log.d(tag, result);
            //tv.append(result + "\n");
            //tv.append("========== 파싱 결과 ==========\n");



            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput(new StringReader(result));
                int eventType = xpp.getEventType();
                boolean  bSetNo=false, bSetmnt=false;
                String mnt="";

                adapter=new ListViewAdapter();

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if(eventType == XmlPullParser.START_DOCUMENT) {
                        ;
                    } else if(eventType == XmlPullParser.START_TAG) {
                        String tag_name = xpp.getName();
                        if (tag_name.equals("mntnsbttlinfo"))//산정보 부제의 경우
                            bSetmnt = true;
                        else if (tag_name.equals("no"))
                            bSetNo = true;

                    } else if(eventType == XmlPullParser.TEXT) {

                        if (bSetmnt) {
                            //String content = xpp.getText();
                            //tv.append(xpp.getText()+"\n");
                            mnt=xpp.getText();
                            bSetmnt = false;
                        }

                        if (bSetNo) {
                            //String content = xpp.getText();
                            //tv.append(xpp.getText()+"\n");
                            adapter.addItem(mnt);
                            bSetNo = false;
                        }


                    } else if(eventType == XmlPullParser.END_TAG) {
                        ;
                    }
                    eventType = xpp.next();
                } // while
                listview.setAdapter(adapter);//리스트뷰에 붙이기
            } catch (Exception e) {
                // tv.setText("\n"+e.getMessage());
            }
        }

        private String downloadUrl(String myurl) throws IOException {

            HttpURLConnection conn = null;
            try {
                //Log.d(tag, "downloadUrl : "+  myurl);
                URL url = new URL(myurl);
                conn = (HttpURLConnection) url.openConnection();
                BufferedInputStream buf = new BufferedInputStream(conn.getInputStream());
                BufferedReader bufreader = new BufferedReader(new InputStreamReader(buf, "utf-8"));
                String line = null;
                String page = "";
                while((line = bufreader.readLine()) != null) {
                    page += line;
                }

                return page;
            } catch(Exception e){
                return " ";
            }
            finally {
                conn.disconnect();
            }
        }
    }

}

