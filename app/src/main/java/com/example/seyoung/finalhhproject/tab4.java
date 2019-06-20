package com.example.seyoung.finalhhproject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class tab4 extends Activity {

    TextView foodtv;//음식에 관한 설명을 적는 텍스트뷰

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab4_frame);

        Gallery gallery = (Gallery) findViewById(R.id.gallery1);//갤러리
        foodtv=(TextView)findViewById(R.id.foodtv) ;//텍스트뷰

        MyGalleryAdapter galAdapter = new MyGalleryAdapter(this);
        gallery.setAdapter(galAdapter);
    }

    public class MyGalleryAdapter extends BaseAdapter {

        Context context;
        Integer[] posterID = { R.drawable.beef, R.drawable.bibimbap,
                R.drawable.chicken, R.drawable.coffee, R.drawable.fish,
                R.drawable.hambuger, R.drawable.jajang, R.drawable.kimbab,
                R.drawable.pasta, R.drawable.pizza, R.drawable.ramen,
                R.drawable.shrimp, R.drawable.sushi, R.drawable.torti,
                R.drawable.mandu, R.drawable.noodle };


        //음식의 이름과 설명
        String[] foodTitle = { "부드러운 소고기 : 280kcal", "맛있는 비빔밥 : 706kcal", "바삭바삭 치킨 : 1700kcal",
                "달달한 커피 : 200kcal", "쫄깃한 회 : 100kcal", "동그란 햄버거 : 558kcal", " 맛있는 자장면 : 864kcal",
                "영양가득 김밥 : 500kcal", "담백한 파스타 : 400kcal", "맛있는 피자 : 253kcal", "간편한 라면 : 422kcal",
                "건강한 새우요리 : 700kcal", "싱싱한 초밥 : 140kcal", "이국적인 토르티야 : 319kcal", "간편한 만두 : 52kcal", "상쾌한 국수 : 374kcal"};


        public MyGalleryAdapter(Context c) {
            context = c;
        }

        public int getCount() {
            return posterID.length;
        }

        public Object getItem(int arg0) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageview = new ImageView(context);
            imageview.setLayoutParams(new Gallery.LayoutParams(200, 300));
            imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageview.setPadding(5, 5, 5, 5);
            imageview.setImageResource(posterID[position]);

            final int pos = position;
            imageview.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    ImageView ivPoster = (ImageView) findViewById(R.id.ivPoster);
                    ivPoster.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    ivPoster.setImageResource(posterID[pos]);
                    foodtv.setText(foodTitle[pos]);
                    return false;
                }
            });

            return imageview;
        }
    }

}
