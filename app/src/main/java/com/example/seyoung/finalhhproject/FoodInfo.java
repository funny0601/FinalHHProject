package com.example.seyoung.finalhhproject;

public class FoodInfo {
    private String name;
    private String phone;
    private String explain;
    private int resId;


    public FoodInfo(String name, String phone, String explain, int resId) {
        this.name = name;
        this.phone = phone;
        this.explain=explain;
        this.resId = resId;
    }

    public int getResId() {
        return resId;
    }

    public String getExplain() {
        return explain;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}

