package com.isep.rpg.item;

public abstract class Item {
    public String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
