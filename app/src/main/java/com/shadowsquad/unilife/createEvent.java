package com.shadowsquad.unilife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ImageButton;

public class createEvent extends AppCompatActivity {
    private EditText eventName,presenter,venue,statTime,endTime,date,note;
    private Button sbtn;
    private DbHandler dbHandler;
    private Context context;

    ImageButton btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);


        eventName = findViewById(R.id.editTextTextPersonName);
        presenter = findViewById(R.id.presenternametxt );
        venue = findViewById(R.id.venutxt );
        statTime = findViewById(R.id.editTextTime2 );
        endTime = findViewById(R.id.editTextTime );
        date = findViewById(R.id.datetxt );
        note = findViewById(R.id.noteTextMultiLine );
        sbtn = findViewById(R.id.savebtn );

        context =this;
        dbHandler =new DbHandler(context);

        //back button

        btn1 = (ImageButton) findViewById(R.id.backarrow);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new EventFragment()).commit();
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
        sbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEentname = eventName.getText().toString();
                String userPresenter =presenter.getText().toString();
                String userVenue = venue.getText().toString();
                String userStarttimer =statTime.getText().toString();
                String userEndtime = endTime.getText().toString();
                String userDate =date.getText().toString();
                String userNote = note.getText().toString();
                long started = System.currentTimeMillis();

                EventModle eventModle = new EventModle(userEentname,userPresenter,userVenue,userStarttimer,userEndtime,userDate,userNote,started,0);
                dbHandler.addCreateEvent(eventModle);


            }
        });





    }



}

