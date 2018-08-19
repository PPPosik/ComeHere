package com.example.khj_pc.gaonnuri.Data;

public class Board {
    private String title;
    private String author;
    private String content;
    private int likes;
    private int type;

    public Board(String title, String author, String content, int likes, int type) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.likes = likes;
        this.type = type;
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
}
