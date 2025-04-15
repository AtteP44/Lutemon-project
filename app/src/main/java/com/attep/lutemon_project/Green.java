package com.attep.lutemon_project;

public class Green extends Lutemon{
    public Green(String name, String type , int id) {
        super(name, type, id);
        image=R.drawable.a_green_pixelated_monster_sprite_for_a_videogame;
        attack = 5;
        defence = 5;
        health = 20;
        maxHealth = 20;
    }
}
