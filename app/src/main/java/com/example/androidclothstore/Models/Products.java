package com.example.androidclothstore.Models;

import com.orm.SugarRecord;

import java.util.ArrayList;

public class Products extends  SugarRecord{

    private String Name;
    private String ShortDescription;
    private String LongDescription;
    private int Catagory;
    private int Price;
    private int Quantity;
    private Boolean Active;
    private String ScaledImage;
    private String FullImage;


    public Products() {
        //Empty Constructor
    }

    public Products(String name, String shortDescription, String longDescription, int catagory, int price, int quantity, Boolean active, String scaledImage, String fullImage) {
        Name = name;
        ShortDescription = shortDescription;
        LongDescription = longDescription;
        Catagory = catagory;
        Price = price;
        Quantity = quantity;
        Active = active;
        ScaledImage = scaledImage;
        FullImage = fullImage;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getShortDescription() {
        return ShortDescription;
    }

    public void setShortDescription(String shortDescription) {
        ShortDescription = shortDescription;
    }

    public String getLongDescription() {
        return LongDescription;
    }

    public void setLongDescription(String longDescription) {
        LongDescription = longDescription;
    }

    public int getCatagory() {
        return Catagory;
    }

    public void setCatagory(int catagory) {
        Catagory = catagory;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public Boolean getActive() {
        return Active;
    }

    public void setActive(Boolean active) {
        Active = active;
    }

    public String getScaledImage() {
        return ScaledImage;
    }

    public void setScaledImage(String scaledImage) {
        ScaledImage = scaledImage;
    }

    public String getFullImage() {
        return FullImage;
    }

    public void setFullImage(String fullImage) {
        FullImage = fullImage;
    }
}
