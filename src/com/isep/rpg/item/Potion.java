package com.isep.rpg.item;

public class Potion extends Consumable {
    int manaPoints;
    public Potion(int manaPoints) {
        super("Potion");
        this.manaPoints = manaPoints;
    }

    public int usePotion() {
        return this.manaPoints;
    }

    public void increasePotion(){
        manaPoints += 3;
    }
}
