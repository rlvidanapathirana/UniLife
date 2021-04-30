package com.shadowsquad.unilife;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;


public class EventFragment extends Fragment {

    Button adder;
    Context context;
    private List<EventModle> eventModles;
    private DbHandler dbHandler;


    public EventFragment() {

    }




//    private EventFragment eventFragment;
//    FragmentEventBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View View = inflater.inflate(R.layout.fragment_event,container,false);

         context= container.getContext();

         dbHandler =new DbHandler (context);
         eventModles =new ArrayList<>();
         ListView listView = (ListView) View.findViewById(R.id.listViewbox);
         eventModles = dbHandler.getallInsertedEvents();
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
        return View;
    }

}


//
//        binding = FragmentEventBinding.inflate(getLayoutInflater());
//        return binding.getRoot();
//    }
//
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        binding.addEventcrt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Fragment addEvent = new addEvent();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.fragment_container, addEvent);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//            }
//        });
//    }
//
//    public void onDestroy() {
//
//        super.onDestroy();
//        binding = null;
//    }
//}


