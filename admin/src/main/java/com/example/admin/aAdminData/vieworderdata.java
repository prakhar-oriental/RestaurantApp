package com.example.admin.aAdminData;

import java.util.ArrayList;

public class vieworderdata {
    String address1,address2,city,state,pincode,documentid;
    Double total;
    ArrayList<Showincartdata> orderlist;


    public vieworderdata() {
    }

    public vieworderdata(String address1, String address2, String city, String state, String pincode, String documentid, Double total, ArrayList<Showincartdata> orderlist) {
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.documentid = documentid;
        this.total = total;
        this.orderlist = orderlist;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getDocumentid() {
        return documentid;
    }

    public void setDocumentid(String documentid) {
        this.documentid = documentid;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public ArrayList<Showincartdata> getOrderlist() {
        return orderlist;
    }

    public void setOrderlist(ArrayList<Showincartdata> orderlist) {
        this.orderlist = orderlist;
    }
}
