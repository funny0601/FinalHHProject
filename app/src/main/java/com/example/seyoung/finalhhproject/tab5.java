package com.example.seyoung.finalhhproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("deprecation")
public class tab5 extends Activity {
    ImageView imgView;
    TextView review, selected;
    View review_Text;
    EditText title, content;
    ArrayList<selectedMount> selectedMounts = new ArrayList<selectedMount>();
    ArrayList<DiarySave> dsArray = new ArrayList<DiarySave>();

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab5_frame);

        Intent intent = getIntent();
        String nickname = intent.getExtras().getString("nickname");
        String profileImagePath = intent.getExtras().getString("profileImagePath");
        //String profileImagePath ="";

        TextView username = (TextView) findViewById(R.id.name);
        username.setText(nickname);

        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        imgView = (ImageView) findViewById(R.id.profile);
        review = (TextView) findViewById(R.id.review);
        selected = (TextView) findViewById(R.id.selected);

        final ArrayList<HashMap<String, String>> diaryList = new ArrayList<HashMap<String, String>>();
        ListView list = (ListView) findViewById(R.id.diarylist);

        final SimpleAdapter adapter = new SimpleAdapter(this, diaryList, android.R.layout.simple_expandable_list_item_2, new String[]{"제목", "날짜"},
                new int[]{android.R.id.text1, android.R.id.text2});

        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        list.setAdapter(adapter);
        list.setTextFilterEnabled(true);

        review.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Log.e("yougoterror", "오류입니당");
                review_Text = (View) View.inflate(tab5.this, R.layout.tab5_diary_frame, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(tab5.this);
                dlg.setTitle("등산 후기 등록");
                dlg.setView(review_Text);


                dlg.setPositiveButton("등록", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 다이얼로그에 입력한 내용 받아서 DiarySave 클래스에 넣고, 거기에 저장한 정보 다시 가져와서 리스트에 동적 추가
                        title = (EditText) review_Text.findViewById(R.id.title);
                        content = (EditText) review_Text.findViewById(R.id.content);
                        HashMap map = new HashMap();
                        DiarySave ds = new DiarySave(title.getText().toString(), content.getText().toString(), "20190620");
                        map.put("제목", ds.getTitle());
                        map.put("날짜", ds.getDate());
                        dsArray.add(ds);

                        diaryList.add(map);
                        adapter.notifyDataSetChanged();
                    }
                });

                /*
                diaryLIst.remove(position); //롱클릭시 삭제함 p.433
                adapter.notifyDataSetChanged();
                 */

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
