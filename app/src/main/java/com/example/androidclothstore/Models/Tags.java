package com.example.androidclothstore.Models;

import com.orm.SugarRecord;

public class Tags extends SugarRecord{
    private String TagName;

    public Tags(){
        //Empty Constructor
    }

    public Tags(String tagName) {
        TagName = tagName;
    }

    public String getTagName() {
        return TagName;
    }

    public void setTagName(String tagName) {
        TagName = tagName;
    }
}
