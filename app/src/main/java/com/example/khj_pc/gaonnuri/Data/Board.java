package com.example.khj_pc.gaonnuri.Data;

import java.io.Serializable;
import java.util.ArrayList;

public class Board implements Serializable {
    private String title;
    private String author;
    private String content;
    private String date;
    private int likes;
    private int type;
    private ArrayList<String> imageURL;

    public Board(String title, String author, String content, String date, int likes, int type, ArrayList<String> imageURL) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.date = date;
        this.likes = likes;
        this.type = type;
        this.imageURL = imageURL;
    }

    public Board(String title, String author, String content, int likes, int type) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.likes = likes;
        this.type = type;
        imageURL = new ArrayList<>();
        imageURL.add("https://images.pexels.com/photos/104827/cat-pet-animal-domestic-104827.jpeg");
        imageURL.add("https://images.pexels.com/photos/730896/pexels-photo-730896.jpeg");
        imageURL.add("https://images.pexels.com/photos/774731/pexels-photo-774731.jpeg");
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
}
