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
        } else { return null; }
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

    public DayLog getDayInfo(LocalDate date){ return data.getDayInfo(date); }
    public DayLog getTodayInfo(){ return data.getTodayInfo(); }

    public Section addSection(String name, boolean[] args){
        Section section;
        try {
            boolean isToday = args[0];          // true: logged via TODAY tab, false: logged via CALENDAR tab
            boolean saveInCalendar = args[1];   // true: eod save in previousDays, false: eod discard
            boolean repeatDaily = args[2];      // true: logged through TODAY but only needed in calendar
            if (!repeatDaily){
                return addSection(name, LocalDate.now(), args);
                // non-repeating: treat as though entered through calendar
            }
            boolean refreshDaily = args[3];
            section = new Section(name, saveInCalendar, refreshDaily);
            data.addToDaily(section);
            return section;
        } catch (ArrayIndexOutOfBoundsException e){
            throw new RuntimeException("Input Argument's Insufficient");
        }
    }
    public Section addSection(String name, LocalDate date, boolean[] args){
        Section section;
        try {
            boolean saveInCalendar = args[1];   // true: eod save in previousDays, false: eod discard
            section = new Section(name, saveInCalendar, false);
            data.addToCalendar(section, date);
            return section;
        } catch (ArrayIndexOutOfBoundsException e){
            throw new RuntimeException("Input Argument's Insufficient");
        }
    }
}
