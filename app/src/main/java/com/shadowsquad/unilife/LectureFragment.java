package com.shadowsquad.unilife;

        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.os.Build;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.ImageButton;
        import android.widget.ListView;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.annotation.RequiresApi;
        import androidx.fragment.app.Fragment;
        import androidx.fragment.app.FragmentManager;
        import androidx.fragment.app.FragmentTransaction;

        import com.google.android.material.dialog.MaterialAlertDialogBuilder;

        import java.util.ArrayList;
        import java.util.List;

public class LectureFragment extends Fragment {

private ImageButton imgadd;
private ListView listView;
private TextView count;
private Context  context;
private DbHandlerLecture dbHandlerLecture;
private List<Lecture>lectures;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View View = inflater.inflate(R.layout.fragment_lecture,container,false);

        imgadd = (ImageButton) View.findViewById(R.id.editBtnOpenLecture);
        listView = (ListView) View.findViewById(R.id.lectureList);
        context = container.getContext();

        dbHandlerLecture =   new DbHandlerLecture(context);
        lectures =new ArrayList<>();
        lectures = dbHandlerLecture.getallInsertedLecture();
        LectureAdapter adapter= new LectureAdapter(context,R.layout.activity_todo_lecture,lectures);
        listView.setAdapter(adapter);


      //count = ()findViewById(R.id.lecturecount);



        ImageButton BtnOpenLecture = (ImageButton) View.findViewById(R.id.editBtnOpenLecture);
        BtnOpenLecture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent in = new Intent(getActivity(),AddLecture.class);
                startActivity(in);




            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id)
            {Lecture lecture = lectures.get(position);
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext(),
                    R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog_Background);
            builder.setTitle(lecture.getName());
            builder .setMessage(lecture.getConductedBy());


                builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //FRAGMENT TO FARAGMENT
                        LectureFragment   lectureFragment = new LectureFragment() ;
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container,lectureFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();

                    }
                });
                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHandlerLecture.deleteLectureTodo(lecture.getId());


                        LectureFragment lectureFragment = new LectureFragment();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, lectureFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                });
                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //  Intent i = new Intent(getActivity(), edit_event.class);
                        Intent intent = new Intent(context,EditLecture.class);
                        intent.putExtra("id",String.valueOf(lecture.getId()).trim());
                        startActivity(intent);
                    }
                });
                builder.show();
            }
        });





        return View;
    }

}




