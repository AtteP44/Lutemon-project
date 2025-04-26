package com.attep.lutemon_project;

import java.io.Serializable;
import java.util.Random;

public abstract class Lutemon implements Serializable {
    private static final Random RNG = new Random();

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
    public int getAttackRandomized() {
        return attack + RNG.nextInt(3);  // 0, 1, or 2
    }
    public int getDefenseRandomized() {
        return defence + RNG.nextInt(3);  // 0, 1, or 2
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

    public int defense(int attackValue, int defence){
        int damageTaken = attackValue - defence;
        if(damageTaken >= health){
            damageTaken = health;
            health = 0;
            losses++;
        }
        else {
            health = health - damageTaken;
        }
        return damageTaken;
    }

    public void restoreHealth(){
        health = maxHealth;
    }



}
