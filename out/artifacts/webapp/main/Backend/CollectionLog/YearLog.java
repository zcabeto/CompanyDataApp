package main.Backend.CollectionLog;

import main.Backend.CollectionLog.DayLog;
import main.Backend.CollectionLog.Section;

import java.time.LocalDate;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;

public class YearLog {
    private final int DAYS = 180;
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

    public DayLog getDayInfo(LocalDate date){
        if (date.isBefore(currentDate)){        // previous days
            return previousDays.get(DAYS-(int) ChronoUnit.DAYS.between(date,currentDate));
        } else {                                // current or following days
            return comingDays.get((int) ChronoUnit.DAYS.between(currentDate,date));
        }
    }
    public DayLog getTodayInfo(){
        DayLog todayTotal = new DayLog(today);
        todayTotal.sections.addAll(comingDays.get(0).sections);
        return todayTotal;
    }
    public void addToDaily(Section section){
        today.sections.add(section);
    }
    public void addToCalendar(Section section, LocalDate date){
        if (date.isBefore(currentDate)){        // previous days
            if (ChronoUnit.DAYS.between(date,currentDate) > DAYS) {
                throw new RuntimeException("Date Out of Bounds");
            }
            previousDays.get(DAYS-(int) ChronoUnit.DAYS.between(date,currentDate)).sections.add(section);
        } else {
            if (ChronoUnit.DAYS.between(currentDate,date) > DAYS) {
                throw new RuntimeException("Date Out of Bounds");
            }// current or following days
            comingDays.get((int) ChronoUnit.DAYS.between(currentDate,date)).sections.add(section);
        }
    }

    public void updateDay(){
        if (currentDate.isEqual(LocalDate.now())) { return; }
        ArrayList<Section> save_today = new ArrayList<>(today.sections);
        save_today.removeIf(section -> !section.logSection());
        comingDays.get(0).sections.addAll(save_today);
        today.clearInfo();
        updateCal();
    }
    private void updateCal(){
        DayLog day;
        while (!currentDate.isEqual(LocalDate.now())){
            // move future to past as necessary
            day = comingDays.remove(0);
            day.dayComplete();
            previousDays.add(day);
            // fix array lengths
            previousDays.remove(0);
            comingDays.add(new DayLog(currentDate.plusDays(DAYS)));
            // iterate days
            currentDate = currentDate.plusDays(1);
        }
    }
}
