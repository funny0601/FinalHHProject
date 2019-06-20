package com.example.seyoung.finalhhproject;

import android.os.Parcel;
import android.os.Parcelable;


// 파싱된 데이터 클래스화

public class mountainTable {
    private String name;
    private String area;

    public mountainTable(String name, String area) {
        this.name = name;
        this.area = area;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public String getArea() {
        return area;
    }


}
