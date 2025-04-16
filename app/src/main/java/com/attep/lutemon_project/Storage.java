package com.attep.lutemon_project;

import java.util.ArrayList;
import java.util.HashMap;

public class Storage {

    private int createdLutemonsAmount;
    private HashMap<String, LutemonLocation> locations;
    private ArrayList<Lutemon> lutemonList = new ArrayList<>();
    private static Storage storage = null;
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

    // returns everything as arraylist, this should probably also be hashmap
    public ArrayList<Lutemon> getAll() {
        ArrayList<Lutemon> all = new ArrayList<>();
        for (LutemonLocation location : locations.values()) {
            all.addAll(location.getLutemons().values());
        }
        return all;
    }

}
