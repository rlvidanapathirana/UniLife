package com.shadowsquad.unilife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.ImageButton;

public class createEvent extends AppCompatActivity {
    ImageButton btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        btn1 = (ImageButton) findViewById(R.id.backarrow);
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,new EventFragment()).commit();
            }
        });

        //back button
//        ImageButton btn = (ImageButton) findViewById(R.id.backarrow);
//
//        btn.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//                startActivity(new Intent( createEvent.this,EventFragment.class));
//            }
//        });





    }
}