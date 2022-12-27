package com.example.androidclothstore.Activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.androidclothstore.R;


@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_splash_screen);


        Thread thread = new Thread()
        {
            SharedPreferences shared = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
            @Override
            public void run() {
                try {
                    sleep(3000);

                    String val = shared.getString("email", "");
                    if (val.length() == 0)
                    {

                        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();


                    }
                    else
                    {
                        Intent intent = new Intent(SplashScreenActivity.this, NavigationDrawerActivity.class);
                        startActivity(intent);
                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };

        thread.start();
    }
}
