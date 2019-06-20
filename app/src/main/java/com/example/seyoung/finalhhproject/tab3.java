package com.example.seyoung.finalhhproject;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class tab3 extends Activity {

    private Spinner spinnertab3;
    ArrayList<String> arrayListtab3;
    ArrayAdapter<String> arrayAdaptertab3;

    TextView weathertv;

    String nx = "60";// 격자X
    String ny = "127";// 격자Y




    Button btn1,btn2, btn3, btn4; //재생버튼과  멈춤 버튼, 이전곡 , 다음곡
    MediaPlayer mp,mp1,mp2;

    private int songs[]; // 음원 목록
    private int playing = -1; // 현재 연주중인

    TextView mFileName;

    SeekBar mProgress;
    boolean wasPlaying;


    //여기부터는 위에 날씨뜨게 하는것

    //선택완료 버튼
    Button btnSearch;

    //리스트뷰
    ListView listview;
    ListViewAdapter adapter;


    String mymountain;//산이름
    int index=0;


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.tab3_frame);

        weathertv = (TextView) findViewById(R.id.weathertv);

        arrayListtab3 = new ArrayList<>();

        arrayListtab3.add("서울특별시");
        arrayListtab3.add("부산광역시");
        arrayListtab3.add("대구광역시");
        arrayListtab3.add("인천광역시");
        arrayListtab3.add("광주광역시");
        arrayListtab3.add("대전광역시");
        arrayListtab3.add("울산광역시");
        arrayListtab3.add("경기도");
        arrayListtab3.add("강원도");
        arrayListtab3.add("충청북도");
        arrayListtab3.add("충청남도");
        arrayListtab3.add("전라북도");
        arrayListtab3.add("전라남도");
        arrayListtab3.add("경상북도");
        arrayListtab3.add("경상남도");
        arrayListtab3.add("제주도");


        arrayAdaptertab3 = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                arrayListtab3);


        spinnertab3 = (Spinner) findViewById(R.id.spinnertab3);
        spinnertab3.setAdapter(arrayAdaptertab3);
        spinnertab3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), arrayListtab3.get(i) + "가 선택되었습니다.",
                        Toast.LENGTH_SHORT).show();
               // key2= Integer.toString(i);
                weathertv.setText(arrayListtab3.get(i)+"의 현재 날씨는?");
                if(i==0){//서울
                    nx="60";
                    ny="127";
                }
                if(i==1){//부산
                    nx="98";
                    ny="76";
                }
                if(i==2){//대구
                    nx="89";
                    ny="90";
                }
                if(i==3){//인천
                    nx="55";
                    ny="124";
                }
                if(i==4){//광주
                    nx="58";
                    ny="74";
                }
                if(i==5){//대전
                    nx="67";
                    ny="100";
                }
                if(i==6){//울산
                    nx="102";
                    ny="84";
                }
                if(i==7){//경기도
                    nx="60";
                    ny="120";
                }
                if(i==8){//강원도
                    nx="73";
                    ny="134";
                }
                if(i==9){//충청북도
                    nx="69";
                    ny="107";
                }
                if(i==10){//충청남도
                    nx="68";
                    ny="100";
                }
                if(i==11){//전라북도
                    nx="63";
                    ny="89";
                }
                if(i==12){//전라남도
                    nx="51";
                    ny="67";
                }
                if(i==13){//경상북도
                    nx="89";
                    ny="91";
                }
                if(i==14){//경상남도
                    nx="91";
                    ny="77";
                }
                if(i==15){//제주도
                    nx="52";
                    ny="38";
                }

            }

            @Override

            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        btn1 = (Button)findViewById(R.id.playBtn);
        btn2 = (Button)findViewById(R.id.pauseBtn);
        btn3 = (Button)findViewById(R.id.prevBtn);
        btn4 = (Button)findViewById(R.id.nextBtn);


        mFileName = (TextView) findViewById(R.id.filename);

        mProgress = (SeekBar) findViewById(R.id.mProgress);

       // mProgress.setOnSeekBarChangeListener(mOnSeek);

      //  mProgressHandler.sendEmptyMessageDelayed(0, 200);


        songs = new int[3];
        songs[0] = R.raw.music;
        songs[1] = R.raw.music1;
        songs[2] = R.raw.music2;
        mp = MediaPlayer.create(tab3.this, R.raw.music);
        mp1 = MediaPlayer.create(tab3.this, R.raw.music1);
        mp2 = MediaPlayer.create(tab3.this, R.raw.music2);






        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playing=0;
                if( mp!=null ) {
                    mp.stop(); // 혹은 pause
                }
                mp = MediaPlayer.create(tab3.this, songs[ playing ]);
                mFileName.setText("파일 : "+(playing+1)+"번째음악");
                mp.start();



                mProgress.setMax(mp.getDuration()); //음악의 총길이를 가져와 sb의 길이로 만든다.

                mProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        if(b){ //3번째로 넘어오는 값 b는 사용자가 움직여서 값이 변하면 true, setProgress등으로 움직이면 false
                            mp.seekTo(i); //재생위치 변경해주기
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        while (mp.isPlaying()) {

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            mProgress.setProgress(mp.getCurrentPosition());

                        }
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });








            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //playing=0;
                //if( mp!=null ) {
               //     mp.stop(); // 혹은 pause
               // }
               // mp = MediaPlayer.create(tab3.this, songs[ playing ]);
                mp.stop();
                //mp.seekTo(0); //0으로 재생위치변경

               // btn.setText(R.string.start);
                mProgress.setProgress(0);


            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playing = (playing+5)%songs.length; // 목록의 끝에 도달하면 다시 첫번째를 선택.
                if( mp!=null ) {
                    mp.stop(); // 혹은 pause
                }
                mp = MediaPlayer.create(tab3.this, songs[ playing ]);
                mFileName.setText("파일 : "+(playing+1)+"번째음악");
                mp.start();
            }
        });



        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playing = (playing+1)%songs.length; // 목록의 끝에 도달하면 다시 첫번째를 선택.
                if( mp!=null ) {
                    mp.stop(); // 혹은 pause
                }
                mp = MediaPlayer.create(tab3.this, songs[ playing ]);
                mFileName.setText("파일 : "+(playing+1)+"번째음악");
                mp.start();
            }
        });




    //음악




    btnSearch = (Button) findViewById(R.id.btnSearch);
        listview = (ListView) findViewById(R.id.listview1);



        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //본인 키값!
                String serviceKey = "AH9qYYkdDabmHdMVNVZt4viR7E2TclJYSbjCck2jgrsVTe%2FcBC7lyWLbEBMoUo3gtUrixKaUpRRBM%2BeVwGJIrQ%3D%3D";

                //자신이 조회를 원하는 지역의 경도와 위도를 입력해주세요

                String baseDate = "20190620";
                // 자신이 조회하고싶은 날짜를 입력해주세요
                String baseTime = "0500";
                // 자신이 조회하고싶은 시간대를 입력해주세요
                // 서비스 인증키입니다. 공공데이터포털에서 제공해준 인증키를 넣어주시면 됩니다.
                // String serviceKey = "개인별로 받은 인증키를 넣어주세요";
                // 정보를 모아서 URL정보를 만들면됩니다. 맨 마지막 "&_type=json"에 따라 반환 데이터의 형태가 정해집니다.
                String strUrl = "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData?"+ "serviceKey=" + serviceKey + "&base_date=" + baseDate + "&base_time=" + baseTime+ "&nx="+ nx + "&ny=" + ny ;
                System.out.println(strUrl);
                btnSearch.setText("선택되었습니다.");

                // String strUrl = serviceUrl + "?serviceKey=" + serviceKey + "&mntnInfoAraCd="+key2+"&mntnInfoThmCd="+key3+"&mntnInfoSsnCd="+key4;
                System.out.println("nx:"+nx);
                System.out.println("ny:"+ny);
                new DownloadWebpageTask().execute(strUrl);


            }
        });


    }



    //onCreate

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
                index=0;
                adapter = new ListViewAdapter();

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_DOCUMENT) {
                    } else if (eventType == XmlPullParser.START_TAG) {
                        String tag_name = xpp.getName();
                        if (tag_name.equals("fcstValue")) //산이름
                            bSetmnt = true;

                    } else if (eventType == XmlPullParser.TEXT) {
                        if (bSetmnt) {

                            mnt = xpp.getText();
                            index++;
                            if(index==1){
                                System.out.println("hello mountain name is"+mnt);
                                mymountain=mnt;
                                adapter.addItem("강수확률 : "+mnt+"%");
                                bSetmnt = false;
                            }
                            if(index==3){
                                System.out.println("hello mountain name is"+mnt);
                                mymountain=mnt;
                                adapter.addItem("습도 : "+mnt+"%");
                                bSetmnt = false;
                            }
                            if(index==5){
                                System.out.println("hello mountain name is"+mnt);
                                Integer mnt2=Integer.parseInt(mnt);
                                adapter.addItem("3시간 이내 평균기온 : "+(double)(mnt2/3)+"℃");
                                bSetmnt = false;
                            }
                            if(index==6){
                                System.out.println("hello mountain name is"+mnt);
                                mymountain=mnt;
                                adapter.addItem("풍속(동서) : "+mnt+"m/s");
                                bSetmnt = false;
                            }



                        }

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
