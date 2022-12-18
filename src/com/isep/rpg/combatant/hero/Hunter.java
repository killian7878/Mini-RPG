package com.isep.rpg.combatant.hero;

import com.isep.rpg.combatant.Enemy;
import com.isep.rpg.item.Armor;
import com.isep.rpg.item.Weapon;

import java.util.List;
import java.util.Random;

public class Hunter extends Hero {
    int numberOfArrows;
    public Hunter(String name, int health, int attack, int defense, Weapon weapon, Armor armor, int food, int numberOfArrows) {
        super(name, health, attack, defense, weapon, armor, food);
        this.numberOfArrows = numberOfArrows;
    }

    @Override
    public void attack(List<Enemy> enemies) {
        if (numberOfArrows > 0){
            numberOfArrows--;
            List<Enemy> aliveEnemies = enemies.stream().filter(Enemy::isAlive).toList();
            Enemy target = aliveEnemies.get(new Random().nextInt(aliveEnemies.size()));
            int damage = Math.max(0, getAttack() - target.getDefense());
            target.takeDamage(damage);
        }
    }

    public void increaseArrows(){
        numberOfArrows += 3;
    }

    public int getNumberOfArrows(){
        return numberOfArrows;
    }

    public int getArrows() {
        return numberOfArrows;
    }
}
