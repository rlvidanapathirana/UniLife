package com.shadowsquad.unilife;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {



//    //COUNTER
//    private TextView countEvent ;
//    private Context context;
//    private DbHandler dbHandler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       return inflater.inflate(R.layout.fragment_home,container,false);

//        countEvent = (TextView) view.findViewById(R.id.counterEventtext);
//
//
//        dbHandler = new DbHandler(getContext());
//
//        // get raw counts from table
//        int CountRaws = dbHandler.CountEventTodo();
//        countEvent.setText("you have "+CountRaws+" Event");
//


    }
}