package com.isep.rpg.item;
public class Food extends Consumable {
    int healthPoints;

    public Food(int healthPoints) {
        super("Lembas");
        this.healthPoints = healthPoints;
    }

    public int useFood() {
        return healthPoints;
    }

    public void increaseFood(){
        healthPoints += 3;
    }

}
