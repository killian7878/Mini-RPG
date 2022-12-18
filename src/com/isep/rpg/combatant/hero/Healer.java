package com.isep.rpg.combatant.hero;

import com.isep.rpg.combatant.Enemy;
import com.isep.rpg.item.Armor;
import com.isep.rpg.item.Weapon;

import java.util.List;
import java.util.Random;

public class Healer  extends Spellcaster {

    public int healPower;
    public Healer(String name, int health, int attack, int defense, Weapon weapon, Armor armor, int maxMana, int potion, int food, int healPower) {
        super(name, health, attack, defense, weapon, armor, maxMana, potion, food);
        this.healPower = healPower;
    }

    @Override
    public void attack(List<Enemy> enemies) {

    }

    public void heal(List<Hero> heroes){
        if (mana >= spellCost){
            mana -= spellCost;
            List<Hero> aliveHeroes = heroes.stream().filter(Hero::isAlive).toList();
            Hero target = aliveHeroes.get(new Random().nextInt(aliveHeroes.size()));
            target.beHealed(healPower);
        }
    }

    public int getHealPower(){
        return this.healPower;
    }
}
