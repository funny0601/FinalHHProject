package com.example.seyoung.finalhhproject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.util.ArrayList;

public class tab1 extends Activity {
    private SharedPreferences appData;

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab1_frame);

        StrictMode.enableDefaults();


        boolean initem = false;

        ArrayList<String> mnt_name_list = new ArrayList<String>();
        ArrayList<String> area_name_list = new ArrayList<String>();

        boolean inMntnm =false; String mntnm= null; // 산명
        boolean inAreanm=false; String areanm = null; // 산정보소재지
        boolean inMntheight= false; String mntheight = null; // 산정보높이
        boolean inAeatreason = false; String aeatreason = null; // 100대 명산 선정 이유
        boolean inTransport = false; String transport = null; // 대중교통정보설명
        boolean inTourisminf = false; String tourisminf = null; // 주변관광정보설명


        try{

            URL url = new URL("http://openapi.forest.go.kr/openapi/service/cultureInfoService/gdTrailInfoOpenAPI?"
                    + "&ServiceKey="
                    + "" // 본인 서비스키 넣으면 됨
                    //+ "&searchMtNm=%EA%B0%80&searchArNm=%EA%B0%95%EC%9B%90&pageNo=1&startPage=1&numOfRows=100&pageSize=10"
            ); //검색 URL부분

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            parserCreator.setNamespaceAware(true);
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);
            String text = "";

            int parserEvent = parser.getEventType();
            Toast.makeText(this,"파싱시작합니다.", Toast.LENGTH_LONG);

            while (parserEvent != XmlPullParser.END_DOCUMENT){
                switch(parserEvent){
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if(parser.getName().equals("mntnm")){ //mntnm 만나면 내용을 받을수 있게 하자
                            inMntnm = true;
                        }
                        if(parser.getName().equals("areanm")){ //areanm 만나면 내용을 받을수 있게 하자
                            inAreanm = true;
                        }
                        if(parser.getName().equals("mntheight")){ //mntheight 만나면 내용을 받을수 있게 하자
                            inMntheight = true;
                        }
                        if(parser.getName().equals("aeatreason")){ //aeatreason 만나면 내용을 받을수 있게 하자
                            inAeatreason = true;
                        }
                        if(parser.getName().equals("transport")){ //transport 만나면 내용을 받을수 있게 하자
                            inTransport = true;
                        }
                        if(parser.getName().equals("tourisminf")){ //tourisminf 만나면 내용을 받을수 있게 하자
                            inTourisminf = true;
                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        if(inMntnm){ //inMntnm이 true일 때 태그의 내용을 저장.
                            mntnm = parser.getText();
                            mnt_name_list.add(mntnm);
                            inMntnm = false;
                        }
                        if(inAreanm){ //inAreanm이 true일 때 태그의 내용을 저장.
                            areanm = parser.getText();
                            area_name_list.add(areanm);
                            inAreanm = false;
                        }
                        if(inMntheight){ //inMntheight이 true일 때 태그의 내용을 저장.
                            mntheight = parser.getText();
                            inMntheight = false;
                        }
                        if(inAeatreason){ //inAeatreason이 true일 때 태그의 내용을 저장.
                            aeatreason = parser.getText();
                            inAeatreason = false;
                        }
                        if(inTransport){ //inTransport이 true일 때 태그의 내용을 저장.
                            transport = parser.getText();
                            inTransport = false;
                        }
                        if(inTourisminf){ //inTourisminf이 true일 때 태그의 내용을 저장.
                            tourisminf = parser.getText();
                            inTourisminf = false;
                        }
                        break;
                    case XmlPullParser.END_TAG: //end tag는 곧 </item> 태그를 의미
                        if(parser.getName().equals("item")){
                            /*status1.setText(status1.getText()+"산코드 : "+ mntncd +"\n 산명: "+ mntnm +"\n 산정보소재지 : " + areanm
                                    +"\n 산정보높이 : " + mntheight +  "\n 100대명산 선정 이유 : " + aeatreason+ "\n 산정보개관 : " + overview
                                    +"\n 산정보내용 : " +details+ "\n 대중교통정보설명 : " + transport + "\n 주변관광정보설명 : " +tourisminf
                                    +"\n");
                                    */

                            initem = false;

                        }

                        break;
                }

                parserEvent = parser.next();
            }
            /*
            for(int i=0; i<area_name_list.size(); i++){
                text = text + area_name_list.get(i).toString() + " ";
            }
            status1.setText(text);
            */


        } catch(Exception e){
            //status1.setText("에러가..났습니다...");
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG);
            System.out.println(e.toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_list_item_multiple_choice, mnt_name_list);
        ListView list = (ListView)findViewById(R.id.listView1);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        list.setAdapter(adapter);

        Intent intent = getIntent();
        String nickname = intent.getExtras().getString("nickname");
        String profileImagePath = intent.getExtras().getString("profileImagePath");




        /*
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        */
    }
}
