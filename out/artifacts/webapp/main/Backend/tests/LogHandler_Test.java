package main.Backend.tests;

import main.Backend.CollectionLog.Section;
import main.Backend.InfoLog.*;
import main.Backend.LogHandler;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class LogHandler_Test {
    LogHandler log;
    Random random = new Random();
    LocalDate today = LocalDate.now();
    final int DAYS = 180;

    @Test
    public void dayLogsCorrespondByDate(){
        log = new LogHandler();
        LocalDate date = today.plusDays(random.nextInt(DAYS));
        assertEquals(log.getDayInfo(date).getDay(),date);
    }

    @Test
    public void calendarSectionsVisible(){
        log = new LogHandler();
        LocalDate date = today.plusDays(random.nextInt(1,DAYS));
        String name = "name";
        // visible in future
        log.addSectionCalendar(name,date,true);
        log.addSectionCalendar(name,date,false);
        assert(log.getDayInfo(date).sections.size()==2);
        assert(log.getDayInfo(date).sections.get(0).getName().equals(name));
        assert(log.getDayInfo(date).sections.get(0).logSection());  // works for true
        assert(!log.getDayInfo(date).sections.get(1).logSection()); // works for false

        // non-repeating today-sections are added to calendar too
        log.addSectionToday("name", true,false,true);
        log.addSectionToday("name", false,false,true);
        assert(log.getDayInfo(date).sections.size()==2);
        assert(log.getDayInfo(date).sections.get(0).getName().equals(name));
        assert(log.getDayInfo(date).sections.get(0).logSection());  // works for true
        assert(!log.getDayInfo(date).sections.get(1).logSection());  // works for false
    }

    @Test
    public void todaySectionsVisible(){
        log = new LogHandler();
        LocalDate date = today.plusDays(random.nextInt(1,DAYS));
        String name = "name";

        log.addSectionToday(name, true,true,true);
        log.addSectionToday(name, false,true,true);
        assert(log.getTodayInfo().sections.size()==2);
        assert(log.getTodayInfo().sections.get(0).getName().equals(name));
        assert(log.getTodayInfo().sections.get(0).logSection());  // works for true
        assert(!log.getTodayInfo().sections.get(1).logSection());  // works for false
    }

    @Test
    public void sectionsToLogCreatedCorrectly(){
        log = new LogHandler();
        log.addSectionToday("name", true,true,true);
        log.addSectionToday("name", false,true,true);

        assert(log.getTodayInfo().sections.size()==2);
        assert(log.getTodayInfo().sections.get(0).logSection());
        assert(!log.getTodayInfo().sections.get(1).logSection());
    }
    @Test
    public void sectionsToClearCreatedCorrectly(){
        log = new LogHandler();
        log.addSectionToday("name", false,true,true);
        log.addSectionToday("name", false,true,false);

        assert(log.getTodayInfo().sections.size()==2);
        assert(log.getTodayInfo().sections.get(0).clear());
        assert(!log.getTodayInfo().sections.get(1).clear());
    }
    @Test
    public void sectionsToRepeatInTodayOnly(){
        log = new LogHandler();
        log.addSectionToday("name1", true,false,true);
        log.addSectionToday("name2", true,true,true);

        assert(log.getTodayInfo().sections.size()==2);      // both in today
        assert(log.getDayInfo(today).sections.size()==1);   // repeating is not in calendar for today
        assert(log.getTodayInfo().sections.contains(log.getDayInfo(today).sections.get(0)));
    }

    @Test (expected = RuntimeException.class)
    public void inputArraysCannotBeDifferentLength(){
        log = new LogHandler();
        String[] names = new String[random.nextInt(1,5)];
        int[] types = new int[random.nextInt(5,10)];
        log.addItems(new Section("",true,true), names,types);
    }
    @Test (expected = RuntimeException.class)
    public void itemsMustBeOneOfThreeTypes(){
        log = new LogHandler();
        String[] names = {"failed-item"};
        int[] types = {4};
        Section section = new Section("section",true,true);
        log.addItems(section,names,types);
    }
    @Test
    public void itemsAddedCorrectlyTogether(){
        log = new LogHandler();
        String[] names = {"checkbox","data-store","sublist"};
        int[] types = {0,1,2};
        Section section = new Section("section",true,true);
        log.addItems(section,names,types);

        ArrayList<Item> items = section.getData();
        assert(items.size()==3);
        assert(items.get(0).getClass().equals(Checkbox.class) && items.get(0).getName().equals("checkbox"));
        assert(items.get(1).getClass().equals(DataStore.class) && items.get(1).getName().equals("data-store"));
        assert(items.get(2).getClass().equals(ItemSublist.class) && items.get(2).getName().equals("sublist"));
    }
}
