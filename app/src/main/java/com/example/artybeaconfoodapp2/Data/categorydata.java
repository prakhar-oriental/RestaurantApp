package com.example.artybeaconfoodapp2.Data;

public class categorydata {
    int catimg;
    String cattext;

    public categorydata() {
    }

    public categorydata(int catimg, String cattext) {
        this.catimg = catimg;
        this.cattext = cattext;
    }

    public int getCatimg() {
        return catimg;
    }

    public void setCatimg(int catimg) {
        this.catimg = catimg;
    }

    public String getCattext() {
        return cattext;
    }

    public void setCattext(String cattext) {
        this.cattext = cattext;
    }
}
