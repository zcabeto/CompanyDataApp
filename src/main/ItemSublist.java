package main;

import java.util.ArrayList;

public class ItemSublist extends Item {
    private ArrayList<Item> items;

    public ArrayList<Item> getData() {
        return this.items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }
    public void removeItem(Item item) {
        this.items.remove(item);
    }
    public Item getItem(int index) {
        return this.items.get(index);
    }

    public void voidData() {
        for (Item item : this.items){
            item.voidData();
        }
    }
}
