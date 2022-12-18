package com.isep.utils;

import com.isep.rpg.combatant.Combatant;
import com.isep.rpg.combatant.hero.Hero;

import java.util.ArrayList;

public interface InputParser {
    public ArrayList<Hero> chooseHeroes();

    public void story();

    public int getAction(Hero hero);

    public int chooseConsumable(Hero hero);

    public int chooseIncrease(Hero hero);

    public void displayStatus(ArrayList<Combatant> combatants);

    public void displayCombats(Combatant combatant);
}
