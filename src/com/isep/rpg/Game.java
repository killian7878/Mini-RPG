package com.isep.rpg;


import com.isep.rpg.combatant.Combatant;
import com.isep.rpg.combatant.Enemy;
import com.isep.rpg.combatant.hero.*;
import com.isep.utils.ConsoleParser;

import java.util.Scanner;

import java.util.*;

import com.isep.utils.GUI.GUIParser;

public class Game {

    ConsoleParser consoleParser = new ConsoleParser();
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<Combatant> combatants = new ArrayList<>();
    private int fightRemaining = 5;

    public Game() {}

    public ArrayList<Hero> getHeroes(){
        ArrayList<Hero> heroes = new ArrayList<>();
        for (Combatant combatant : combatants) {
            if (combatant instanceof Hero) {
                heroes.add((Hero) combatant);
            }
        }
        return heroes;
    }

    public ArrayList<Hero> getHeroesAlive(){
        ArrayList<Hero> heroes = new ArrayList<>();
        for (Combatant combatant : combatants) {
            if (combatant instanceof Hero && combatant.isAlive()) {
                heroes.add((Hero) combatant);
            }
        }
        return heroes;
    }
    public ArrayList<Enemy> getEnemies(){
        ArrayList<Enemy> enemies = new ArrayList<>();
        for (Combatant combatant : combatants) {
            if (combatant instanceof Enemy) {
                enemies.add((Enemy) combatant);
            }
        }
        return enemies;
    }

    public ArrayList<Enemy> getEnemiesAlive(){
        ArrayList<Enemy> enemies = new ArrayList<>();
        for (Combatant combatant : combatants) {
            if (combatant instanceof Enemy && combatant.isAlive()) {
                enemies.add((Enemy) combatant);
            }
        }
        return enemies;
    }
    public void chooseHeroes(){
        combatants.addAll(consoleParser.chooseHeroes());
    }

    public void startFight(){
        int enemyNum = combatants.size();
        if (fightRemaining == 1){
            ConsoleParser.printHeading("The final fight");
            combatants.add(new Enemy("Sauron", 200, 20, 3));
        }else {
            for (int i = 0; i < enemyNum; i++) {
                combatants.add(new Enemy("Enemy " + i, 100, 10, 3));
            }
        }
        Collections.shuffle(combatants);
        while(getEnemiesAlive().size() > 0 && getHeroesAlive().size() > 0){
            consoleParser.displayStatus(combatants);
            for (Combatant combatant : combatants) {
                if(!combatant.isAlive()){
                    continue;
                }
                if (combatant instanceof Hero) {
                    int action = consoleParser.getAction((Hero) combatant);
                    switch (action) {
                        case 1 -> {
                            if (combatant instanceof Healer){
                                ((Healer) combatant).heal(getHeroesAlive());
                            } else {
                                ((Hero) combatant).attack(getEnemies());
                            }
                        }
                        case 2 -> ((Hero) combatant).defend();
                        case 3 -> {
                            int item =consoleParser.chooseConsumable((Hero) combatant);
                            if (item == 0) {
                                continue;
                            }
                            switch (item) {
                                case 1 -> ((Hero) combatant).eat();
                                case 2 -> {
                                    assert combatant instanceof Spellcaster;
                                    ((Spellcaster) combatant).drink();
                                }
                            }
                        }
                    }
                } else {
                    ((Enemy) combatant).attack(getHeroes());
                }
            }

            for (Combatant combatant : combatants) {
                if (combatant instanceof Hero) {
                    ((Hero) combatant).resetDefense();
                }
            }
        }
            if (getHeroes().stream().noneMatch(Hero::isAlive)){
                ConsoleParser.printHeading("Vous avez perdu !");
            }else {
                ConsoleParser.printHeading("Vous avez gagné !");
                for (Hero hero : getHeroes()) {
                    if (!hero.isAlive()) {
                        break;
                    }
                    int choice = consoleParser.chooseIncrease(hero);
                    switch (choice) {
                        case 1 -> hero.increaseDamage();
                        case 2 -> hero.increaseDefense();
                        case 3 -> hero.increaseConsumableEfficiency();
                        case 4 -> hero.addConsumables();
                        case 5 -> {
                            if (hero instanceof Hunter) {
                                ((Hunter) hero).increaseArrows();
                            } else if (hero instanceof Spellcaster) {
                                ((Spellcaster) hero).increaseSpellEfficiency();
                            }
                        }
                        case 6 -> {
                            if (hero instanceof Spellcaster) {
                                ((Spellcaster) hero).reduceSpellCost();
                            }
                        }
                    }
                }
                combatants.removeAll(getEnemies());
                fightRemaining--;
            }
    }

    public void displayFightStatus(){
        System.out.println("Il reste " + fightRemaining + " combats");
        System.out.println("État des héros :");
        for (Combatant combatant : combatants) {
            if (combatant instanceof Hero) {
                System.out.println("Héro: " + combatant.getName() + " - " + combatant.getHealth() + " PV" + " - " + ((Hero) combatant).getFood() + " nourriture" + " - " + ((Hero) combatant).getWeapon() + " dégâts" + " - " + ((Hero) combatant).getArmor() + " défense");
                if (combatant instanceof Spellcaster) {
                    System.out.println(((Spellcaster) combatant).getMana() + " mana");
                }
                if (combatant instanceof Healer) {
                    System.out.println("Healer");
                    System.out.println(((Healer) combatant).healPower + " power");
                }
                if (combatant instanceof Hunter) {
                    System.out.println("Hunter");
                    System.out.println(((Hunter) combatant).getNumberOfArrows() + " flèches");
                }
                if (combatant instanceof Mage){
                    System.out.println("Mage");
                }
                if (combatant instanceof Warrior){
                    System.out.println("Warrior");
                }
            }
        }
        System.out.println("Les ennemis sont :");
        for (Combatant combatant : combatants) {
            if (combatant instanceof Enemy) {
                System.out.println("Enemy : " + combatant.getName() + " - " + combatant.getHealth() + " PV" );
            }
        }
    }

    public void play() {
        int chosenParser = ConsoleParser.startConsoleGame();
        if (chosenParser == 2){
            GUIParser.startGUI();
        } else {
            consoleParser.story();
            chooseHeroes();
            while (fightRemaining > 0) {
                startFight();
                if (getHeroes().stream().noneMatch(Hero::isAlive)){
                    break;
                }
            }
            if (!getHeroes().stream().noneMatch(Hero::isAlive)) {
                ConsoleParser.printHeading("Vous avez gagné la partie !");
            }
        }
    }
}
