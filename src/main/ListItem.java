package main;

public class ListItem
{
    protected String itemName;
    protected int isChecked;        // -1 not checkable, 0 not done, 1 done

    public ListItem(String name){
        itemName = name;
    }
    public String getItemName(){
        return itemName;
    }
    public void setItemName(String name){
        itemName = name;
    }
    public void checkbox(){
        if (isChecked != -1) {
            isChecked = 1 - isChecked;
        }
    }

    @Override
    public String toString(){
        return itemName;
    }
}
