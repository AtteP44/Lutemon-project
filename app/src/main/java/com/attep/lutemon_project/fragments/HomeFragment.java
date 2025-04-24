package com.attep.lutemon_project.fragments;

import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.attep.lutemon_project.CreateLutemonActivity;
import com.attep.lutemon_project.GameActivity;
import com.attep.lutemon_project.LutemonListAdapter;
import com.attep.lutemon_project.MainActivity;
import com.attep.lutemon_project.MoveLutemonsActivity;
import com.attep.lutemon_project.R;
import com.attep.lutemon_project.Storage;


public class HomeFragment extends Fragment {


    private RecyclerView recyclerView;
    private Button createBtn;

    private Button moveBtn;



    public HomeFragment() {
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        createBtn = view.findViewById(R.id.AddLutemonButton);
        moveBtn = view.findViewById(R.id.MoveMonsterBtn);

        recyclerView = view.findViewById(R.id.HomeRv);

            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(new LutemonListAdapter(
                new ArrayList<>(Storage.getInstance().getLutemonsByLocation("Home").values()),
                getContext()
          ));


        createBtn.setOnClickListener(v ->{
            Intent intent = new Intent(getActivity(), CreateLutemonActivity.class);
            startActivity(intent);
        });
        moveBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MoveLutemonsActivity.class);
            startActivity(intent);
        });

        return view;
    }


}