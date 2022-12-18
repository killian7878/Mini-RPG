package com.isep.rpg.item;

public class Weapon extends Item{

    int additionalDamage;

    public Weapon(String name, int additionalDamage) {
        super(name);
        this.additionalDamage = additionalDamage;
    }

    public int getDamage() {
        return additionalDamage;
    }
}
