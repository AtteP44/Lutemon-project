package com.attep.lutemon_project;

import java.util.ArrayList;

public class Storage {

    private int createdLutemonsAmount;

    private ArrayList<Lutemon> lutemonList = new ArrayList<>();
    private static Storage storage = null;

    public static Storage getInstance(){
        if(storage == null){
            storage = new Storage();
        }
        return storage;
    }

    public void addLutemon(Lutemon lutemon){
        lutemon.setId(createdLutemonsAmount);
        createdLutemonsAmount++;
        lutemonList.add(lutemon);

    }



    public ArrayList<Lutemon> getAll() {
        return lutemonList;
    }

}
