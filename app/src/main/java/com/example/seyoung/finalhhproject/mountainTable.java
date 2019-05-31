package com.example.seyoung.finalhhproject;

import android.os.Parcel;
import android.os.Parcelable;


// 파싱된 데이터 클래스화

public class mountainTable implements Parcelable {
    private String name;
    private int height;
    private String area;
    private String reason;
    private String transportation;
    private String tourInfo;

    public mountainTable(){}

    public mountainTable(String name, int height, String area, String reason, String transportation, String tourInfo){
        this.name= name;
        this.height=height;
        this.area=area;
        this.reason=reason;
        this.transportation=transportation;
        this.tourInfo=tourInfo;
    }

    protected mountainTable(Parcel in){
        name = in.readString();
        height = in.readInt();
        area = in.readString();
        reason = in.readString();
        transportation = in.readString();
        tourInfo = in.readString();
    }

    @Override
    public int describeContents(){
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(height);
        dest.writeString(area);
        dest.writeString(reason);
        dest.writeString(transportation);
        dest.writeString(tourInfo);
    }

    public void readFromParcel(Parcel in){
        name = in.readString();
        height = in.readInt();
        area = in.readString();
        reason = in.readString();
        transportation = in.readString();
        tourInfo = in.readString();
    }

    @SuppressWarnings("rawtypes")
    public static final Creator<mountainTable> CREATOR = new Creator<mountainTable>() {

        @Override
        public mountainTable createFromParcel(Parcel in) {
            return new mountainTable(in);
        }

        @Override
        public mountainTable[] newArray(int size) {
            // TODO Auto-generated method stub
            return new mountainTable[size];
        }

    };

    public void setName(String name) {
        this.name = name;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }

    public void setTourInfo(String tourInfo) {
        this.tourInfo = tourInfo;
    }

    public String getName(){
        return name;
    }

    public int getHeight() {
        return height;
    }

    public String getArea() {
        return area;
    }

    public String getReason() {
        return reason;
    }

    public String getTransportation() {
        return transportation;
    }

    public String getTourInfo() {
        return tourInfo;
    }

    @Override
    public String toString() {
        return area;
    }
}
