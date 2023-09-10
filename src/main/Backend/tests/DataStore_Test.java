package main.Backend.tests;

import main.Backend.InfoLog.Checkbox;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DataStore_Test {
    private Checkbox item;
    private String name = "item_name";
    private String description = "item_description";

    @Test
    public void constructorCorrectlySetsName(){
        item = new Checkbox(name);
        assertEquals(name,item.getName());
    }

    @Test
    public void setterCorrectlySetsName(){
        item = new Checkbox("");
        item.setName(name);
        assertEquals(name,item.getName());
    }

    @Test
    public void setterCorrectlySetsDescription(){
        item = new Checkbox(name);
        item.setDescription(description);
        assertEquals(description,item.getDescription());
    }
}
