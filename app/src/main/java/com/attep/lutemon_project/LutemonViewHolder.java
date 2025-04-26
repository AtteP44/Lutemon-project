package com.attep.lutemon_project;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LutemonViewHolder extends RecyclerView.ViewHolder {

    ImageView lutemonImage, lutemonInfoBtn;
    TextView lutemonLvlTxt, lutemonNameTxt, lutemonHpTxt, lutemonWinTxt, lutemonLossTxt, lutemonTrainTxt, lutemonAtckTxt, lutemonDefTxt, lutemonStatTxt;
    public LutemonViewHolder(@NonNull View itemView) {
        super(itemView);
        lutemonImage = itemView.findViewById(R.id.LutemonImage);
        lutemonInfoBtn = itemView.findViewById(R.id.LutemonInfoBtn);
        lutemonLvlTxt = itemView.findViewById(R.id.LutemonLvlTxt);
        lutemonNameTxt = itemView.findViewById(R.id.LutemonNameTxt);
        lutemonHpTxt = itemView.findViewById(R.id.LutemonHpTxt);
        lutemonWinTxt = itemView.findViewById(R.id.LutemonWinTxt);
        lutemonLossTxt = itemView.findViewById(R.id.LutemonLossTxt);
        lutemonTrainTxt = itemView.findViewById(R.id.LutemonTrainTxt);
        lutemonAtckTxt = itemView.findViewById(R.id.LutemonAttackTxt);
        lutemonDefTxt = itemView.findViewById(R.id.LutemonDefTxt);
        lutemonStatTxt = itemView.findViewById(R.id.LutemonStatTxt);
    }
}
