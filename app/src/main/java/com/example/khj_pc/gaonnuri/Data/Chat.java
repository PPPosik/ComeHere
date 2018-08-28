package com.example.khj_pc.gaonnuri.Data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Chat implements Serializable{
    @SerializedName("room_ObjId")
    private String roomId;
    @SerializedName("post_ObjId")
    private String postId;
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

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
