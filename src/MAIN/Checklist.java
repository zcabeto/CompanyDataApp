package MAIN;

import java.util.ArrayList;

public class Checklist extends ListItem
{
    private final ArrayList<ListItem> items = new ArrayList<>();
    public Checklist(String name){
        super(name);
    }

    public void addItem(ListItem new_item){
        items.add(new_item);
    }
    public void removeItem(ListItem old_item){
        items.remove(old_item);
    }
    public ArrayList<ListItem> getItems(){ return items; }
    public void list_to_item(Checklist inner_list){
        items.set( items.indexOf(inner_list) , new ListItem(inner_list.getItemName()) );
    }
    public void item_to_list(ListItem inner_item){
        items.set( items.indexOf(inner_item) , new Checklist(inner_item.getItemName()) );
    }

    @Override
    public String toString(){
        return items.toString();
    }
}
