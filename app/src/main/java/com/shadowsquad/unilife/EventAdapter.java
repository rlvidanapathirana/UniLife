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

public class EventAdapter extends ArrayAdapter<EventModle> {
    private Context context;
    private int resourse;
    List<EventModle> eventModles;

    EventAdapter(Context context, int resourse, List<EventModle> eventModles) {
        super(context,resourse,eventModles);
        this.context = context;
        this.resourse =resourse;
        this.eventModles =eventModles;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);

        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resourse,parent,false);

        TextView eventnameview  = row.findViewById(R.id.eventnameview);
        TextView presenterview = row.findViewById(R.id.presenterview);
        TextView venueview = row.findViewById(R.id.venueview);
//        TextView startview = row.findViewById(R.id.startview);
//        TextView endview  = row.findViewById(R.id.endview);
        TextView dateview = row.findViewById(R.id.dateview);
//        TextView noeview = row.findViewById(R.id.noeview);

//Images
//ImageView imageView = row.findViewById(R.id.)
//DB and row combine and data add

        EventModle eventModle = eventModles.get(position);

        eventnameview.setText(eventModle.getEventName());
        presenterview.setText(eventModle.getPresenter());
        venueview.setText(eventModle.getVenue());
//        startview.setText(eventModle.getStatTime());
//        endview.setText(eventModle.getEndTime());
        dateview.setText(eventModle.getDate());
//        noeview.setText(eventModle.getNote());


        return row;

    }
}

