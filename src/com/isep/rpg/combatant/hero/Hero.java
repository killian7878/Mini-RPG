package com.isep.rpg.combatant.hero;

import com.isep.rpg.combatant.Combatant;
import com.isep.rpg.combatant.Enemy;
import com.isep.rpg.item.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Hero extends Combatant {
    private final Weapon weapon;
    private final Armor armor;
    public ArrayList<Food> food = new ArrayList<>();

    private boolean defend = false;

    public Hero(String name, int health, int attack, int defense, Weapon weapon, Armor armor, int food) {
        super(name, health, attack, defense);
        this.weapon = weapon;
        this.armor = armor;
        for (int i = 0; i < food; i++) {
            this.food.add(new Food(7));
        }
    }

    public void beHealed(int heal){
        health += heal;
    }

    public void eat(){
        if(food.size() > 0){
            Food food = this.food.get(0);
            this.health += food.useFood();
            this.food.remove(0);
        }
    }

    public int getFood() {
        return food.size();
    }


    public int getAttack() {
        return attack + weapon.getDamage();
    }

    public int getDefense() {
        return defend ? defense + armor.getDefense() : armor.getDefense();
    }

    public void takeDamage(int damage){
        health = Math.max(0, health - Math.max(0, damage - getDefense()));
    }

    public abstract void attack(List<Enemy> enemies);

    public void defend() {
        defend = true;
    }

    public void resetDefense(){
        defend = false;
    }

    public void increaseDamage(){
        attack += attack*0.3;
    }

    public void increaseDefense(){
        defense += defense*0.3;
    }

    public void increaseConsumableEfficiency(){
        for (Food food : food) {
            food.increaseFood();
        }
    }

    public void addConsumables(){
        for (int i = 0; i < 3; i++) {
            this.food.add(new Food(7));
        }
    }

    public int getWeapon() {
        return weapon.getDamage();
    }

    public int getArmor() {
        return armor.getDefense();
    }
}
