package com.attep.lutemon_project;

import java.util.ArrayList;

public class Storage {

    private ArrayList<Lutemon> lutemonList = new ArrayList<>();
    private static Storage storage = null;

    public static Storage getInstance(){
        if(storage == null){
            storage = new Storage();
        }
        return storage;
    }

    public void addLutemon(Lutemon lutemon){
        lutemonList.add(lutemon);

    }



    public ArrayList<Lutemon> getAll() {
        return lutemonList;
    }

}
