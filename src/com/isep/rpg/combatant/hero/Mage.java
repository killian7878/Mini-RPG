package com.isep.rpg.combatant.hero;

import com.isep.rpg.combatant.Enemy;
import com.isep.rpg.item.Armor;
import com.isep.rpg.item.Weapon;

import java.util.List;
import java.util.Random;

public class Mage extends Spellcaster {
    public Mage(String name, int health, int attack, int defense, Weapon weapon, Armor armor, int maxMana, int potion, int food) {
        super(name, health, attack, defense, weapon, armor, maxMana, potion, food);
    }

    @Override
    public void attack(List<Enemy> enemies) {
        if (mana >= spellCost){
            mana -= spellCost;
            List<Enemy> aliveEnemies = enemies.stream().filter(Enemy::isAlive).toList();
            if (aliveEnemies.size() > 0){
                Enemy target = aliveEnemies.get(new Random().nextInt(aliveEnemies.size()));
                int damage = Math.max(0, getAttack() - target.getDefense());
                target.takeDamage(damage);
            }
        }
    }
}
