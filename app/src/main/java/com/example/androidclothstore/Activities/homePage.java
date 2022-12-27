package com.example.androidclothstore.Activities;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;

import com.example.androidclothstore.Models.Products;
import com.example.androidclothstore.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class homePage extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    GridView grid;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        grid = (GridView)findViewById(R.id.productsGrid);

        Gson gson = new Gson();
        Type listType = new TypeToken<List<Products>>(){}.getType();
        final ArrayList<Products> products = gson.fromJson(readFromFile(), listType);
    }

    private String readFromFile() {

        String filename = "products";
        StringBuffer sbJsonString = new StringBuffer();
        InputStream is = getResources().openRawResource(R.raw.products);

        int character;

        try {
            while((character = is.read()) !=-1) {
                sbJsonString.append((char) character);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sbJsonString.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}