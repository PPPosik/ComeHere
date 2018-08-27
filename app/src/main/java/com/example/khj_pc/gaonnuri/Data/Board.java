package com.example.khj_pc.gaonnuri.Data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Board implements Serializable {

    @SerializedName("_id")
    private String id;

    @SerializedName("room_id")
    private String roomId;
    private String title;

    @SerializedName("user_name")
    private String author;

    @SerializedName("user_auth_id")
    private String userAuthId;

    @SerializedName("context")
    private String content;

    @SerializedName("image_count")
    private int imageCount;

    @SerializedName("created_at")
    private String date;
    private int likes;
    private int type;

    @SerializedName("images")
    private ArrayList<String> imageURL;

    private ArrayList<Chat> chats;


    public Board(String roomId, String title, String author, String userAuthId, String content, int imageCount, String date, int likes, int type, ArrayList<String> imageURL) {
        this.roomId = roomId;
        this.title = title;
        this.author = author;
        this.userAuthId = userAuthId;
        this.content = content;
        this.imageCount = imageCount;
        this.date = date;
        this.likes = likes;
        this.type = type;
        this.imageURL = imageURL;
    }

    public Board(String userAuthId, String author, String title, String content, int imageCount, ArrayList<String> imageURL) {
        this.title = title;
        this.userAuthId = userAuthId;
        this.author = author;
        this.content = content;
        this.likes = likes;
        this.type = type;
        this.imageCount = imageCount;
        this.imageURL = imageURL;
        this.date = "June 2, 2017";
    }

    public Board(String title, String author, String content, int likes, int type, ArrayList<String> imageURL) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.likes = likes;
        this.type = type;
        this.imageURL = imageURL;
    }

    public Board(String id, String roomId, String title, String author, String userAuthId, String content, int imageCount, String date, int likes, int type, ArrayList<String> imageURL, ArrayList<Chat> chats) {
        this.id = id;
        this.roomId = roomId;
        this.title = title;
        this.author = author;
        this.userAuthId = userAuthId;
        this.content = content;
        this.imageCount = imageCount;
        this.date = date;
        this.likes = likes;
        this.type = type;
        this.imageURL = imageURL;
        this.chats = chats;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ArrayList<String> getImageURL() {
        return imageURL;
    }

    public void setImageURL(ArrayList<String> imageURL) {
        this.imageURL = imageURL;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getUserAuthId() {
        return userAuthId;
    }

    public void setUserAuthId(String userAuthId) {
        this.userAuthId = userAuthId;
    }

    public int getImageCount() {
        return imageCount;
    }

    public void setImageCount(int imageCount) {
        this.imageCount = imageCount;
    }

    public ArrayList<Chat> getChats() {
        return chats;
    }

    public void setChats(ArrayList<Chat> chats) {
        this.chats = chats;
    }
}
