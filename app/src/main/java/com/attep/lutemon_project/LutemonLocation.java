package com.attep.lutemon_project;

import java.util.HashMap;

public abstract class LutemonLocation {
    protected HashMap<Integer, Lutemon> lutemons;

    public LutemonLocation() {
        this.lutemons = new HashMap<>();
    }

    public void addLutemon(Lutemon lutemon) {
        lutemons.put(lutemon.getId(), lutemon);
    }


    public void removeLutemon(Lutemon lutemon) {
        lutemons.remove(lutemon.getId());
    }


    public HashMap<Integer, Lutemon> getLutemons() {
        return lutemons;
    }


    public abstract String getLocationName();
}
