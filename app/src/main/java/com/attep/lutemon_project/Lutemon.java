package com.attep.lutemon_project;

import java.io.Serializable;

public abstract class Lutemon implements Serializable {

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

    public String getInfoString(){
        return "att: " + String.valueOf(this.getAttack()) + "; def: " + String.valueOf(this.getDefence()+ "; exp: "+ String.valueOf(this.getLevel()));
    }

    public void setId(int id) {
        this.id = id;
    }

    public Lutemon(String name, String type){
        this.name = name;

        this.type = type;

        level = 1;
    }

    public void levelUp(){
        level ++;
        attack ++;
        restoreHealth();
    }

    public String defense(Lutemon attacker){
        if(attacker.getAttack() >= health){
            health = 0;
            return name + " kuoli";
        }
        else {
            health = health - attacker.getAttack();
            return name + " onnistui välttämään kuoleman";
        }
    }

    public void restoreHealth(){
        health = maxHealth;
    }

}
