package main.Backend.tests;

import main.Backend.CollectionLog.DayLog;
import main.Backend.CollectionLog.Section;
import main.Backend.CollectionLog.YearLog;
import main.Backend.InfoLog.Checkbox;
import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Random;

public class Log_Test {
    YearLog calendar;
    LocalDate today = LocalDate.now();
    Random random = new Random();

    @Test
    public void storeDataInRangeOfToday(){
        calendar = new YearLog();
        assert(calendar.getTodayInfo()!=null);
        assert(calendar.getDayInfo(today.plusDays(random.nextInt(180)))!=null);
        assert(calendar.getDayInfo(today.minusDays(random.nextInt(180)))!=null);
    }

    @Test
    public void dayLogsCorrespondByDate(){
        calendar = new YearLog();
        LocalDate date = today.plusDays(random.nextInt(180));
        assertEquals(calendar.getDayInfo(date).getDay(),date);
    }

    @Test
    public void calendarSectionsVisible(){
        calendar = new YearLog();
        LocalDate date = today.plusDays(random.nextInt(180));
        Section section = new Section("name",true,true);
        // visible in future
        calendar.addToCalendar(section,date);
        assert(calendar.getDayInfo(date).sections.contains(section));
        // visible today
        calendar.addToCalendar(section,today);
        assert(calendar.getTodayInfo().sections.contains(section));
    }
    @Test
    public void calendarInfoVisibleViaTodayWhenReached(){
        LocalDate date = today.minusDays(random.nextInt(1,180));  // centred on random day
        calendar = new YearLog(date);
        Section section = new Section("name",true,true);

        calendar.addToCalendar(section,today);  // added to day that should be center
        calendar.updateDay();                   // updated to centre the day
        assert(calendar.getTodayInfo().sections.contains(section));

    }
    @Test
    public void dayShiftRemoveNonLoggedData(){
        LocalDate date = today.minusDays(random.nextInt(1,180));
        calendar = new YearLog(date);
        Section sectionKeep = new Section("name",true,true);
        Section sectionRemove = new Section("name",false,true);
        calendar.addToCalendar(sectionKeep,date);
        calendar.addToCalendar(sectionRemove,date);
        assert(calendar.getDayInfo(date).sections.contains(sectionKeep));   // check presence
        assert(calendar.getDayInfo(date).sections.contains(sectionRemove));

        calendar.updateDay();   // move into past
        assert(calendar.getDayInfo(date).sections.contains(sectionKeep));
        assert(!calendar.getDayInfo(date).sections.contains(sectionRemove));
    }
    @Test
    public void dataResetEachDay(){
        LocalDate date = today.minusDays(random.nextInt(1,180));
        calendar = new YearLog(date);
        Checkbox boxKeep = new Checkbox("name");
        boxKeep.checkbox();
        Checkbox boxClear = new Checkbox("name");
        boxClear.checkbox();

        Section section1 = new Section("name",true,false);
        Section section2 = new Section("name", true, true);
        section1.addItem(boxKeep); section2.addItem(boxClear);
        calendar.addToDaily(section1); calendar.addToDaily(section2);
        calendar.updateDay();
        DayLog today = calendar.getTodayInfo();
        assert(today.sections.size()==2);                   // should be two sections of one item each
        assert(today.sections.get(0).getData().size()==1 && today.sections.get(1).getData().size()==1);

        Checkbox box1 = (Checkbox) today.sections.get(0).getData().get(0);
        Checkbox box2 = (Checkbox) today.sections.get(1).getData().get(0);
        assert(box1.getData() && !box2.getData());          // first should be checked, second voided
    }
}
