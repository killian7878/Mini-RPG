package com.isep.rpg.combatant.hero;

import com.isep.rpg.item.Armor;
import com.isep.rpg.item.Food;
import com.isep.rpg.item.Potion;
import com.isep.rpg.item.Weapon;

import java.util.ArrayList;

public abstract class Spellcaster extends Hero{

    public int mana;
    public int maxMana;

    public ArrayList<Potion> potion = new ArrayList<>();

    public int spellCost = 5;
    public Spellcaster(String name, int health, int attack, int defense, Weapon weapon, Armor armor, int maxMana, int potion, int food) {
        super(name, health, attack, defense, weapon, armor, food);
        this.mana = maxMana;
        this.maxMana = maxMana;
        for (int i = 0; i < potion; i++) {
            this.potion.add(new Potion(7));
        }
    }

    public void drink(){
        if(potion.size() > 0){
            mana += potion.get(0).usePotion();
            potion.remove(0);
        }
    }

    public void increaseSpellEfficiency(){}

    @Override
    public void increaseConsumableEfficiency(){
        for (Food food : food) {
            food.increaseFood();
        }
        for (Potion currPotion : potion) {
            currPotion.increasePotion();
        }
    }



    public int getMana() {
        return mana;
    }

    public void reduceSpellCost(){
        spellCost -= 2;
    }

}
