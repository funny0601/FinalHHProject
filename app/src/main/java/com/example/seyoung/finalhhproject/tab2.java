package com.example.seyoung.finalhhproject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
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
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Random;

// 지금 쓰는 데이터 이름 : 산 정보 조회  , 대략 데이터 수가 15000 개 정도..
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

    String keyword;

    //큰 체크박스들
    CheckBox chkAgree;//"지역"
    CheckBox chkAgree2;//"산정보 주제"
    CheckBox chkAgree3;//"산정보 계절"

    //"지역" 체크박스 선택시 스피너
    private Spinner spinner2;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    //"산정보 주제" 체크박스 선택시 스피너
    private Spinner spinner3;
    ArrayList<String> arrayList2;
    ArrayAdapter<String> arrayAdapter2;

    //"산정보 계절" 체크박스 선택시 스피너
    private Spinner spinner4;
    ArrayList<String> arrayList3;
    ArrayAdapter<String> arrayAdapter3;

    //선택완료 버튼
    Button btnSearch;
    Button btnInit;

    // String logt= "mountain";


    //리스트뷰
    ListView listview;
    ListViewAdapter adapter;

    String key2;//산지역정보
    String key3;//산주제코드


    String key4;//산정보계절코드

    String mymountain;//산이름




    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab2_frame);

        chkAgree = (CheckBox) findViewById(R.id.ChkAgree);
        chkAgree2 = (CheckBox) findViewById(R.id.ChkAgree2);
        chkAgree3 = (CheckBox) findViewById(R.id.ChkAgree3);

        //"지역" 체크박스 선택시 스피너 안에 들어갈 arrayList
        arrayList = new ArrayList<>();

        arrayList.add("선택하세요");
        arrayList.add("서울특별시");
        arrayList.add("부산광역시");
        arrayList.add("대구광역시");
        arrayList.add("인천광역시");
        arrayList.add("광주광역시");
        arrayList.add("대전광역시");
        arrayList.add("울산광역시");
        arrayList.add("경기도");
        arrayList.add("강원도");
        arrayList.add("충청북도");
        arrayList.add("충청남도");
        arrayList.add("전라북도");
        arrayList.add("전라남도");
        arrayList.add("경상북도");
        arrayList.add("경상남도");
        arrayList.add("제주도");


        arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                arrayList);


        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setAdapter(arrayAdapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), arrayList.get(i) + "가 선택되었습니다.",
                        Toast.LENGTH_SHORT).show();
                key2= Integer.toString(i);
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
                    key2="";//선택안하면 "" 처리
                }
            }
        });


        //여기까지     "지역" 체크박스 선택시 스피너


        //이제부터  "산정보 주제" 체크박스 선택시 스피너에 들어갈 어레이리스트

        arrayList2 = new ArrayList<>();

        arrayList2.add("선택하세요");
        arrayList2.add("계곡");
        arrayList2.add("단풍");
        arrayList2.add("억새");
        arrayList2.add("바다");
        arrayList2.add("문화유적");
        arrayList2.add("일출/일몰");
        arrayList2.add("가족산행");
        arrayList2.add("바위");
        arrayList2.add("봄꽃");
        arrayList2.add("조망");
        arrayList2.add("설경");


        arrayAdapter2 = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                arrayList2);

        spinner3 = (Spinner) findViewById(R.id.spinner3);
        spinner3.setAdapter(arrayAdapter2);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), arrayList2.get(i) + "가 선택되었습니다.",
                        Toast.LENGTH_SHORT).show();

                key3= Integer.toString(i);


            }

            @Override

            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });




        //이제부터  "산정보 계절" 체크박스 선택시 스피너에 들어갈 어레이리스트

        arrayList3 = new ArrayList<>();

        arrayList3.add("선택하세요");
        arrayList3.add("봄");
        arrayList3.add("여름");
        arrayList3.add("가을");
        arrayList3.add("겨울");
        arrayList3.add("봄/여름");
        arrayList3.add("봄/가을");
        arrayList3.add("봄/겨울");
        arrayList3.add("여름/가을");
        arrayList3.add("여름/겨울");
        arrayList3.add("가을/겨울");
        arrayList3.add("사계절");


        arrayAdapter3 = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                arrayList3);


        spinner4 = (Spinner) findViewById(R.id.spinner4);
        spinner4.setAdapter(arrayAdapter3);
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), arrayList3.get(i) + "가 선택되었습니다.",
                        Toast.LENGTH_SHORT).show();

                key4= Integer.toString(i);

            }

            @Override

            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });




        //여기까지  "산정보 주제" 체크박스 선택시 스피너

        spinner3.setVisibility(android.view.View.INVISIBLE);

        chkAgree2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                // "높이" 체크되면 모두 보이도록 설정
                if (chkAgree2.isChecked() == true) {

                    spinner3.setVisibility(android.view.View.VISIBLE);

                } else {
                    spinner3.setVisibility(android.view.View.INVISIBLE);
                    key3="";//선택안하면 "" 처리
                }
            }
        });


        spinner4.setVisibility(android.view.View.INVISIBLE);

        chkAgree3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                // "산정보 계절" 체크되면 스피너 보이도록 설정
                if (chkAgree3.isChecked() == true) {

                    spinner4.setVisibility(android.view.View.VISIBLE);

                } else {
                    spinner4.setVisibility(android.view.View.INVISIBLE);
                    key4= "";//선택안하면 "" 처리
                }
            }
        });




        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnInit = (Button) findViewById(R.id.btnInit);
        listview = (ListView) findViewById(R.id.listview1);

        keyword=null;


        // 인코딩 에러날 수 있어서 try-catch 처리한거임
        // 체크박스에서 선택된 변수들에 맞게 여기에 집어넣을 수 있게 함수를 만들던
        // 각 체크박스 별로 문자 다 넣어놓고 배열 만들어서 여기에서는 배열만 넣어서 인코딩하던
        // 효율적으로 처리만 해주면 될듯
        try {
            keyword = URLEncoder.encode("가리왕산", "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }



        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String serviceUrl = "http://openapi.forest.go.kr/openapi/service/trailInfoService/getforeststoryservice";
                //본인 키값!
                String serviceKey = "AH9qYYkdDabmHdMVNVZt4viR7E2TclJYSbjCck2jgrsVTe%2FcBC7lyWLbEBMoUo3gtUrixKaUpRRBM%2BeVwGJIrQ%3D%3D"
                        //"cg57liprV33JjaeFy1LJgzsD6EYcgoaVf9Du7P2W8P47pfco85kGJPMrOhESrZluVfW1D2k%2BgX7yxn%2F40U6VWA%3D%3D"
                        ;


                String strUrl = serviceUrl + "?serviceKey=" + serviceKey + "&mntnInfoAraCd="+key2+"&mntnInfoThmCd="+key3+"&mntnInfoSsnCd="+key4;

                new DownloadWebpageTask().execute(strUrl);


            }
        });

        // 초기화 버튼 누르면 체크박스 누른거 다 해제
        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chkAgree.setChecked(false);
                chkAgree2.setChecked(false);
                chkAgree3.setChecked(false);
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

        protected void onPostExecute(String result){
            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput(new StringReader(result));
                int eventType = xpp.getEventType();
                boolean bSetmnt = false;
                String mnt = "";

               // adapter = new ListViewAdapter();

                // 지금 신경써야하는 태그가 3가지 이니까 3가지에 맞게 더 추가해서 다 넣어줘야하는데
                // 만약 유저가 3가지 조건중에 2개만 선택하면 (예를 들어 산이름, 산높이, 산주제중에 산이름하고 높이만 선택했을때)
                // 그럼 선택되지 않은 산주제 태그에 대해서는 그냥 "" 이 값 (근데 이건 위에서 변수 선언할때 default로 넣어줄거니까
                // 넣어주면 &mntnHght= 이렇게 하고 바로 그냥 조건안에 넣어준거 없으니까 별 에러 안내고 넘어감
                // 모르겠으면 직접 링크에 숫자 넣었다가 뺐다가 해보기

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_DOCUMENT) {
                    } else if (eventType == XmlPullParser.START_TAG) {
                        String tag_name = xpp.getName();
                        if (tag_name.equals("mntnnm")) //산이름
                            bSetmnt = true;
                    } else if (eventType == XmlPullParser.TEXT) {
                        if (bSetmnt) {
                            mnt = xpp.getText();
                            System.out.println("hello mountain name is"+mnt);
                            mymountain=mnt;
                           // Random random=new Random();
                           // random.nextInt(i);

                            //선택한 조건으로 버튼누르면 구글맵에 뜬다!
                            Intent mapIntent1=new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="+mymountain));
                            startActivity(mapIntent1);


                           //adapter.addItem(mnt); 원래 밑에 리스트 뜨게 하는건데 사용안함
                            bSetmnt = false;
                        }

                        //else{
                         //   mnt="해당하는산이 존재하지않습니다.";
                        //    adapter.addItem(mnt);
                       // }

                    } else if (eventType == XmlPullParser.END_TAG) {
                    }
                    eventType = xpp.next();
                } // while
                listview.setAdapter(adapter);//리스트뷰에 붙이기
            } catch (Exception e) {

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

