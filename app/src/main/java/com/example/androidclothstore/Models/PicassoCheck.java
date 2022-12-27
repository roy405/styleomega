package com.example.androidclothstore.Models;

import com.orm.SugarRecord;

public class PicassoCheck extends SugarRecord{

    public boolean check;

    public PicassoCheck() {

    }

    public PicassoCheck(boolean check) {
        this.check = check;
    }

    public boolean getCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
