package com.shadowsquad.unilife;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class ExamFragment extends Fragment {

    Context context;
    private List<ExamModel>  examModels; //DB Handdeler Get Data
    private DbHandler dbHandler;


    //Button addBtnExam;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View View = inflater.inflate(R.layout.fragment_exam,container,false);

        //context = this;
       //initialize examModels variable

        context = container.getContext();
        dbHandler = new DbHandler(context);

        //listView =
        examModels = new ArrayList<>();

        examModels = dbHandler.getAllInsertedExamModel();
        ListView listView = (ListView) View.findViewById(R.id.ListViewExam);
        // 28 - 28:07
        ExamAdapter examAdapter = new ExamAdapter(context,R.layout.activity_todoexam,examModels);
        listView.setAdapter(examAdapter);

       //Exam Page btn link ADD EXAM Fragment page [Fragment to Activity btn]
        ImageButton BtnOpenExam = (ImageButton) View.findViewById(R.id.BtnOpenExam);
        BtnOpenExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent in = new Intent(getActivity(),AddExam.class);
                startActivity(in);
            }
        });


        return View;
        }

    }

