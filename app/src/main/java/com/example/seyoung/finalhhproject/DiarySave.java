package com.example.seyoung.finalhhproject;

public class DiarySave {
    private String title; // 등산 후기 등록 제목
    private String content; // 내용
    private String date; // 등록 날짜

    public DiarySave(String title, String date,  String content){
        this.title= title;
        this.date = date;
        this.content=content;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
