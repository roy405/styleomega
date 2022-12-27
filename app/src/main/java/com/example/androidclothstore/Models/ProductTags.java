package com.example.androidclothstore.Models;

import com.orm.SugarRecord;

public class ProductTags extends SugarRecord{

    private Products product;
    private String TagName;

    public ProductTags(){
        //Empty Constructor
    }

    public ProductTags(Products product, String tagName) {
        this.product = product;
        TagName = tagName;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public String getTagName() {
        return TagName;
    }

    public void setTagName(String tagName) {
        TagName = tagName;
    }
}

