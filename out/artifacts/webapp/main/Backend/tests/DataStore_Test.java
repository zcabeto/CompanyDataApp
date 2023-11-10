package main.Backend.tests;

import main.Backend.InfoLog.DataStore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DataStore_Test {
    private DataStore item;
    private String name = "item_name";
    private String description = "item_description";
    private String data = "item_data";

    @Test
    public void constructorCorrectlySetsName(){
        item = new DataStore(name);
        assertEquals(name,item.getName());
    }

    @Test
    public void setterCorrectlySetsName(){
        item = new DataStore("");
        item.setName(name);
        assertEquals(name,item.getName());
    }

    @Test
    public void setterCorrectlySetsDescription(){
        item = new DataStore(name);
        item.setDescription(description);
        assertEquals(description,item.getDescription());
    }

    @Test
    public void defaultIsEmpty(){
        item = new DataStore(name);
        assertEquals(item.getData(),"");
    }

    @Test
    public void setDataWritesData(){
        item = new DataStore(name);
        item.setData(data);
        assertEquals(item.getData(),data);
    }

    @Test
    public void voidDataEmptiesData(){
        item = new DataStore(name);
        item.setData(data);
        item.voidData();
        assertEquals(item.getData(),"");
    }
}
