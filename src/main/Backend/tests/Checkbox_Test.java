package main.Backend.tests;

import main.Backend.InfoLog.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class Checkbox_Test {
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

    @Test
    public void defaultIsFalse(){
        item = new Checkbox(name);
        assert(!item.getData());
    }

    @Test
    public void checkboxMakesTrue(){
        item = new Checkbox(name);
        item.checkbox();
        assert(item.getData());
    }

    @Test
    public void checkboxTwiceMakesFalse(){
        item = new Checkbox(name);
        item.checkbox();
        item.checkbox();
        assert(!item.getData());
    }

    @Test
    public void voidDataMakesFalse(){
        item = new Checkbox(name);
        item.checkbox();
        item.voidData();
        assert(!item.getData());
    }
}
