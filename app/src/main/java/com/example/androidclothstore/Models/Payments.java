package com.example.androidclothstore.Models;

import com.orm.SugarRecord;

public class Payments extends SugarRecord{ //Table used to store information regarding payment methods

    String Name;

    public Payments(){
        //Empty Constructor

    }

    public Payments(String name) {
        Name = name;
    }//Parametric Constructor
    //Getters and Setters
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
