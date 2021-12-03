package com.example.artybeaconfoodapp2.Data;

public class Burgeroptiondata {
    String category,multilinedes,puniqueid,subheading;
    String pname;
    int pprice;
    String pimg;
    boolean isShrink = true;

    public Burgeroptiondata() {
    }

    public Burgeroptiondata(String category, String multilinedes, String puniqueid, String subheading, String pname, int pprice, String pimg, boolean isShrink) {
        this.category = category;
        this.multilinedes = multilinedes;
        this.puniqueid = puniqueid;
        this.subheading = subheading;
        this.pname = pname;
        this.pprice = pprice;
        this.pimg = pimg;
        this.isShrink = isShrink;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMultilinedes() {
        return multilinedes;
    }

    public void setMultilinedes(String multilinedes) {
        this.multilinedes = multilinedes;
    }

    public String getPuniqueid() {
        return puniqueid;
    }

    public void setPuniqueid(String puniqueid) {
        this.puniqueid = puniqueid;
    }

    public String getSubheading() {
        return subheading;
    }

    public void setSubheading(String subheading) {
        this.subheading = subheading;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getPprice() {
        return pprice;
    }

    public void setPprice(int pprice) {
        this.pprice = pprice;
    }

    public String getPimg() {
        return pimg;
    }

    public void setPimg(String pimg) {
        this.pimg = pimg;
    }

    public boolean isShrink() {
        return isShrink;
    }

    public void setShrink(boolean shrink) {
        isShrink = shrink;
    }
}
