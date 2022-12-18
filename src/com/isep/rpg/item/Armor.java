package com.isep.rpg.item;

public class Armor extends Item{
    private final int defense;

    public Armor(String name, int defense) {
        super(name);
        this.defense = defense;
    }

    public int getDefense() {
        return defense;
    }


}
