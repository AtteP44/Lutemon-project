package com.attep.lutemon_project.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.attep.lutemon_project.ArenaActivity;
import com.attep.lutemon_project.Lutemon;
import com.attep.lutemon_project.R;
import com.attep.lutemon_project.Storage;
import com.attep.lutemon_project.TrainingActivity;


public class ArenaFragment extends Fragment {

    private Button startArenaBtn;
    private LinearLayout arenaContainer;
    private TextView emptyText;

    private final List<CheckBox> checkBoxes = new ArrayList<>();



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
    public void onResume() {
        super.onResume();
        populateCheckBoxes();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_arena, container, false);
        startArenaBtn = view.findViewById(R.id.StartArenaActivityBtn);
        arenaContainer = view.findViewById((R.id.arenaLutemonContainer));
        emptyText = view.findViewById(R.id.emptyArenaTextView);
        populateCheckBoxes();

        startArenaBtn.setOnClickListener(v -> {
            List<Integer> selectedIds = new ArrayList<>();
            for (CheckBox cb : checkBoxes) {
                if (cb.isChecked()) {
                    selectedIds.add((Integer) cb.getTag());
                }
            }
            if (selectedIds.size() != 2) {
                Toast.makeText(getContext(),
                        "Please select exactly two Lutemons", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(getActivity(), ArenaActivity.class);
            intent.putExtra("lutemon1_id", selectedIds.get(0));
            intent.putExtra("lutemon2_id", selectedIds.get(1));
            startActivity(intent);
        });


        return view;
    }


    private void populateCheckBoxes() {
        arenaContainer.removeAllViews();
        checkBoxes.clear();

        ArrayList<Lutemon> lutemons =
                new ArrayList<>(Storage.getInstance().getLutemonsByLocation("Arena").values());
        if (lutemons.isEmpty()) {

            emptyText.setText("The arena is empty");
            startArenaBtn.setEnabled(false);
        } else {

            emptyText.setText("Chose 2 lutemons to fight");
            startArenaBtn.setEnabled(true);
        }
        for (Lutemon l : lutemons) {
            CheckBox cb = new CheckBox(getContext());
            cb.setText(l.getName() + " Lvl:" + l.getLevel() + " (" + l.getType() + ")");

            cb.setTag(l.getId());
            arenaContainer.addView(cb);
            checkBoxes.add(cb);
        }
    }
}