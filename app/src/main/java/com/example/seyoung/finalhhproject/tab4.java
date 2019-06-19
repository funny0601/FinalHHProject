package com.example.seyoung.finalhhproject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class tab4 extends Activity {
    MyAdapter adapter;
    ArrayList<FoodInfo> arr = new ArrayList<>();

    ListView mainLv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab4_frame);

        mainLv = (ListView) findViewById(R.id.mainLv);
        adapter = new MyAdapter(this);
        mainLv.setAdapter(adapter);

        init();
    }

    private void init(){
        arr.add(new FoodInfo("간장게장최고","032-0101-0101","이곳은 간장게장",R.drawable.ic_child_care_black_24dp));
        arr.add(new FoodInfo("맘스터치최고","032-1323-1920","이곳은 맘스터치",R.drawable.ic_child_care_black_24dp));
        arr.add(new FoodInfo("망고쥬스최고","032-1093-1120","이곳은 망고쥬스집",R.drawable.ic_child_care_black_24dp));
        arr.add(new FoodInfo("붕자네최고","032-7865-5123","이곳은 붕자네",R.drawable.ic_child_care_black_24dp));
        arr.add(new FoodInfo("낙곱새최고","032-7952-1572","이곳은 낙곱새",R.drawable.ic_child_care_black_24dp));
        adapter.notifyDataSetChanged();
    }

    public class RowDataViewHolder {
        public TextView nameTvHolder;
        public TextView phoneTvHolder;
        public TextView explainTvHolder;
        public ImageView foodIvHoler;
    }

    class MyAdapter extends ArrayAdapter {
        LayoutInflater lnf;

        public MyAdapter(Activity context) {
            super(context, R.layout.tab4_item, arr);
            lnf = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
// TODO Auto-generated method stub
            return arr.size();
        }

        @Override
        public Object getItem(int position) {
// TODO Auto-generated method stub
            return arr.get(position);
        }

        @Override
        public long getItemId(int position) {
// TODO Auto-generated method stub
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            RowDataViewHolder viewHolder;
            if (convertView == null) {
                convertView = lnf.inflate(R.layout.tab4_item, parent, false);
                viewHolder = new RowDataViewHolder();
                viewHolder.nameTvHolder = (TextView) convertView.findViewById(R.id.nameTv);
                viewHolder.phoneTvHolder = (TextView) convertView.findViewById(R.id.phoneTv);
                viewHolder.explainTvHolder = (TextView) convertView.findViewById(R.id.explainTv);
                viewHolder.foodIvHoler = (ImageView) convertView.findViewById(R.id.foodIv);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (RowDataViewHolder) convertView.getTag();
            }

            viewHolder.nameTvHolder.setText(arr.get(position).getName());
            viewHolder.phoneTvHolder.setText(arr.get(position).getPhone());
            viewHolder.explainTvHolder.setText(arr.get(position).getExplain());
            viewHolder.foodIvHoler.setImageResource(arr.get(position).getResId());

            return convertView;
        }
    }
}
