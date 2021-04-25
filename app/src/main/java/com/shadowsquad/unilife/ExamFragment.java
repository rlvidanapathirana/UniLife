package com.shadowsquad.unilife;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ExamFragment extends Fragment {

    //Button addBtnExam;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View View = inflater.inflate(R.layout.fragment_exam,container,false);

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

