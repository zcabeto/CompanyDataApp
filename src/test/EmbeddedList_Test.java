package test;

import org.junit.Test;

public class EmbeddedList_Test {
    /*private final String list_name = "list_all";
    private final ItemSublist embeddedList = new ItemSublist();
    private final Item testItem = new Item();
    private final ItemSublist testList = new ItemSublist();

    @Test
    public void nameIsSet(){
        assertEquals(embeddedList.getItemName() , list_name);
    }

    @Test
    public void listStartEmpty() { assertEquals(embeddedList.getItems().size() , 0); }

    @Test
    public void addItemSuccessfully() {
        embeddedList.addItem(testItem);
        embeddedList.addItem(testList);
        assertEquals( embeddedList.getItems() , new ArrayList<>(List.of(new ListItem[]{testItem, testList})));
        embeddedList.removeItem(testItem);
        embeddedList.removeItem(testList);
    }

    @Test
    public void checklistEquivalentToArrayList() {
        embeddedList.addItem(testList);
        testList.addItem(testItem);
        assertEquals(embeddedList.getItems().size(),1);
        assertEquals(embeddedList.getItems().get(0), testList);
        assertEquals(testList.getItems().size(),1);
        assertEquals(testList.getItems().get(0), testItem);
        embeddedList.removeItem(testList);
        testList.removeItem(testItem);
    }

    @Test
    public void listCanBeChangedToItem(){
        embeddedList.addItem(testList);
        assert(embeddedList.getItems().get(0) instanceof EmbeddedList);
        embeddedList.list_to_item(testList);
        assert(!(embeddedList.getItems().get(0) instanceof EmbeddedList));
        embeddedList.removeItem(testList);
    }

    @Test
    public void itemCanBeChangedToList(){
        embeddedList.addItem(testItem);
        assert(!(embeddedList.getItems().get(0) instanceof EmbeddedList));
        embeddedList.item_to_list(testItem);
        assert(embeddedList.getItems().get(0) instanceof EmbeddedList);
        embeddedList.removeItem(testItem);
    }*/
}
