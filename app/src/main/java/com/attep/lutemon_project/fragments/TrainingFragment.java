package com.attep.lutemon_project.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.attep.lutemon_project.CreateLutemonActivity;
import com.attep.lutemon_project.Lutemon;
import com.attep.lutemon_project.R;
import com.attep.lutemon_project.Storage;
import com.attep.lutemon_project.TrainingActivity;

import java.util.ArrayList;


public class TrainingFragment extends Fragment {

    private Button startTrainingBtn;
    private RadioGroup trainingRg;



    public TrainingFragment() {
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
        View view = inflater.inflate(R.layout.fragment_training, container, false);
        startTrainingBtn = view.findViewById(R.id.StartTrainingActivityBtn);
        makeRadiobuttons(view);

        startTrainingBtn.setOnClickListener(v ->{
            Intent intent = new Intent(getActivity(), TrainingActivity.class);
            startActivity(intent);
        });


        return view;
    }

    public void makeRadiobuttons(View view){
         trainingRg = view.findViewById(R.id.TrainingLutemonRg);

        ArrayList<Lutemon> lutemons = new ArrayList<>(Storage.getInstance().getLutemonsByLocation("Training").values());
        int i = 0;
        for (Lutemon l : lutemons){
            RadioButton rb = new RadioButton(getContext());
            rb.setText(l.getName() + " Lvl:" +l.getLevel()+" ("+l.getType()+")");
            rb.setId(i++);
            trainingRg.addView(rb);

        }
    }
}