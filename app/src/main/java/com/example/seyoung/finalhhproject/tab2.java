package com.example.seyoung.finalhhproject;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;

public class tab2 extends Activity {
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.j);

        StrictMode.enableDefaults();

        TextView status1 = (TextView)findViewById(R.id.result); //파싱된 결과확인!

        boolean initem = false;

        boolean inMntncd = false;  String mntncd= null; // 산코드
        boolean inMntnm =false; String mntnm = null; // 산명
        boolean inAreanm=false; String areanm = null; // 산정보소재지
        boolean inMntheight= false; String mntheight = null; // 산정보높이
        boolean inAeatreason = false; String aeatreason = null; // 100대 명산 선정 이유
        boolean inOverview = false; String overview = null; // 산정보개관
        boolean inDetails = false; String details = null; // 산정보내용
        boolean inTransport = false; String transport = null; // 대중교통정보설명
        boolean inTourisminf = false; String tourisminf = null; // 주변관광정보설명


        try{

            URL url = new URL("http://openapi.forest.go.kr/openapi/service/cultureInfoService/gdTrailInfoOpenAPI?"
                    + "&ServiceKey="
                    + "" // 본인 서비스키 넣으면 됨
                    + "&searchMtNm=%EA%B0%80&searchArNm=%EA%B0%95%EC%9B%90&pageNo=1&startPage=1&numOfRows=10&pageSize=1"); //검색 URL부분

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            parserCreator.setNamespaceAware(true);
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);


            int parserEvent = parser.getEventType();
            Toast.makeText(this,"파싱시작합니다.", Toast.LENGTH_LONG);

            while (parserEvent != XmlPullParser.END_DOCUMENT){
                switch(parserEvent){
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if(parser.getName().equals("mntncd")){ //mntncd 만나면 내용을 받을수 있게 하자
                            inMntncd = true;
                        }
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
                        if(parser.getName().equals("overview")){ //overview 만나면 내용을 받을수 있게 하자
                            inOverview = true;
                        }
                        if(parser.getName().equals("details")){ //details 만나면 내용을 받을수 있게 하자
                            inDetails = true;
                        }
                        if(parser.getName().equals("transport")){ //transport 만나면 내용을 받을수 있게 하자
                            inTransport = true;
                        }
                        if(parser.getName().equals("tourisminf")){ //tourisminf 만나면 내용을 받을수 있게 하자
                            inTourisminf = true;
                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        if(inMntncd){
                            mntncd = parser.getText();
                            inMntncd = false;
                        }

                        if(inMntnm){ //inMntnm이 true일 때 태그의 내용을 저장.
                            mntnm = parser.getText();
                            inMntnm = false;
                        }
                        if(inAreanm){ //inAreanm이 true일 때 태그의 내용을 저장.
                            areanm = parser.getText();
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
                        if(inOverview){ //inOverview이 true일 때 태그의 내용을 저장.
                            overview = parser.getText();
                            inOverview = false;
                        }
                        if(inDetails){ //inDetails이 true일 때 태그의 내용을 저장.
                            details = parser.getText();
                            inDetails = false;
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
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("item")){
                            status1.setText(status1.getText()+"산코드 : "+ mntncd +"\n 산명: "+ mntnm +"\n 산정보소재지 : " + areanm
                                    +"\n 산정보높이 : " + mntheight +  "\n 100대명산 선정 이유 : " + aeatreason+ "\n 산정보개관 : " + overview
                                    +"\n 산정보내용 : " +details+ "\n 대중교통정보설명 : " + transport + "\n 주변관광정보설명 : " +tourisminf
                                    +"\n");
                            initem = false;
                        }
                        break;
                }
                parserEvent = parser.next();
            }
        } catch(Exception e){
            status1.setText("에러가..났습니다...");
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG);
            System.out.println(e.toString());
        }
    }
}

