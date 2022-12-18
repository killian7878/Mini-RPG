package com.isep.rpg.combatant;

public abstract class Combatant {
    public String name;
    protected int health, attack, defense;


    public Combatant(String name, int health, int attack,  int defense) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public abstract int getDefense();

    public abstract void takeDamage(int damage);

}
