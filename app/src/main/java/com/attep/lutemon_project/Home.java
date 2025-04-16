package com.attep.lutemon_project;

public class Home extends LutemonLocation {

    @Override
    public String getLocationName() {
        return "Home";
    }

    //overridden for full health returning to home
    @Override
    public void addLutemon(Lutemon lutemon) {
        lutemon.restoreHealth();
        super.addLutemon(lutemon);
    }
}
