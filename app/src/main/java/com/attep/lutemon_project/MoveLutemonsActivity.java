package com.attep.lutemon_project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MoveLutemonsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Spinner destinationSpinner;
    private Button moveButton;
    private Lutemon selectedLutemon = null;

    private ArrayList<Lutemon> lutemons;
    private ArrayList<String> locationOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_lutemons);

        recyclerView = findViewById(R.id.moveLutemonsRecyclerView);
        destinationSpinner = findViewById(R.id.destinationSpinner);
        moveButton = findViewById(R.id.moveButton);

        // Set up destination options; these must correspond to the keys used in Storage.
        locationOptions = new ArrayList<>();
        locationOptions.add("Home");
        locationOptions.add("Training");
        locationOptions.add("Arena");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                locationOptions);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        destinationSpinner.setAdapter(spinnerAdapter);

        // Get all lutemons from storage
        lutemons = Storage.getInstance().getAll();

        // Set up RecyclerView with our custom adapter that supports selection
        MoveLutemonAdapter adapter = new MoveLutemonAdapter(lutemons, new MoveLutemonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Lutemon lutemon) {
                selectedLutemon = lutemon;
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        ImageView returnBtn = findViewById(R.id.ReturnBtn);

        returnBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        });

        // When user taps move button, perform the move using Storage.moveLutemon()
        moveButton.setOnClickListener(v -> {
            if(selectedLutemon == null) {
                Toast.makeText(MoveLutemonsActivity.this, "Please select a Lutemon first!", Toast.LENGTH_SHORT).show();
                return;
            }
            String destination = destinationSpinner.getSelectedItem().toString();

            // Determine current location using a helper method below
            String currentLocation = getCurrentLocation(selectedLutemon);
            if(currentLocation == null) {
                Toast.makeText(MoveLutemonsActivity.this, "Could not determine current location", Toast.LENGTH_SHORT).show();
                return;
            }
            // No need to move if target is same as current
            if(currentLocation.equals(destination)) {
                Toast.makeText(MoveLutemonsActivity.this, "Lutemon is already in " + destination, Toast.LENGTH_SHORT).show();
                return;
            }
            boolean success = Storage.getInstance().moveLutemon(selectedLutemon, currentLocation, destination);
            if(success) {
                Toast.makeText(MoveLutemonsActivity.this, "Lutemon moved from " + currentLocation + " to " + destination, Toast.LENGTH_SHORT).show();
                // Refresh the adapter list if necessary. For simplicity, we re-fetch all lutemons.
                lutemons = Storage.getInstance().getAll();
                adapter.setLutemons(lutemons);
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(MoveLutemonsActivity.this, "Move failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Helper method that returns the current location (as a String key) for the given Lutemon.
     */
    private String getCurrentLocation(Lutemon lutemon) {
        if(Storage.getInstance().getLutemonsByLocation("Home").containsKey(lutemon.getId())) {
            return "Home";
        }
        if(Storage.getInstance().getLutemonsByLocation("Training").containsKey(lutemon.getId())) {
            return "Training";
        }
        if(Storage.getInstance().getLutemonsByLocation("Arena").containsKey(lutemon.getId())) {
            return "Arena";
        }
        return null;
    }
}
