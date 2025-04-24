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
import android.widget.TextView;
import android.widget.Toast;

import com.attep.lutemon_project.CreateLutemonActivity;
import com.attep.lutemon_project.Lutemon;
import com.attep.lutemon_project.R;
import com.attep.lutemon_project.Storage;
import com.attep.lutemon_project.TrainingActivity;

import java.io.Serializable;
import java.util.ArrayList;


public class TrainingFragment extends Fragment {

    private Button startTrainingBtn;
    private RadioGroup trainingRg;
    private TextView statusText;



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
        statusText = view.findViewById(R.id.trainingFragmentTxt);
        makeRadiobuttons(view);

        startTrainingBtn.setOnClickListener(v ->{
            trainingRg = view.findViewById(R.id.TrainingLutemonRg);

            Intent intent = new Intent(getActivity(), TrainingActivity.class);
            intent.putExtra("chosenOne", trainingRg.getCheckedRadioButtonId());
            startActivity(intent);
        });
        return view;
    }

    public void makeRadiobuttons(View view){
         trainingRg = view.findViewById(R.id.TrainingLutemonRg);

        ArrayList<Lutemon> lutemons = new ArrayList<>(Storage.getInstance().getLutemonsByLocation("Training").values());
        if (lutemons.isEmpty()) {

            startTrainingBtn.setEnabled(false);
            statusText.setText("Training is empty");
        } else {

            startTrainingBtn.setEnabled(true);
            statusText.setText("Choose lutemon to train");
        }
        int i = 0;
        for (Lutemon l : lutemons){
            RadioButton rb = new RadioButton(getContext());
            rb.setText(l.getName() + " Lvl:" +l.getLevel()+" ("+l.getType()+")");
            rb.setId(i++);
            trainingRg.addView(rb);

        }
    }
}