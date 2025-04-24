package com.attep.lutemon_project;

public class Orange extends Lutemon{
    public Orange(String name, String type) {
        super(name, type);
        image=R.drawable.an_orange_pixelated_monster_sprite_for_a_videogame_without_a_background;
        attack = 8;
        defence = 1;
        health = 17;
        maxHealth = 17;
    }
}
