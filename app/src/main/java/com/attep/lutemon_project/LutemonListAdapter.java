package com.attep.lutemon_project;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
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
        holder.lutemonNameTxt.setText(lutemons.get(position).getName());

        holder.lutemonHpTxt.setText("HP: "+lutemons.get(position).getHealth()+"/"+lutemons.get(position).getMaxHealth());
        holder.lutemonLvlTxt.setText("Level: "+lutemons.get(position).getLevel());
        holder.lutemonImage.setImageResource(lutemons.get(position).getImage());
        holder.lutemonWinTxt.setText("Wins: "+ lutemons.get(position).getWins());
        holder.lutemonLossTxt.setText("Losses: "+ lutemons.get(position).getLosses());
        holder.lutemonTrainTxt.setText("Training: "+ lutemons.get(position).getTrainingSessions());
        holder.lutemonAtckTxt.setText("Atck "+ lutemons.get(position).getAttack());
        holder.lutemonDefTxt.setText("Def "+ lutemons.get(position).getDefence());

        holder.lutemonInfoBtn.setOnClickListener(view ->{
            if(holder.lutemonStatTxt.getVisibility() == ViewGroup.GONE){
                holder.lutemonStatTxt.setVisibility(View.VISIBLE);
                holder.lutemonWinTxt.setVisibility(View.VISIBLE);
                holder.lutemonLossTxt.setVisibility(View.VISIBLE);
                holder.lutemonTrainTxt.setVisibility(View.VISIBLE);
                holder.lutemonAtckTxt.setVisibility(View.VISIBLE);
                holder.lutemonDefTxt.setVisibility(View.VISIBLE);
            } else{
                holder.lutemonStatTxt.setVisibility(View.GONE);
                holder.lutemonWinTxt.setVisibility(View.GONE);
                holder.lutemonLossTxt.setVisibility(View.GONE);
                holder.lutemonTrainTxt.setVisibility(View.GONE);
                holder.lutemonAtckTxt.setVisibility(View.GONE);
                holder.lutemonDefTxt.setVisibility(View.GONE);

            }



        });

    }





    @Override
    public int getItemCount() {
        return lutemons.size();
    }
}
