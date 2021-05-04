package com.shadowsquad.unilife;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;


public class EventFragment extends Fragment {

    private Button adder;
    private Context context;
    private List<EventModle> eventModles;
    private DbHandler dbHandler;





//    private EventFragment eventFragment;
//    FragmentEventBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View View = inflater.inflate(R.layout.fragment_event,container,false);

         context= container.getContext();
         dbHandler =new DbHandler (context);
         eventModles =new ArrayList<>();

         eventModles = dbHandler.getallInsertedEvents();
         ListView listView = (ListView) View.findViewById(R.id.listViewbox);

         EventAdapter adapter= new EventAdapter(context,R.layout.activity_todoevent,eventModles);
         listView.setAdapter(adapter);

        // fragment to event after click buttton
        ImageButton BtnOpenExam = (ImageButton) View.findViewById(R.id.addEventcrt);

        BtnOpenExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent in = new Intent(getActivity(),createEvent.class);
                startActivity(in);
            }
        });

        //




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {

                EventModle eventModle = eventModles.get(position);

                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext(),
                        R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog_Background);



                builder.setTitle(eventModle.getEventName());
                builder .setMessage(eventModle.getPresenter());




                builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //FRAGMENT TO FARAGMENT

                        EventFragment  eventFragment= new EventFragment() ;
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container,eventFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();


                    }

                });
                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHandler.deleteEventTodo(eventModle.getId());

                        EventFragment  eventFragment= new EventFragment() ;
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container,eventFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }

                });

                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                      //  Intent i = new Intent(getActivity(), edit_event.class);
                        Intent intent = new Intent(context,edit_event.class);
                        intent.putExtra("id",String.valueOf(eventModle.getId()).trim());
                        startActivity(intent);
                    }
                });

                builder.show();

            }

        });

        return View;
    }

}

