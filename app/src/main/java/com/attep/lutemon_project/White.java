package com.attep.lutemon_project;

public class White extends Lutemon{


    public White(String name, String type ) {
        super(name, type);

        image = R.drawable.a_white_pixelated_monster_sprite_for_a_videogame;
        attack = 5;
        defence = 4;
        health = 20;
        maxHealth = 20;
    }
}
