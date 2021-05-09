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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {



    //COUNTER
    private TextView countLecture ;
    private Context context;
    private DbHandler dbHandler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home,container,false);

        countLecture = (TextView) view.findViewById(R.id.counterLectureText);


        dbHandler = new DbHandler(getContext());

        // get raw counts from table
        int CountRaws = dbHandler.CountLectureTodo();
        countLecture.setText("you have "+CountRaws+" Lectures");


        return view;
    }
}