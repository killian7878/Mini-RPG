package com.isep.rpg.combatant;

import com.isep.rpg.combatant.hero.Hero;

import java.util.List;
import java.util.Random;

public class Enemy extends Combatant {


    public Enemy(String name, int health, int attack, int defense) {
        super(name, health, attack, defense);
    }

    @Override
    public int getDefense() {
        return defense;
    }

    public void attack(List<Hero> heroes) {
        List<Hero> aliveHeroes = heroes.stream().filter(Hero::isAlive).toList();
        Hero target = aliveHeroes.get(new Random().nextInt(aliveHeroes.size()));
        int damage = Math.max(0, attack - target.getDefense());
        target.takeDamage(damage);
    }

    public void takeDamage(int damage) {
        health = Math.max(0, health - Math.max(0, damage - defense));
    }
}
