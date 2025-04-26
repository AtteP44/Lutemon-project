package com.attep.lutemon_project;

import android.content.Intent;

import java.io.Serializable;
import java.util.Random;

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

    protected int wins;
    protected int losses;
    protected int trainingSessions;

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
    public int getTrainingSessions() {
        return trainingSessions;
    }

    public int getLosses() {
        return losses;
    }

    public int getWins() {
        return wins;
    }
    public void addWin(){
        wins++;
    }
    public void addTrainingSession(){
        trainingSessions++;
    }

    public String getInfoString(){
        return "att: " + String.valueOf(this.getAttack()) + "; def: " + String.valueOf(this.getDefence()+ "; exp: "+ String.valueOf(this.getLevel()));
    }
    public String getArenaString(){
        return name +" Lvl:" + level;
    }
    public String getHealthStatus(){
        return "HP: "+ health+" / "+ maxHealth;
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
        int damageTaken = attacker.getAttack()- defence;
        if(damageTaken >= health){
            health = 0;
            losses++;
            return name + " Fainted";
        }
        else {
            health = health - damageTaken;
            return name + " took "+ damageTaken +" damage!";
        }
    }

    public void restoreHealth(){
        health = maxHealth;
    }



}
