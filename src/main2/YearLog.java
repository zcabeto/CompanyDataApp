package main2;

import java.time.LocalDate;
import java.util.ArrayList;

public class YearLog {
    private final int DAYS = 5;
    private ArrayList<DayLog> previousDays = new ArrayList<>();
    private ArrayList<DayLog> comingDays = new ArrayList<>();
    private LocalDate currentDate;
    private DayLog today = new DayLog();
    // group types of data together for easier referencing...
    private ArrayList<String> data_types = new ArrayList<>();
    public YearLog(LocalDate date){
        currentDate = date;
        for (int i=0; i<DAYS; i++){
            previousDays.add(new DayLog(currentDate.minusDays(DAYS-i)));
            comingDays.add(new DayLog(currentDate.plusDays(i)));
        }
    }
    public YearLog(){
        this(LocalDate.now());
    }

    public ArrayList<Section> getDayInfo(LocalDate date){
        updateDayInfo();
        DayLog day;
        if (date.isBefore(currentDate)){        // previous days
            day = previousDays.get(currentDate.compareTo(date));
        } else {                                // current or following days
            day = comingDays.get(date.compareTo(currentDate));
        }
        return day.sections;
    }
    public ArrayList<Section> getTodayInfo(){
        ArrayList<Section> sections = new ArrayList<>(today.sections);
        sections.addAll(comingDays.get(0).sections);
        return sections;
    }


    private void updateDayInfo(){
        // calendar days are shifted
        DayLog prevToday = updateCalendar();
        if (prevToday==null) { return; }
        // today's information logged in calendar
        ArrayList<Section> todaySaved = new ArrayList<>(this.today.sections);
        todaySaved.removeIf(Section::logSection);
        prevToday.sections.addAll(todaySaved);
        // reset new day's information
        this.today.clearInfo();
    }
    private DayLog updateCalendar(){
        DayLog prevToday = comingDays.get(0);
        LocalDate now = LocalDate.now();
        if (currentDate.isEqual(now)){ return null; }
        LocalDate iterateDate = currentDate.minusDays(DAYS);
        // remove old days
        while (!iterateDate.isEqual(now.minusDays(DAYS))){
            previousDays.remove(0);
            iterateDate = iterateDate.plusDays(1);
        }
        // move future days into the past
        iterateDate = currentDate.minusDays(1);
        while (!iterateDate.isEqual(now.minusDays(1))){
            DayLog dayNowInPast = comingDays.remove(0);
            dayNowInPast.dayComplete();     // remove data that does not need storing
            previousDays.add(dayNowInPast);
            iterateDate = iterateDate.plusDays(1);
        }
        // add new days
        iterateDate = currentDate.plusDays(DAYS);
        while (!iterateDate.isEqual(now.plusDays(DAYS))){
            comingDays.add(new DayLog(iterateDate));
            iterateDate = iterateDate.plusDays(1);
        }
        currentDate = now;
        return prevToday;
    }
}
