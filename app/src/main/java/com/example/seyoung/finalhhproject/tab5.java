package com.example.seyoung.finalhhproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.example.seyoung.finalhhproject.tab2;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.StringTokenizer;

@SuppressWarnings("deprecation")
public class tab5 extends Activity {
    ImageView imgView;
    TextView review, selected;
    View review_Text, review_Full;
    EditText title, content, mntName;
    TextView content_review, date1;
    int selectYear, selectMonth, selectDay;
    String date;
    CalendarView cv;
    ListView list;
    int p;

    //ArrayList<selectedMount> selectedMounts = new ArrayList<selectedMount>();
    ArrayList<DiarySave> dsArray = new ArrayList<DiarySave>();
    ImageButton refresh;

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab5_frame);

        Intent intent = getIntent();
        String nickname = intent.getExtras().getString("nickname");
        //String profileImagePath = intent.getExtras().getString("profileImagePath");
        String profileImagePath ="";

        TextView username = (TextView) findViewById(R.id.name);
        username.setText(nickname);

        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        imgView = (ImageView) findViewById(R.id.profile);
        review = (TextView) findViewById(R.id.review);
        selected = (TextView) findViewById(R.id.selected);


        StringBuffer buffer= new StringBuffer();

        final ArrayList<HashMap<String, String>> diaryList = new ArrayList<HashMap<String, String>>();
        list = (ListView) findViewById(R.id.diarylist);
        final SimpleAdapter adapter = new SimpleAdapter(this, diaryList, android.R.layout.simple_expandable_list_item_2, new String[]{"제목", "날짜"},
                new int[]{android.R.id.text1, android.R.id.text2});

        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        list.setAdapter(adapter);
        list.setTextFilterEnabled(true);


        try {
            //FileInputStream 객체생성, 파일명 "data.txt"
            FileInputStream fis=openFileInput("diary.txt");

            BufferedReader reader= new BufferedReader(new InputStreamReader(fis));

            String str=reader.readLine();//한 줄씩 읽어오기

            HashMap map = new HashMap();

            while(str!=null){
                System.out.println("첫줄:"+str);
                String split[] = str.split("&");

                System.out.println("제목"+split[0]);
                System.out.println("날짜"+split[1]);
                System.out.println("내용"+split[2]);

                DiarySave ds = new DiarySave(split[0], split[1], split[2]);

                map.put("제목", ds.getTitle());
                map.put("날짜", ds.getDate());
                map.put("내용", ds.getContent());

                dsArray.add(ds);
                diaryList.add(map);
                adapter.notifyDataSetChanged();
                str=reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("없음");
            e.printStackTrace();
        }




        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            String title_mini;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("error", "오류!");
                p = position;
                title_mini = diaryList.get(position).get("제목");
                review_Full = (View) View.inflate(tab5.this, R.layout.tab5_diary_full_frame, null);
                AlertDialog.Builder dlg2 = new AlertDialog.Builder(tab5.this);
                dlg2.setTitle(title_mini);
                dlg2.setView(review_Full);

                content_review = (TextView) review_Full.findViewById(R.id.content2);
                date1 = (TextView)review_Full.findViewById(R.id.date1);
                date1.setText(diaryList.get(position).get("날짜"));
                content_review.setText(diaryList.get(position).get("내용"));
                dlg2.setPositiveButton("닫기", null);
                dlg2.setNegativeButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        diaryList.remove(p);
                        adapter.notifyDataSetChanged();
                    }
                });
                dlg2.show();
            }
        });

        review.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.e("yougoterror", "오류입니당");
                review_Text = (View) View.inflate(tab5.this, R.layout.tab5_diary_frame, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(tab5.this);
                dlg.setTitle("등산 후기 등록");
                dlg.setView(review_Text);
                cv = (CalendarView) review_Text.findViewById(R.id.calendar);
                mntName = (EditText) review_Text.findViewById(R.id.mntName);

                cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                        selectYear = year;
                        selectMonth = month + 1;
                        selectDay = dayOfMonth;
                    }
                });
                dlg.setPositiveButton("등록", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 다이얼로그에 입력한 내용 받아서 DiarySave 클래스에 넣고, 거기에 저장한 정보 다시 가져와서 리스트에 동적 추가
                        title = (EditText) review_Text.findViewById(R.id.title);
                        selected.setText(mntName.getText().toString());
                        String mnt_name = selected.getText().toString();
                        content = (EditText) review_Text.findViewById(R.id.content);
                        date = Integer.toString(selectYear) + "년 " + Integer.toString(selectMonth) + "월 " + Integer.toString(selectDay) + "일";
                        HashMap map = new HashMap();
                        DiarySave ds = new DiarySave("["+mnt_name+"] "+title.getText().toString(), date, content.getText().toString());
                        map.put("제목", ds.getTitle());
                        map.put("날짜", ds.getDate());
                        map.put("내용", ds.getContent());
                        String title_diary=ds.getTitle()+"_"+ds.getDate();

                        try {
                            //FileOutputStream 객체생성, 파일명 "data.txt", 새로운 텍스트 추가하기 모드
                            FileOutputStream fos=openFileOutput("diary.txt", Context.MODE_APPEND);

                            PrintWriter writer= new PrintWriter(fos);
                            writer.print(ds.getTitle()+"&");
                            writer.print(ds.getDate()+"&");
                            writer.print(ds.getContent()+"\n");
                            writer.close();

                        } catch (FileNotFoundException e) {
                            System.out.println("저장안됩니다.");
                            e.printStackTrace();
                        }
                        dsArray.add(ds);

                        diaryList.add(map);
                        adapter.notifyDataSetChanged();
                    }
                });
                dlg.setNegativeButton("취소", null);
                dlg.show();
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
