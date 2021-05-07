package com.shadowsquad.unilife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddExam extends AppCompatActivity {

    private EditText examName,  place, type, note ;
    private Button addEx, date, time, clear;
    private DbHandler dbHandler;
    private Context context;


    //    DatePicker --------------
    DatePickerDialog.OnDateSetListener dateSetListener;

    //    TimePicker --------------
    TimePickerDialog.OnTimeSetListener timeSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //link to xml txt inputs
        setContentView(R.layout.activity_add_exam);

        examName = findViewById(R.id.editTextExamName);
        date = findViewById(R.id.editTextDate);
        time = findViewById(R.id.editTextTime);
        place = findViewById(R.id.editTextPlace);
        type = findViewById(R.id.editTextType);
        note = findViewById(R.id.editTextNote);

        addEx = findViewById(R.id.btnAddExamSave);

        context = this;
        dbHandler = new DbHandler(context);


        //Clear Button
        clear = findViewById(R.id.btnClearExamSave);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                      examName.getText().clear();
//                    date.getText().clear();
//                    time.getText().clear();
                      place.getText().clear();
                      type.getText().clear();
                      note.getText().clear();
            }
        });


        addEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Data validation
                if(TextUtils.isEmpty(examName.getText())) {
                    examName.setError("Name is Required!");
                    examName.requestFocus();
                } else if(TextUtils.isEmpty(date.getText())) {
                    date.setError("Date is Required!");
                    date.requestFocus();
                } else if(TextUtils.isEmpty(time.getText())) {
                    time.setError("Time is Required!");
                    time.requestFocus();
                } else if (TextUtils.isEmpty(place.getText())) {
                    place.setError("Place is Required!");
                    place.requestFocus();
                } else if (TextUtils.isEmpty(type.getText())) {
                    type.setError("Type is Required!");
                    type.requestFocus();
                } else {


                    //End of data validation prt

                    String userExamName = examName.getText().toString();
                    String userDate = date.getText().toString();
                    String userTime = time.getText().toString();
                    String userPlace = place.getText().toString();
                    String userType = type.getText().toString();
                    String userNotes = note.getText().toString();
                    long started = System.currentTimeMillis();


                    ExamModel examModel = new ExamModel(userExamName, userDate, userTime, userPlace, userType, userNotes, started, 0);
                    dbHandler.addExam(examModel);

                    //Toast Message
                    String saveBtn = "Success";
                    Toast.makeText(context, saveBtn, Toast.LENGTH_SHORT).show();

                    //Transaction add exam act to exam frag click save btn  and Reload
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.addExamActivity, new ExamFragment()).commit();
                }


            }
        });


        // Date

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
                String dateExam = day + "/" + month + "/" + year;
                date.setText(dateExam);
            }
        };

        // Time

        time.setOnClickListener(new View.OnClickListener() {
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
                String dateTime = hours + ":" + minute;
                time.setText(dateTime);
            }
        };
    }

}