package com.example.seyoung.finalhhproject;

public class selectedMount {
    private String mnt;
    private boolean selected=false;
    // 랜덤 선택으로 선택된 산만 true 값을 줘서 다른 탭들에서 boolean값으로 판별할 수 있게 함

    public selectedMount(String mnt){
        this.mnt = mnt;
    }

    public String getMnt() {
        return mnt;
    }

    public void setMnt(String mnt) {
        this.mnt = mnt;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }
}
