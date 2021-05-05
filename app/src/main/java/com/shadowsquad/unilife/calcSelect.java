package com.shadowsquad.unilife;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class calcSelect extends Fragment implements AdapterView.OnItemSelectedListener {

    Button gpaCal, cgpaCal;
    Spinner spnFaculty, spnSpecial, spnBatch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calc_select, container, false);

        gpaCal = (Button) view.findViewById(R.id.btngpa);
        cgpaCal = (Button) view.findViewById(R.id.btncgpa);
        spnSpecial = view.findViewById(R.id.specializeSpinner);
        spnBatch = view.findViewById(R.id.batchSpinner);

        // Spinners
        ArrayAdapter spacialAdapter = ArrayAdapter.createFromResource( getContext(), R.array.special, R.layout.custom_spinner_light); //change this to design selected
        spacialAdapter.setDropDownViewResource(R.layout.custom_spinner_light_drop); //change this layout for design dropdown
        spnSpecial.setAdapter(spacialAdapter);
        spnSpecial.setOnItemSelectedListener(this);
        spnSpecial.getSelectedItem();

        ArrayAdapter batchAdapter = ArrayAdapter.createFromResource( getContext(), R.array.batchArr, R.layout.custom_spinner_light);
        batchAdapter.setDropDownViewResource(R.layout.custom_spinner_light_drop);
        spnBatch.setAdapter(batchAdapter);
        spnBatch.setOnItemSelectedListener(this);
        spnBatch.getSelectedItem(); //This should assigned to a variable and return

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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}