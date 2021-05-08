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

public class AddLecture extends AppCompatActivity {

    private EditText name, place, conductedBy, specialNote;
    private Button add,clear,date,time;
    private DbHandler dbHandler;
    private Context context;
    private ImageButton backbtn;

    //date piker
    DatePickerDialog.OnDateSetListener dateSetListener;


    //time piker
    TimePickerDialog.OnTimeSetListener timeSetListener1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lecture);

        name = findViewById(R.id.editTextLectureName);
        date = findViewById(R.id.editTextLectureDate);
        time = findViewById(R.id.editTextLectureTime);
        place = findViewById(R.id.editTextLecturePlace);
        conductedBy = findViewById(R.id.editTextLectureConductedBy);
        specialNote = findViewById(R.id.editTextLectureNote);
        add = findViewById(R.id.editBtnOpenLecture);
        //clear button
        clear = findViewById(R.id.clear_textBtnOpenLecture);
        //back arrow button
        backbtn = findViewById(R.id.backArrow);

        //back button
        backbtn = (ImageButton) findViewById(R.id.backArrow);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddLecture.super.onBackPressed();
            }
        });


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.getText().clear();
//                date.getText().clear();
//                time.getText().clear();
                conductedBy.getText().clear();
                specialNote.getText().clear();
                place.getText().clear();
            }
        });

        context = this;

        dbHandler = new DbHandler(context);
//save Button
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = name.getText().toString();
                String userDate = date.getText().toString();
                String userTime = time.getText().toString();
                String userPlace = place.getText().toString();
                String userConductedBy = conductedBy.getText().toString();
                String userSpecialNote = specialNote.getText().toString();
                long started = System.currentTimeMillis();

                //validation part

                if(TextUtils.isEmpty(name.getText())) {
                    name.setError("Name is Required!");
                    name.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(time.getText())) {
                    time.setError("Time is Required!");
                    time.requestFocus();
                    return;
                }

                if(TextUtils.isEmpty(date.getText())) {
                    date.setError("Date is Required!");
                    date.requestFocus();
                    return;
                }

                if(TextUtils.isEmpty(place.getText())) {
                    place.setError("Place is Required!");
                    place.requestFocus();
                    return;
                }




                Lecture lecture = new Lecture(userName,userDate,userTime,userPlace,userConductedBy,userSpecialNote,started,0);
                dbHandler.addLecture(lecture);


                String from = ("Details saved successfully");
                Toast.makeText(context, from, Toast.LENGTH_SHORT).show();

                //After click, Navigate to the main fragment
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.addLectureActivity,new LectureFragment()).commit();


//                //showing toast msg
//                if(TextUtils.isEmpty(name.getText().toString())) {
//                    Toast.makeText(AddLecture.this, "No empty fields are allowed", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    Toast.makeText(AddLecture.this,name.getText().toString(),Toast.LENGTH_SHORT).show();
//                }




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
        time.setOnClickListener(new View.OnClickListener() {
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

                String time1 = hours + ":" + minute;
                time.setText(time1);
            }
        };


    }
}