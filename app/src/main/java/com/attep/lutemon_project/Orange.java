package com.attep.lutemon_project;

public class Orange extends Lutemon{
    public Orange(String name, String type) {
        super(name, type);
        image=R.drawable.an_orange_pixelated_monster_sprite_for_a_videogame_without_a_background;
        attack = 5;
        defence = 2;
        health = 20;
        maxHealth = 20;
    }
}
