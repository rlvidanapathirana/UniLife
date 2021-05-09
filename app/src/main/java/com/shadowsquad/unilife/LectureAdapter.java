package com.shadowsquad.unilife;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class LectureAdapter extends ArrayAdapter <Lecture>{

    private Context context;
    private int resourse;
    List<Lecture> lectures;

    LectureAdapter(Context context, int resourse, List<Lecture> lectures) {
        super(context,resourse,lectures);
        this.context = context;
        this.resourse =resourse;
        this.lectures =lectures;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //        return super.getView(position, convertView, parent);
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resourse,parent,false);

        TextView lectureNameView  = row.findViewById(R.id.lectureNameView);
        TextView lecturePersonView = row.findViewById(R.id.lecturePersonView);
        TextView lectureDateView = row.findViewById(R.id.lectureDateView);
        TextView lectureTimeView = row.findViewById(R.id.lectureTimeView);
        TextView lecturePlaceView = row.findViewById(R.id.lecturePlaceView);

//DB and row combine and data add
          Lecture  lecture = lectures.get(position);

        lectureNameView.setText(lecture.getName());
        lecturePersonView.setText(lecture.getConductedBy());
        lectureDateView.setText(lecture.getDate());
        lectureTimeView.setText(lecture.getTime());
        lecturePlaceView.setText(lecture.getPlace());

         return row;

    }
}

