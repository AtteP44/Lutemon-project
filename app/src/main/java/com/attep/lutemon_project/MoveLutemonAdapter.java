package com.attep.lutemon_project;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MoveLutemonAdapter extends RecyclerView.Adapter<MoveLutemonAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Lutemon lutemon);
    }

    private ArrayList<Lutemon> lutemons;
    private OnItemClickListener listener;
    private int selectedPosition = RecyclerView.NO_POSITION;

    public MoveLutemonAdapter(ArrayList<Lutemon> lutemons, OnItemClickListener listener) {
        this.lutemons = lutemons;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MoveLutemonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate your custom item layout (make sure the XML is saved as lutemon_view.xml)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lutemon_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoveLutemonAdapter.ViewHolder holder, int position) {
        Lutemon currentLutemon = lutemons.get(position);
        holder.bind(currentLutemon, position);
    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }

    public void setLutemons(ArrayList<Lutemon> lutemons) {
        this.lutemons = lutemons;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView lutemonImage, lutemonInfoBtn;
        TextView lutemonLvlTxt, lutemonNameTxt, lutemonHpTxt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lutemonImage = itemView.findViewById(R.id.LutemonImage);
            lutemonInfoBtn = itemView.findViewById(R.id.LutemonInfoBtn);
            lutemonLvlTxt = itemView.findViewById(R.id.LutemonLvlTxt);
            lutemonNameTxt = itemView.findViewById(R.id.LutemonNameTxt);
            lutemonHpTxt = itemView.findViewById(R.id.LutemonHpTxt);

            itemView.setOnClickListener(this);
        }

        public void bind(Lutemon lutemon, int position) {
            // Bind data to our views
            lutemonNameTxt.setText(lutemon.getName());
            lutemonLvlTxt.setText("Level: " + lutemon.getLevel());
            lutemonHpTxt.setText("HP: " + lutemon.getHealth() + "/" + lutemon.getMaxHealth());
            lutemonImage.setImageResource(lutemon.getImage()); // Assumes Lutemon provides an image resource

            // Highlight selected item
            if (position == selectedPosition) {
                itemView.setBackgroundColor(Color.LTGRAY);
            } else {
                itemView.setBackgroundColor(Color.TRANSPARENT);
            }
        }

        @Override
        public void onClick(View view) {
            // Get the old selected position and update selection state
            int previousPosition = selectedPosition;
            selectedPosition = getAdapterPosition();
            notifyItemChanged(previousPosition);
            notifyItemChanged(selectedPosition);

            // Fire the callback with the selected Lutemon
            if (listener != null) {
                listener.onItemClick(lutemons.get(selectedPosition));
            }
        }
    }
}
