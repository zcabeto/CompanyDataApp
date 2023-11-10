package main.Backend;

import main.Backend.CollectionLog.*;
import main.Backend.InfoLog.*;

import java.time.LocalDate;

public class LogHandler {
    private YearLog data = new YearLog();

    private Item addItemToSection(Section section, String itemName, int itemType){
        Item new_item;
        if (itemType==0){
            new_item = new Checkbox(itemName);
        } else if (itemType==1){
            new_item = new DataStore(itemName);
        } else if (itemType==2){
            new_item = new ItemSublist(itemName);
        } else {
            throw new RuntimeException("Input Argument's Insufficient");
        }
        section.addItem(new_item);
        return new_item;
    }
    public void addItems(Section section, String[] itemNames, int[] itemTypes){
        if (itemNames.length != itemTypes.length) {
            throw new RuntimeException("Input Argument's Insufficient");
        }
        for (int i=0; i<itemNames.length; i++){
            addItemToSection(section, itemNames[i], itemTypes[i]);
        }
    }

    public DayLog getDayInfo(LocalDate date){
        data.updateDay();
        return data.getDayInfo(date);
    }
    public DayLog getTodayInfo(){
        data.updateDay();
        return data.getTodayInfo();
    }

    public Section addSectionToday(String name, boolean saveInCalendar, boolean repeatDaily, boolean refreshDaily){
        data.updateDay();
        try {
            // saveInCalendar - true: eod save in previousDays, false: eod discard
            // repeatDaily - true: logged through TODAY but only needed in calendar
            if (!repeatDaily){
                return addSectionCalendar(name, LocalDate.now(), saveInCalendar);
                // non-repeating: treat as though entered through calendar
            }
            Section section = new Section(name, saveInCalendar, refreshDaily);
            data.addToDaily(section);
            return section;
        } catch (ArrayIndexOutOfBoundsException e){
            throw new RuntimeException("Input Argument's Insufficient");
        }
    }
    public Section addSectionCalendar(String name, LocalDate date, boolean saveInCalendar){
        data.updateDay();
        Section section = new Section(name, saveInCalendar, false);
        data.addToCalendar(section, date);
        return section;
    }
}
