package main.Backend.tests;

import main.Backend.CollectionLog.*;

import main.Backend.InfoLog.Checkbox;
import main.Backend.InfoLog.DataStore;
import org.junit.Test;
import static org.junit.Assert.*;

public class Section_Test {

    // all ItemSublist tests already apply
    private Section section;
    private String name = "section_name";

    @Test
    public void constructorSetsLogTrueCorrectly(){
        section = new Section(name, true,true);
        assert(section.logSection());
    }
    @Test
    public void constructorSetsLogFalseCorrectly(){
        section = new Section(name, false,true);
        assert(!section.logSection());
    }

    @Test
    public void constructorSetsClearTrueCorrectly(){
        section = new Section(name, true,true);
        Checkbox item1 = new Checkbox(name);
        DataStore item2 = new DataStore(name);
        item1.checkbox();
        item2.setData("input");
        section.addItem(item1);
        section.addItem(item2);

        assert(section.clear());
        assert(!item1.getData());
        assertEquals(item2.getData(),"");
    }

    @Test
    public void constructorSetsClearFalseCorrectly(){
        section = new Section(name, true,false);
        Checkbox item1 = new Checkbox(name);
        DataStore item2 = new DataStore(name);
        item1.checkbox();
        item2.setData("input");
        section.addItem(item1);
        section.addItem(item2);

        assert(!section.clear());
        assert(item1.getData());
        assertEquals(item2.getData(),"input");
    }
}
