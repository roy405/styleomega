package com.example.androidclothstore.Models;

import com.orm.SugarRecord;

import java.util.Date;

public class Reviews extends  SugarRecord{

    private String Review;
    private String UserName;
    private String ProductName;
    private String reviewDate;

    public Reviews() {
        //Empty Constructor
    }

    public Reviews(String review, String userName, String productName, String reviewDate) {
        Review = review;
        UserName = userName;
        ProductName = productName;
        this.reviewDate = reviewDate;
    }

    public String getReview() {
        return Review;
    }

    public void setReview(String review) {
        Review = review;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }
}
