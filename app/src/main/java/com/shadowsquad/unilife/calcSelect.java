package com.shadowsquad.unilife;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link calcSelect#newInstance} factory method to
 * create an instance of this fragment.
 */
public class calcSelect extends Fragment {

    Button gpaCal, cgpaCal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calc_select, container, false);

        gpaCal = (Button) view.findViewById(R.id.btngpa);
        cgpaCal = (Button) view.findViewById(R.id.btncgpa);
        gpaCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                gpaCalc gpacalc = new gpaCalc();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.linearLayout1,gpacalc);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        cgpaCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                cgpaCalc cgpacalc = new cgpaCalc();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.linearLayout1,cgpacalc);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}