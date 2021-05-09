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

public class EditLecture extends AppCompatActivity {

    private EditText name,place, conductedBy, specialNote;
    private Button updatebtn,clear, date, time;
    private DbHandlerLecture dbHandlerLecture;
    private Context context;
    private Long updatedate;
    private ImageButton backArrBtn;

    //date piker
    DatePickerDialog.OnDateSetListener dateSetListener;
    

    //time piker
    TimePickerDialog.OnTimeSetListener timeSetListener1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_lecture);

        context= this;
        dbHandlerLecture = new DbHandlerLecture(context);

        name = findViewById(R.id.updateLectureName);
        date = findViewById(R.id.updateLectureDate);
        time = findViewById(R.id.updateLectureTime);
        place = findViewById(R.id.updateLecturePlace);
        conductedBy = findViewById(R.id.updateLectureConductedBy);
        specialNote = findViewById(R.id.updateLectureSpecialNote);
        updatebtn = findViewById(R.id.updateLecturesaveButton);
        backArrBtn =findViewById(R.id.backArrowImgBtn);

        //back button
        backArrBtn = (ImageButton) findViewById(R.id.backArrowImgBtn);
        backArrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditLecture.super.onBackPressed();
            }
        });



        final String id = getIntent().getStringExtra("id");
        Lecture lecture = dbHandlerLecture.getsingleLectureTodo(Integer.parseInt(id)); //id eka string krgnnwa
//        Log.i("DBH", "EventName => ");
        name.setText(lecture.getName());
        date.setText(lecture.getDate());
        time.setText(lecture.getTime());
        place.setText(lecture.getPlace());
        conductedBy.setText(lecture.getConductedBy());
        specialNote.setText(lecture.getSpecialNote());

        //update button
        updatebtn.setOnClickListener(new View.OnClickListener() {
            //private EventFragment  eventFragment;
            @Override
            public void onClick(View v) {

                int lecturetId = lecture.getId();
                String LecturetNameText = name.getText().toString();
                String LectureDateText = date.getText().toString();
                String LectureTimetext = time.getText().toString();
                String LecturePlaceText = place.getText().toString();
                String LectureConductedByText = conductedBy.getText().toString();
                String LecturesepecialNotText = specialNote.getText().toString();


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


                updatedate = System.currentTimeMillis();
                //danata tyena sytem eke welawa gnnwa
                Lecture lecture1 = new Lecture(lecturetId, LecturetNameText, LectureDateText, LectureTimetext, LecturePlaceText, LectureConductedByText,LecturesepecialNotText, updatedate, 0);
                int state = dbHandlerLecture.updateLectureTodo(lecture1);
//                int state = dbHandler.updateEventTodo(new EventModle());
                String from = (" successfully updated  ");
                Toast.makeText(context, from, Toast.LENGTH_LONG).show();
                // startActivity(new Intent(context,MainActivity.class));
                //activity to fragment
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.editLecturePage,new LectureFragment()).commit();
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