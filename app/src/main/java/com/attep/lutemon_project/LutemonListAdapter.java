package com.attep.lutemon_project;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LutemonListAdapter extends RecyclerView.Adapter<LutemonViewHolder> {
    private Context context;
    private ArrayList<Lutemon> lutemons = new ArrayList<>();

    public LutemonListAdapter(ArrayList<Lutemon> lutemons, Context context) {
        this.lutemons = lutemons;
        this.context = context;
    }

    @NonNull
    @Override
    public LutemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LutemonViewHolder(LayoutInflater.from(context).inflate(R.layout.lutemon_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull LutemonViewHolder holder, int position) {
        /*holder.lutemonNameTxt.setText(lutemons.get(position).getName());
        holder.lutemonHpTxt.setText(lutemons.get(position).getHp());
        holder.lutemonLvlTxt.setText(lutemons.get(position).getLvl());
        holder.lutemonImage.setImageResource(lutemons.get(position).getImage());*/
        holder.lutemonInfoBtn.setOnClickListener(view ->{


        });

    }





    @Override
    public int getItemCount() {
        return lutemons.size();
    }
}
