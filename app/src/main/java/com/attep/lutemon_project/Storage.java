package com.attep.lutemon_project;

import android.content.Context;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Storage {

    private int createdLutemonsAmount;
    private HashMap<String, LutemonLocation> locations;
    private ArrayList<Lutemon> lutemonList = new ArrayList<>();
    private static Storage storage = null;
    private  static String FILENAME = "lutemons.data";
    private Storage() {
        locations = new HashMap<>();
        locations.put("Home", new Home());
        locations.put("Training", new TrainingArea());
        locations.put("Arena", new BattleArena());
    }

    public static Storage getInstance(){
        if(storage == null){
            storage = new Storage();
        }
        return storage;
    }

    public void addLutemon(Lutemon lutemon) {
        addLutemon(lutemon, "Home");
    }

    public void addLutemon(Lutemon lutemon, String locationName) {
        lutemon.setId(createdLutemonsAmount);
        createdLutemonsAmount++;
        LutemonLocation location = locations.get(locationName);
        if(location != null) {
            location.addLutemon(lutemon);
        } else {
            locations.get("Home").addLutemon(lutemon);
        }
    }
    public boolean moveLutemon(Lutemon lutemon, String fromLocation, String toLocation) {
        LutemonLocation fromLoc = locations.get(fromLocation);
        LutemonLocation toLoc = locations.get(toLocation);

        if (fromLoc == null || toLoc == null) {
            return false;
        }

        // remove from original location and add to new location
        if (fromLoc.getLutemons().containsKey(lutemon.getId())) {
            fromLoc.removeLutemon(lutemon);
            toLoc.addLutemon(lutemon);
            return true;
        }
        return false;
    }
    public HashMap<Integer, Lutemon> getLutemonsByLocation(String locationName) {
        LutemonLocation location = locations.get(locationName);
        if(location != null) {
            return location.getLutemons();
        }
        return new HashMap<>();
    }

    public Lutemon getLutemonById(int id) {
        for (LutemonLocation loc : locations.values()) {
            if (loc.getLutemons().containsKey(id)) {
                return loc.getLutemons().get(id);
            }
        }
        return null;
    }


    // returns everything as arraylist, this should probably also be hashmap
    public ArrayList<Lutemon> getAll() {
        ArrayList<Lutemon> all = new ArrayList<>();
        for (LutemonLocation location : locations.values()) {
            all.addAll(location.getLutemons().values());
        }
        return all;
    }

    public void saveLutemons(Context context){
        try {
            ObjectOutputStream lutemonWriter = new ObjectOutputStream(context.openFileOutput(FILENAME,Context.MODE_PRIVATE));
            lutemonWriter.writeObject(getAll());
            lutemonWriter.close();
            Toast.makeText(context, "Game saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Saving lutemos failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void loadLutemons(Context context){
        try {
            ObjectInputStream luetmonReader = new ObjectInputStream(context.openFileInput(FILENAME));
            ArrayList <Lutemon> loadedOnes = (ArrayList<Lutemon>) luetmonReader.readObject();
            for (Lutemon l : loadedOnes ) {
                locations.get("Home").addLutemon(l);
                createdLutemonsAmount++;

            }
            Toast.makeText(context, "Game loaded", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            Toast.makeText(context, "Loading lutemons failed", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(context, "Loading lutemons failed", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            Toast.makeText(context, "Loading lutemons failed", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

}
