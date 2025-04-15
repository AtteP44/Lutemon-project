package com.attep.lutemon_project;

public abstract class Lutemon {

    protected String name;
    protected String type;
    protected int attack;
    protected int defence;
    protected int level;
    protected int health;
    protected int maxHealth;
    protected int image;
    protected int id;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public int getLevel() {
        return level;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getId() {
        return id;
    }

    public int getImage() {
        return image;
    }




    public Lutemon(String name, String type, int id){
        this.name = name;

        this.type = type;
        this.id = id;
        level = 1;
    }

    public void levelUp(){
        level = +1;
        attack =+1;
        restoreHealth();
    }

    public void restoreHealth(){
        health = maxHealth;
    }

}
