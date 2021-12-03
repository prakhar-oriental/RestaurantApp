package com.example.artybeaconfoodapp2.Data;

import java.io.Serializable;

public class Showincartdata implements Serializable {
    String dish,documentid;
    String dishprice,dishquantity;

    public Showincartdata() {
    }

    public Showincartdata(String dish, String documentid, String dishprice, String dishquantity) {
        this.dish = dish;
        this.documentid = documentid;
        this.dishprice = dishprice;
        this.dishquantity = dishquantity;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public String getDocumentid() {
        return documentid;
    }

    public void setDocumentid(String documentid) {
        this.documentid = documentid;
    }

    public String getDishprice() {
        return dishprice;
    }

    public void setDishprice(String dishprice) {
        this.dishprice = dishprice;
    }

    public String getDishquantity() {
        return dishquantity;
    }

    public void setDishquantity(String dishquantity) {
        this.dishquantity = dishquantity;
    }
}
