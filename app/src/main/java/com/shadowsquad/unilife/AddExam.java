package com.shadowsquad.unilife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddExam extends AppCompatActivity {

    private EditText examName, date, time, place, type, note ;
    private Button addEx;
    private DbHandler dbHandler;
    private Context context;

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


        addEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userExamName = examName.getText().toString();
                String userDate = date.getText().toString();
                String userTime = time.getText().toString();
                String userPlace = place.getText().toString();
                String userType = type.getText().toString();
                String userNotes = note.getText().toString();
                long started = System.currentTimeMillis();


                ExamModel examModel = new ExamModel(userExamName,userDate,userTime, userPlace, userType, userNotes, started,0 );
                dbHandler.addExam(examModel);

            }
        });

    }

}