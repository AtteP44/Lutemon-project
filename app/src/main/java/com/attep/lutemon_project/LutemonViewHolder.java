package com.attep.lutemon_project;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LutemonViewHolder extends RecyclerView.ViewHolder {

    ImageView lutemonImage, lutemonInfoBtn;
    TextView lutemonLvlTxt, lutemonNameTxt, lutemonHpTxt;
    public LutemonViewHolder(@NonNull View itemView) {
        super(itemView);
        lutemonImage = itemView.findViewById(R.id.LutemonImage);
        lutemonInfoBtn = itemView.findViewById(R.id.LutemonInfoBtn);
        lutemonLvlTxt = itemView.findViewById(R.id.LutemonLvlTxt);
        lutemonNameTxt = itemView.findViewById(R.id.LutemonNameTxt);
        lutemonHpTxt = itemView.findViewById(R.id.LutemonHpTxt);
    }
}
