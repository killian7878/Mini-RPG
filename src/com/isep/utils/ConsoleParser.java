package com.isep.utils;

import com.isep.rpg.combatant.Combatant;
import com.isep.rpg.combatant.Enemy;
import com.isep.rpg.combatant.hero.*;
import com.isep.rpg.item.Armor;
import com.isep.rpg.item.Weapon;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleParser implements InputParser {
    static Scanner scanner = new Scanner(System.in);

    // Read a string from the console

    public static int readInt(String prompt, int userChoices) {
        int input;

        do {
            System.out.print(prompt);
            try {
                input = Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                input = -1;
            }
        } while (input < 1 || input > userChoices);
        return input;
    }

    public static void clearConsole(){
        for(int i = 0; i < 100; i++){
            System.out.println();
        }
    }

    public static void printSeparator(int n){
        for(int i = 0; i < n; i++){
            System.out.print("-");
        }
            System.out.println();
    }

    public static void printHeading(String title){
        printSeparator(30);
        System.out.println(title);
        printSeparator(30);
    }

    public static void anythingToContinue(){
        System.out.println("Veuillez entrer quelque chose pour continuer...");
        scanner.next();
    }

    public static int startConsoleGame(){
        clearConsole();
        printSeparator(40);
        printSeparator(30);
        System.out.println("Bienvenue dans MiniRPG!");
        System.out.println("RPG réalisé pour dans le cadre d'un apprentissage à l'isep");
        printSeparator(30);
        printSeparator(40);
        anythingToContinue();
        clearConsole();
        printHeading("Choix de l'interface");
        return readInt("1. Console\n2. Graphique\n -> ", 2);
    }

    public static String getHeroName(){
        printHeading("Veuillez entrer le nom de votre héros :");
        return scanner.next();
    }

    public static int getHeroClass(){
        printHeading("Veuillez choisir la classe de votre héros :");
        System.out.println("1. Warrior");
        System.out.println("2. Mage");
        System.out.println("3. Healer");
        System.out.println("4. Hunter");
        return readInt("-> ", 4);
    }

    @Override
    public ArrayList<Hero> chooseHeroes() {
        clearConsole();
        printHeading("Choix des héros");
        System.out.println("Veuillez choisir le nombre de héros que vous souhaitez avoir dans votre équipe (entre 1 et 7) :");
        int heroNumber = readInt("-> ", 7);
        ArrayList<Hero> heroes = new ArrayList<>();
        for(int i = 0; i < heroNumber; i++){
            boolean valid = false;
            Hero hero;
            do {
                clearConsole();
                printHeading("Caractéristiques du héros " + (i+1));
                System.out.println("Veuillez choisir le nom de votre héros ");
                System.out.println("1. Warrior");
                System.out.println("2. Mage");
                System.out.println("3. Healer");
                System.out.println("4. Hunter");
                int heroClass = readInt("-> ", 4);
                System.out.println("Veuillez choisir le nom de votre héros :");
                String heroName = scanner.next();
                switch (heroClass) {
                    case 4 ->
                            hero = new Hunter(heroName, 100, 10, 5, new Weapon("Bow", 7), new Armor("Armor", 1), 5, 10);
                    case 1 ->
                            hero = new Warrior(heroName, 100, 10, 10, new Weapon("Sword", 12), new Armor("Armor", 5), 5);
                    case 2 ->
                            hero = new Mage(heroName, 100, 10, 5, new Weapon("Wood", 5), new Armor("Armor", 1),  50, 10, 5);
                    case 3 ->
                            hero = new Healer(heroName, 100, 10, 5, new Weapon("Wood", 5), new Armor("Armor", 1), 50, 10, 5, 7);
                    default -> {
                        System.out.println("Classe non reconnue");
                        hero = new Hunter(heroName, 100, 10, 5, new Weapon("Bow", 7), new Armor("Armor", 1), 5, 10);
                    }
                }
                System.out.println("Vous avez choisi le héros suivant :");
                System.out.println("Nom: "+ hero.name + " "+"Type: " + hero.getClass().getSimpleName());
                System.out.println("Voulez-vous confirmer ce héros ? (1. Oui, 2. Non)");
                int choice = readInt("-> ", 2);
                if(choice == 1){
                    heroes.add(hero);
                    valid = true;
                }

            } while (!valid);

        }
        return heroes;
    }

    @Override
    public int getAction(Hero hero) {
        clearConsole();
        printHeading("Tour de " + hero.name + " " + hero.getClass().getSimpleName() + "\nVie: " + hero.getHealth() + "\nAttaque" + hero.getAttack() + "\nDéfense" + hero.getDefense());
        if (hero instanceof Spellcaster) {
            System.out.println("Mana: " + ((Spellcaster) hero).getMana());
        }else if(hero instanceof Hunter && ((Hunter) hero).getArrows() > 0){
            System.out.println("Arrows: " + ((Hunter) hero).getNumberOfArrows());
        }
        System.out.println("Veuillez choisir une action :");
        if (hero instanceof Healer) {
            System.out.println("1. Soigner un allié");
        }else {
            System.out.println("1. Attaquer");
        }
        System.out.println("2. Se défendre");
        System.out.println("3. Utiliser un objet");
        return readInt("-> ", 3);
    }

    @Override
    public int chooseConsumable(Hero hero){
        clearConsole();
        printHeading("Choix de l'objet");
        if (hero.getFood() == 0) {
            System.out.println("Vous n'avez pas de nourriture");
            return 0;
        } else {
            System.out.println("1. Nourriture");
        }
        if (hero instanceof Spellcaster && ((Spellcaster) hero).potion.size() > 0) {
            System.out.println("2. Potion de mana");
        }
        return readInt("-> ", hero instanceof Spellcaster ? 2 : 1);
    }

    @Override
    public void story(){
        clearConsole();
        printHeading("Histoire");
        System.out.println("Vous êtes un aventurier en quête de trésor.");
        System.out.println("Vous vous retrouvez dans une forêt sombre et dangereuse.");
        System.out.println("Vous allez devoir affronter les monstres de cette forêt pour récupérer le trésor.");
        anythingToContinue();
    }

    @Override
    public int chooseIncrease(Hero hero) {
        clearConsole();
        printHeading("Choix de l'amélioration");
        System.out.println("1. Augmenter la force");
        System.out.println("2. Augmenter la défense");
        System.out.println("3. Augmenter l'efficacité des potions et de la nourriture");
        System.out.println("4. Augmenter le nombre de potions et de nourriture");
        if (hero instanceof Hunter) {
            System.out.println("5. Nombre de flèches");
        } else if (hero instanceof Spellcaster) {
            System.out.println("5. Nombre de sorts");
            System.out.println("6. Coût des sorts");
        }
        return readInt("-> ", hero instanceof Hunter ? 5 : hero instanceof Spellcaster ? 6 : 4);
    }

    @Override
    public void displayStatus(ArrayList<Combatant> combatants) {
        clearConsole();
        printHeading("Statut des combattants");
        System.out.println("Héros :");
        for(Combatant combatant : combatants.stream().filter(c -> c instanceof Hero).toList()){
                System.out.println(combatant.name + " " + combatant.getClass().getSimpleName() + " : " + combatant.getHealth() + " PV" + (combatant instanceof Spellcaster ? " - " + ((Spellcaster) combatant).getMana() + " Mana" : "") + (combatant instanceof Hunter ? " - " + ((Hunter) combatant).getNumberOfArrows() + " Flèches" : ""));
        }

        System.out.println("\nMonstres :");
        for (Combatant combatant : combatants.stream().filter(c -> c instanceof Enemy).toList()) {
            System.out.println(combatant.name + " " + combatant.getClass().getSimpleName() + " : " + combatant.getHealth() + " PV" );
        }
        anythingToContinue();
    }

    @Override
    public void displayCombats(Combatant combatant) {

    }
}
