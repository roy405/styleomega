package com.example.androidclothstore.Models;

import com.orm.SugarRecord;

public class Users extends SugarRecord{

    public String firstname;
    public String lastname;
    public String email;
    public String password;
    public String dob;
    public String shippingAddress;
    public String billingAddress;

    public Users() {
        //Empty Constructor
    }

    public Users (String firstname, String lastname, String email, String password, String dob, String shippingAddress, String billingAddress )
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

}
