package main.Backend.InfoLog;

import java.util.ArrayList;

public class ItemSublist extends Item {
    protected ArrayList<Item> items = new ArrayList<>();

    public ItemSublist(String name) {
        super(name);
    }

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
