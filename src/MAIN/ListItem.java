package MAIN;

public class ListItem
{
    private String itemName;
    private boolean isChecked;

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
        isChecked = !isChecked;
    }

    @Override
    public String toString(){
        return itemName;
    }
}
