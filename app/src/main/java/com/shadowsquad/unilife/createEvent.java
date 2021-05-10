package com.shadowsquad.unilife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class createEvent extends AppCompatActivity {

    private EditText eventName,presenter,venue,note;
    private Button sbtn,endTime,statTime,clear;
    private DbHandlerEvent dbHandlerEvent;
    private Context context;
    private ImageButton btn1;

    //date piker
    DatePickerDialog.OnDateSetListener dateSetListener;
    private Button date;

    //time piker
    TimePickerDialog.OnTimeSetListener timeSetListener1;
    TimePickerDialog.OnTimeSetListener timeSetListener;



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
        dbHandlerEvent =new DbHandlerEvent(context);

        //back button

        btn1 = (ImageButton) findViewById(R.id.backarrow);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createEvent.super.onBackPressed();
            }
        });

        //clear button
        clear = findViewById(R.id.eventclear);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventName.getText().clear();
                presenter.getText().clear();
                venue.getText().clear();
                note.getText().clear();


            }
        });

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



                //Data validation
                if(TextUtils.isEmpty(eventName.getText())) {
                    eventName.setError("Name is Required!");
                    eventName.requestFocus();
                    return;
                }
                 if(TextUtils.isEmpty(statTime.getText())) {
                    statTime.setError("Start time is Required!");
                    statTime.requestFocus();
                    return;
                }
                  if(TextUtils.isEmpty(date.getText())) {
                    date.setError("Date  is Required!");
                    date.requestFocus();
                    return;
                }


                EventModle eventModle = new EventModle(userEentname,userPresenter,userVenue,userStarttimer,userEndtime,userDate,userNote,started,0);
                dbHandlerEvent.addCreateEvent(eventModle);

                String from = ("successfully saved details");
                Toast.makeText(context, from, Toast.LENGTH_SHORT).show();

                //after click the save button gose to fragment

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.createpage,new EventFragment()).commit();

              //  fragmentTransaction.addToBackStack(null);

//                NotificationManager notificationManager =(NotificationManager)
//                getSystemService(NOTIFICATION_SERVICE);
//                notificationManager.notify(0,builder.build());


            }
        });

        //date picker


                date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar cal = Calendar.getInstance();
                        int year = cal.get(Calendar.YEAR);
                        int month = cal.get(Calendar.MONTH);
                        int day = cal.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog dialog = new DatePickerDialog(context, android.R.style.Theme_Holo_Light_Dialog, dateSetListener, year, month, day);
                        dialog.show();
                    }
                });

                dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                       // Log.d(TAG, "onDateSet: dd/mm/yyyy" + day + "/" + month + "/" + year);

                        String dateview = day + "/" + month + "/" + year;
                        date.setText(dateview);
                    }
                };

        //time piker for star time

        statTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int hours = cal.get(Calendar.HOUR_OF_DAY);
                int minutes = cal.get(Calendar.MINUTE);

                TimePickerDialog dialog = new TimePickerDialog(context, android.R.style.Theme_Holo_Light_Dialog, timeSetListener1, hours, minutes, true);
                dialog.show();
            }


        });

        timeSetListener1 = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hours, int minute) {

                String time = hours + ":" + minute;
                statTime.setText(time);
            }
        };



        //time piker for end time

        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int hours = cal.get(Calendar.HOUR_OF_DAY);
                int minutes = cal.get(Calendar.MINUTE);

                TimePickerDialog dialog = new TimePickerDialog(context, android.R.style.Theme_Holo_Light_Dialog, timeSetListener, hours, minutes, true);
                dialog.show();
            }
        });

        timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hours, int minute) {

                String timeend = hours + ":" + minute;
                endTime.setText(timeend);
            }
        };








    }



}

