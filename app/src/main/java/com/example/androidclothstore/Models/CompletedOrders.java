package com.example.androidclothstore.Models;

import com.orm.SugarRecord;

public class CompletedOrders extends SugarRecord{ //The Completed Order Model Class used to store orders that are completed
    private Orders orders;
    private String totalPrice;
    private String paymentMethod;

    public CompletedOrders(){
        //Empty Constructor
    }

    public CompletedOrders(Orders orders, String totalPrice, String paymentMethod) { //Parametric Constructor
        this.orders = orders;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
    }
    //Getters and Setters
    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
