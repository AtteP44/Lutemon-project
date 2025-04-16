package com.attep.lutemon_project.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.attep.lutemon_project.ArenaActivity;
import com.attep.lutemon_project.Lutemon;
import com.attep.lutemon_project.R;
import com.attep.lutemon_project.Storage;
import com.attep.lutemon_project.TrainingActivity;


public class ArenaFragment extends Fragment {

    private Button startArenaBtn;
    private RadioGroup arenaRg;



    public ArenaFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_arena, container, false);
        startArenaBtn = view.findViewById(R.id.StartArenaActivityBtn);
        makeRadiobuttons(view);

        startArenaBtn.setOnClickListener(v ->{
            Intent intent = new Intent(getActivity(), ArenaActivity.class);
            startActivity(intent);
        });


        return view;
    }

    public void makeRadiobuttons(View view){
        arenaRg = view.findViewById(R.id.ArenaLutemonRg);

        ArrayList<Lutemon> lutemons = new ArrayList<>(Storage.getInstance().getLutemonsByLocation("Arena").values());
        int i = 0;
        for (Lutemon l : lutemons){
            RadioButton rb = new RadioButton(getContext());
            rb.setText(l.getName() + " Lvl:" +l.getLevel()+" ("+l.getType()+")");
            rb.setId(i++);
            arenaRg.addView(rb);

        }
    }
}