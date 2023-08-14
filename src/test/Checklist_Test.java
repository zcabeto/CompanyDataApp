package test;
import main.*;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;

public class Checklist_Test {
    private final String list_name = "list_all";
    private final Checklist checklist = new Checklist(list_name);

    @Test
    public void nameIsSet(){
        assertEquals(checklist.getItemName(), list_name);
    }

    @Test
    public void listStartEmpty() { assertEquals(checklist.getItems(), new ArrayList<>()); }
}
