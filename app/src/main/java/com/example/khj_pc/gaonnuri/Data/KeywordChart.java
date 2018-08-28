package com.example.khj_pc.gaonnuri.Data;

public class KeywordChart {
    private String keyword;
    private int num;

    public KeywordChart(String keyword, int num) {
        this.keyword = keyword;
        this.num = num;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
