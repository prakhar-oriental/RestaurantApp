package com.example.admin.aAdminData;

public class vieworderitemdata {
    String dish,dishquantity,documentid;
    String dishprice;


    public vieworderitemdata() {
    }

    public vieworderitemdata(String dish, String dishquantity, String documentid, String dishprice) {
        this.dish = dish;
        this.dishquantity = dishquantity;
        this.documentid = documentid;
        this.dishprice = dishprice;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public String getDishquantity() {
        return dishquantity;
    }

    public void setDishquantity(String dishquantity) {
        this.dishquantity = dishquantity;
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
}
