package com.example.khj_pc.gaonnuri.Data;

public class CircleChart {

    private String title;
    private String content;
    private int percent;

    public CircleChart(String title, String content, int percent) {
        this.title = title;
        this.content = content;
        this.percent = percent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
}
