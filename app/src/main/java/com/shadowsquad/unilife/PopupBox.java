package com.shadowsquad.unilife;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class PopupBox extends AppCompatDialogFragment {

    EditText edtCgpa, edtTarget;
    private DbHandlerGpa dbHandlerGpa;
    private Context context;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Context context;
        dbHandlerGpa = new DbHandlerGpa(getActivity().getBaseContext());

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext(),
                R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog_Background);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_popup,null);

        edtCgpa = view.findViewById(R.id.edit_cgpa);
        edtTarget = view.findViewById(R.id.edit_target);

        gpaModel gpamodel = dbHandlerGpa.getSingle(1);

        edtCgpa.setText(gpamodel.getCgpa());
        edtTarget.setText(gpamodel.getTarget());

        builder.setView(view)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String cgpa = edtCgpa.getText().toString();
                        String target = edtTarget.getText().toString();

                        //Validation - CGPA and Target GPA cannot be null
                        if(TextUtils.isEmpty(edtTarget.getText())) {
                            edtTarget.setError("");
                            edtTarget.requestFocus();
                            return;
                        }

                        if(TextUtils.isEmpty(edtCgpa.getText())) {
                            edtCgpa.setError("");
                            edtCgpa.requestFocus();
                            return;
                        }

                        double cgpaNum = Double.parseDouble(cgpa);
                        double targetNum = Double.parseDouble(target);

                        //Validation - GPA between 0.0 and 4.0
                        if(cgpaNum >= 0 && cgpaNum <= 4 && targetNum >= 0 && targetNum <= 4) {
                            gpaModel gpam = new gpaModel(cgpa, target);
                            int ret = dbHandlerGpa.updateCgpa(gpam);
                        }

                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container,new HomeFragment());
//                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                });



        return builder.create();
    }

}
