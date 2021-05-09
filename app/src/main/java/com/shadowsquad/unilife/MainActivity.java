package com.shadowsquad.unilife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

   private  ImageButton menubtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Bottom Nav bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);



        BottomNavigationView bottomNavigationView1 =findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();

       // final MainActivity RelativeLayout = findViewById(R.id.mainAcitiviy);









    }


    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment=null;
            switch (item.getItemId())
            {
                case R.id.home:
                selectedFragment= new HomeFragment();
                break;

                case R.id.exam:
                    selectedFragment= new ExamFragment();
                    break;

                case R.id.event:
                    selectedFragment= new EventFragment();
                    break;

                case R.id.lecture:
                    selectedFragment= new LectureFragment();
                    break;
            }

getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;
        }
    };


}