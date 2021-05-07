package com.shadowsquad.unilife;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

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

        //
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {

                ExamModel examModel = examModels.get(position);

                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext(),
                        R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog_Background);



                builder.setTitle(examModel.getExamName());
                builder .setMessage("Exam Data");




                builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //FRAGMENT TO FARAGMENT
                        ExamFragment  examFragment = new ExamFragment() ;
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container,examFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();

                    }
                });

                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHandler.deleteExamTodo(examModel.getId());

                        //FRAGMENT TO FARAGMENT
                        ExamFragment  examFragment = new ExamFragment() ;
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment,examFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }

                });

                builder.setNeutralButton("View&Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //  Intent i = new Intent(getActivity(), edit_event.class);
                        Intent intent = new Intent(context,EditExam.class);
                        intent.putExtra("id",String.valueOf(examModel.getId()));
                        startActivity(intent);
                    }
                });

                builder.show();

            }

        });




        return View;
        }

    }

