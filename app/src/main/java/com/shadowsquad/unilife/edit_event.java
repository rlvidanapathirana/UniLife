package com.shadowsquad.unilife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
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

public class edit_event extends AppCompatActivity {

    private DbHandlerEvent dbHandlerEvent;
    private Context context;
    private Long updatedate;
    private EditText  eventName,presenter,venue,note;
    private  ImageButton btn1;

    private Button savedata;
    String eventId;

    //date piker
    DatePickerDialog.OnDateSetListener dateSetListener;
    private Button  statTime,endTime;
    private Button  date;

    //time piker
    TimePickerDialog.OnTimeSetListener timeSetListener1;
    TimePickerDialog.OnTimeSetListener timeSetListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);


        context =this;
        dbHandlerEvent =new DbHandlerEvent(context);

        eventName = findViewById(R.id.updateEname);
        presenter = findViewById(R.id.updatePresentername);
        venue = findViewById(R.id.updateVenue);
        statTime = findViewById(R.id.updateStarttime);
        endTime = findViewById(R.id.updateEndtime);
        date = findViewById(R.id.updateDate);
        note = findViewById(R.id.updateNote);
        savedata = findViewById(R.id.updatebtn);

        //back button
        btn1 = (ImageButton) findViewById(R.id.btnback);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_event.super.onBackPressed();
            }
        });


        final String id =getIntent().getStringExtra("id");
        EventModle eventModle =  dbHandlerEvent.getsingleTodo(Integer.parseInt(id)); //id eka string krgnnwa

//        Log.i("DBH", "EventName => ");
         eventName.setText(eventModle.getEventName());
         presenter.setText(eventModle.getPresenter());
         venue.setText(eventModle.getVenue());
         statTime.setText(eventModle.getStatTime());
         endTime.setText(eventModle.getEndTime());
         date.setText(eventModle.getDate());
         note.setText(eventModle.getNote());

         //update button
        savedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int eventId = eventModle.getId();
                String eventNameText = eventName.getText().toString();
                String presenterText = presenter.getText().toString();
                String venueText = venue.getText().toString();
                String statTimeText = statTime.getText().toString();
                String endTimeText = endTime.getText().toString();
                String dateText = date.getText().toString();
                String noteText = note.getText().toString();

                updatedate = System.currentTimeMillis();
                //danata tyena sytem eke welawa gnnwa

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



                EventModle eventModle1 = new EventModle(eventId, eventNameText, presenterText, venueText, statTimeText, endTimeText,dateText, noteText, updatedate, 0);
                int state = dbHandlerEvent.updateEventTodo(eventModle1);
//                int state = dbHandler.updateEventTodo(new EventModle());

                String from = (" successfully updated  ");
                Toast.makeText(context, from, Toast.LENGTH_LONG).show();

                // startActivity(new Intent(context,MainActivity.class));

                 //activity to fragment
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.editieventpage,new EventFragment()).commit();

                Intent openMainActivity = new Intent(edit_event.this, MainActivity.class);
                openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(openMainActivity, 0);

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
