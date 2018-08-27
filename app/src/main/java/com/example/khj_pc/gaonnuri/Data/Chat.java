package com.example.khj_pc.gaonnuri.Data;

import com.google.gson.annotations.SerializedName;

public class Chat {
    @SerializedName("author")
    private String userName;
    @SerializedName("message")
    private String content;
    @SerializedName("created_at")
    private String time;

    public Chat(String userName, String time, String context) {
        this.userName = userName;
        this.content = context;
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
