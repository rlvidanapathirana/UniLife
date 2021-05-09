package com.shadowsquad.unilife;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ExamAdapter extends ArrayAdapter <ExamModel> {

    private Context context;
    private int resource;
    List<ExamModel> examModels;

    ExamAdapter(Context context, int resource, List<ExamModel> examModels){
        super(context,resource,examModels);
        this.context = context;
        this.resource = resource;
        this.examModels = examModels;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);

        //Link to Fragment_exam.xml TextViews
        TextView examNameView = row.findViewById(R.id.examNameView);
        TextView dateView = row.findViewById(R.id.dateView);
        TextView timeView = row.findViewById(R.id.timeView);
        TextView placeView = row.findViewById(R.id.placeView);
        TextView typeView = row.findViewById(R.id.typeView);

        //Images
        //ImageView imageView = row.findViewById(R.id.)

        //DB and row combine and data add
        ExamModel examModel = examModels.get(position);

        examNameView.setText(examModel.getExamName());
        dateView.setText(examModel.getDate());
        timeView.setText(examModel.getTime());
        placeView.setText(examModel.getPlace());
        typeView.setText(examModel.getType());

        //imageView.setVisibility

        //if (examModel.getFinished() > 0){
        //    i
        //}
        return row;
    }
}
