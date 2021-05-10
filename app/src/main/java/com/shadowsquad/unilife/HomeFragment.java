//package com.shadowsquad.unilife;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//public class HomeFragment extends Fragment {
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_home,container,false);
//    }
//}

package com.shadowsquad.unilife;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import android.widget.ImageButton;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.text.DecimalFormat;

public class HomeFragment extends Fragment {


    ConstraintLayout btnCal;
    ImageButton addBtn;
    TextView txtCgpa, txtTarget, txtNeeded;

    DbHandlerGpa dbHandlerGpa;
//
    gpaModel gpamodel = null;




    //COUNTERS

    private TextView countExam,countEvent, countLecture;
    private Context context;
    private DbHandler dbHandler;
    private DbHandlerEvent dbHandlerEvent;
    private DbHandlerLecture dbHandlerLecture;

//
//
//
//
//
//



    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home,container,false);

        txtCgpa = (TextView) view.findViewById(R.id.textViewCgpa);
        txtTarget = (TextView) view.findViewById(R.id.textViewTarget);
        txtNeeded = (TextView) view.findViewById(R.id.textLine);
        btnCal = (ConstraintLayout) view.findViewById(R.id.btnCalc);
        addBtn = (ImageButton) view.findViewById(R.id.addImgBtn);


        dbHandlerGpa = new DbHandlerGpa(getActivity().getApplicationContext());
        boolean insert = dbHandlerGpa.insertgpa(1,"0","0");

        gpamodel = dbHandlerGpa.getSingle(1);

        txtCgpa.setText("CGPA = " + gpamodel.getCgpa());
        txtTarget.setText("Target CGPA = " + gpamodel.getTarget());

        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                calcSelect calcselect = new calcSelect() ;
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,calcselect);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPopup();
            }
        });

        needed(Double.parseDouble(gpamodel.getTarget()) - Double.parseDouble(gpamodel.getCgpa()));

//COUNTER
        dbHandler = new DbHandler(getContext());
        dbHandlerEvent = new DbHandlerEvent(getContext());
        dbHandlerLecture = new DbHandlerLecture(getContext());



        //COUNTER TEXT
        countExam = (TextView) view.findViewById(R.id.ExamCountTextView);
        countEvent = (TextView) view.findViewById(R.id.EventCountTextView);
        countLecture = (TextView) view.findViewById(R.id.LectureCountTextView);

        //METHORD
        //get raw counts from table
        int CountRawsExams = dbHandler.CountExamTodo();
        countExam.setText("You have added "+CountRawsExams+" Exams");

        int CountRawsEvents = dbHandlerEvent.CountEventTodo();
        countEvent.setText("You have added "+CountRawsEvents+" Events");

        int CountRawsLectures = dbHandlerLecture.CountLectureTodo();
        countLecture.setText("You have added "+CountRawsLectures+" Lectures");

        return view;
    }

    public void openPopup() {
        PopupBox popup = new PopupBox();
        popup.show(getActivity().getSupportFragmentManager(), "example popup");
    }

    public void needed(double value) {
        if (value > 0) {
            DecimalFormat df = new DecimalFormat("0.00");
            txtNeeded.setText("You need to\nincrease your\nCGPA by " + df.format(value));
        }
        else {
            txtNeeded.setText("You have\nachieved\nyour target\nCGPA");
        }


    }




}
