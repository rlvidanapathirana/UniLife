package com.shadowsquad.unilife;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class HomeFragment extends Fragment {

    Button btnCal;
    ImageButton addBtn;
    TextView txtCgpa, txtTarget;

    DbHandler dbHandler;
//
    gpaModel gpamodel = null;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        txtCgpa = (TextView) view.findViewById(R.id.textViewCgpa);
        txtTarget = (TextView) view.findViewById(R.id.textViewTarget);
        btnCal = (Button) view.findViewById(R.id.btnCalc);
        addBtn = (ImageButton) view.findViewById(R.id.addImgBtn);


        dbHandler = new DbHandler(getActivity().getApplicationContext());
        boolean insert = dbHandler.insertgpa(1,"0","0");

        gpamodel = dbHandler.getSingleTodo(1);

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

        return view;
    }

    public void openPopup() {
        PopupBox popup = new PopupBox();
        popup.show(getActivity().getSupportFragmentManager(), "example popup");
    }

}
