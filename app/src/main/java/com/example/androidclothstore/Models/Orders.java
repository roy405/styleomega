package com.example.androidclothstore.Models;

import com.orm.SugarRecord;

public class Orders extends SugarRecord { //Orders table used to store information about the items in the cart and corresponding users and quantity of items
    private Products product;
    private Users user;
    private String date;
    private String quantityAmount;

    public Orders() {
        //Empty Constructor
    }

    public Orders(Products product, Users user, String date, String quantityAmount) {//Parametric Constructor
        this.product = product;
        this.user = user;
        this.date = date;
        this.quantityAmount = quantityAmount;
    }
    //Getters and Setters
    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getQuantityAmount() {
        return quantityAmount;
    }

    public void setQuantityAmount(String quantityAmount) {
        this.quantityAmount = quantityAmount;
    }
}
