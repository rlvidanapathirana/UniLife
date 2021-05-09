package com.shadowsquad.unilife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class lounchhome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lounchhome);

        //Run SplashScreen at begining
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent i = new Intent(SplashScreen.this,CreateUser.class);
                Intent i = new Intent(lounchhome.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 3000);
    }


    }
