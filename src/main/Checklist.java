package main;

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
    public void list_to_item(Checklist list){
        int index = items.indexOf(list);
        if (index == -1){
            throw new RuntimeException("not a valid item");
        }
        items.set( index , new ListItem(list.getItemName()) );
    }
    public void item_to_list(ListItem item){
        int index = items.indexOf(item);
        if (index == -1){
            throw new RuntimeException("not a valid item");
        }
        items.set( index , new Checklist(item.getItemName()) );
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(itemName+"{");
        for (ListItem item : items){
            sb.append("\n").append(item);
        }
        sb.append("}");
        return sb.toString();
    }
}
