package com.isep.rpg.combatant.hero;

import com.isep.rpg.combatant.Enemy;
import com.isep.rpg.item.Armor;
import com.isep.rpg.item.Weapon;

import java.util.List;
import java.util.Random;

public class Warrior extends Hero {

    public Warrior(String name, int health, int attack, int defense, Weapon weapon, Armor armor, int food) {
        super(name, health, attack, defense, weapon, armor, food);
    }

    @Override
    public void attack(List<Enemy> enemies) {
        List<Enemy> aliveEnemies = enemies.stream().filter(Enemy::isAlive).toList();
        Enemy target = aliveEnemies.get(new Random().nextInt(aliveEnemies.size()));
        int damage = Math.max(0, getAttack() - target.getDefense());
        target.takeDamage(damage);
    }
}
