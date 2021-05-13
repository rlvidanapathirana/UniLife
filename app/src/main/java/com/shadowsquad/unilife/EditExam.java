package com.shadowsquad.unilife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class EditExam extends AppCompatActivity {
    private Context context;
    private Button updateEx, date, time;
    private EditText examName,  place, type, note ;
    private DbHandler dbHandler;
    private Long updatedate;
    private ImageButton backUpdateExambtn;

    //    DatePicker --------------
    DatePickerDialog.OnDateSetListener dateSetListener;

    //    TimePicker --------------
    TimePickerDialog.OnTimeSetListener timeSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_exam);

        examName = findViewById(R.id.updateExamNameTxt);
        date = findViewById(R.id.updateDateTxt);
        time = findViewById(R.id.updateTimeTxt);
        place = findViewById(R.id.updatePlaceTxt);
        type = findViewById(R.id.updateTypeTxt);
        note = findViewById(R.id.updateNoteTxt);

        updateEx = findViewById(R.id.btnUpdate);

        context = this;
        dbHandler = new DbHandler(context);


        //Back img btn
        backUpdateExambtn = (ImageButton) findViewById(R.id.backArrowEditExam);
        backUpdateExambtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditExam.super.onBackPressed();
            }
        });
//

        //EDIT EXAM DATA SET TO VALUES
        final String id =getIntent().getStringExtra("id");
        ExamModel examModel =  dbHandler.getsingleExamTodo(Integer.parseInt(id)); //change ID to string
//        Log.i("DBH", "EventName => ");
        examName.setText(examModel.getExamName());
        date.setText(examModel.getDate());
        time.setText(examModel.getTime());
        place.setText(examModel.getDate());
        type.setText(examModel.getType());
        note.setText(examModel.getNote());


        //update button
        updateEx.setOnClickListener(new View.OnClickListener() {
            //private EventFragment  eventFragment;
            @Override
            public void onClick(View v) {
                String examNameText = examName.getText().toString();
                String dateText = date.getText().toString();
                String timeText = time.getText().toString();
                String placeText = place.getText().toString();
                String typeText = type.getText().toString();
                String noteText = note.getText().toString();


                updatedate = System.currentTimeMillis();
                ; //get current system time


                ExamModel examModel = new ExamModel(Integer.parseInt(id), examNameText, dateText, timeText,
                        placeText, typeText, noteText, updatedate, 0);

                int state = dbHandler.updateExamTodo(examModel);

            //UPDATE SUCCESS Toast message and auto back to exam frag
                String from = (" successfully updated  ");
                Toast.makeText(context, from, Toast.LENGTH_LONG).show();
                // startActivity(new Intent(context,MainActivity.class));
             //activity to fragment
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.editExamPage,new ExamFragment()).commit();

                Intent openMainActivity = new Intent(EditExam.this, MainActivity.class);
                openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(openMainActivity, 0);

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
