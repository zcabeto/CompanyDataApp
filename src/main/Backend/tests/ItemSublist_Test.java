package main.Backend.tests;

import main.Backend.InfoLog.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ItemSublist_Test {
    private ItemSublist item;
    private String name = "item_name";
    private String description = "item_description";

    @Test
    public void constructorCorrectlySetsName(){
        item = new ItemSublist(name);
        assertEquals(name,item.getName());
    }

    @Test
    public void setterCorrectlySetsName(){
        item = new ItemSublist("");
        item.setName(name);
        assertEquals(name,item.getName());
    }

    @Test
    public void setterCorrectlySetsDescription(){
        item = new ItemSublist(name);
        item.setDescription(description);
        assertEquals(description,item.getDescription());
    }

    @Test
    public void ItemListBeginsEmpty(){
        item = new ItemSublist(name);
        assertEquals(item.getData(), new ArrayList<>());
    }

    @Test
    public void worksAsArrayList(){
        item = new ItemSublist(name);
        ArrayList<Item> list = new ArrayList<>();
        Item new_item = new Checkbox(name);
        list.add(new_item);
        item.addItem(new_item);
        assertEquals(item.getData(), list);
    }

    @Test
    public void voidDataVoidsItemsNotList(){
        item = new ItemSublist(name);
        ArrayList<Item> list = new ArrayList<>();
        Checkbox checkbox = new Checkbox(name);
        list.add(checkbox);
        item.addItem(checkbox);
        checkbox.checkbox();    // sets to true

        item.voidData();
        assertNotEquals(item.getData(), new ArrayList<>());
        assert(!checkbox.getData());
    }
}
