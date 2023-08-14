package test;
import main.*;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Checklist_Test {
    private final String list_name = "list_all";
    private final Checklist checklist = new Checklist(list_name);
    private final ListItem testItem = new ListItem("item");
    private final Checklist testList = new Checklist("list");

    @Test
    public void nameIsSet(){
        assertEquals(checklist.getItemName() , list_name);
    }

    @Test
    public void listStartEmpty() { assertEquals(checklist.getItems().size() , 0); }

    @Test
    public void addItemSuccessfully() {
        checklist.addItem(testItem);
        checklist.addItem(testList);
        assertEquals( checklist.getItems() , new ArrayList<>(List.of(new ListItem[]{testItem, testList})));
        checklist.removeItem(testItem);
        checklist.removeItem(testList);
    }

    @Test
    public void checklistEquivalentToArrayList() {
        checklist.addItem(testList);
        testList.addItem(testItem);
        assertEquals(checklist.getItems().size(),1);
        assertEquals(checklist.getItems().get(0), testList);
        assertEquals(testList.getItems().size(),1);
        assertEquals(testList.getItems().get(0), testItem);
        checklist.removeItem(testList);
        testList.removeItem(testItem);
    }

    @Test
    public void listCanBeChangedToItem(){
        checklist.addItem(testList);
        assert(checklist.getItems().get(0) instanceof Checklist);
        checklist.list_to_item(testList);
        assert(!(checklist.getItems().get(0) instanceof Checklist));
        checklist.removeItem(testList);
    }

    @Test
    public void itemCanBeChangedToList(){
        checklist.addItem(testItem);
        assert(!(checklist.getItems().get(0) instanceof Checklist));
        checklist.item_to_list(testItem);
        assert(checklist.getItems().get(0) instanceof Checklist);
        checklist.removeItem(testItem);
    }
}
