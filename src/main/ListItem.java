package main;

public class ListItem
{
    protected String itemName;
    protected int isChecked;        // -1 not checkable, 0 not done, 1 done
    protected String information;
    protected String datatype;

    public ListItem(String name, String type){
        itemName = name;
        datatype = type;
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

    public void setInfo(String info){
        information = info;
    }
    public String getInfo(){
        return information;
    }

    @Override
    public String toString(){
        return itemName;
    }
}
