package main;

import java.time.LocalDate;
import java.util.ArrayList;

public class DayLog
{
    private final int DAYS = 5;
    private ArrayList<EmbeddedList> previousDays = new ArrayList<>();
    private ArrayList<EmbeddedList> comingDays = new ArrayList<>();
    private LocalDate currentDate;
        // group types of data together for easier referencing...
    private ArrayList<String> data_types = new ArrayList<>();
    public DayLog(LocalDate date){
        currentDate = date;
        for (int i=0; i<DAYS; i++){
            previousDays.add(new EmbeddedList(currentDate.minusDays(DAYS-i).toString(),""));
            comingDays.add(new EmbeddedList(currentDate.plusDays(i).toString(),""));
        }
    }
    public DayLog(){
        this(LocalDate.now());
    }







    public ArrayList<EmbeddedList> getDataAll(){
        updateDates();
        ArrayList<EmbeddedList> allDays = new ArrayList<>(previousDays);
        allDays.addAll(comingDays);
        return allDays;
    }
    public EmbeddedList getDataToday(){
        updateDates();
        return comingDays.get(0);
    }

    private void updateDates(){
        LocalDate now = LocalDate.now();
        if (currentDate.isEqual(now)){ return; }
        LocalDate iterateDate = currentDate.minusDays(DAYS);
        // remove old days
        while (!iterateDate.isEqual(now.minusDays(DAYS))){
            previousDays.remove(0);
            iterateDate = iterateDate.plusDays(1);
        }
        // move future days into the past
        iterateDate = currentDate.minusDays(1);
        while (!iterateDate.isEqual(now.minusDays(1))){
            previousDays.add(comingDays.remove(0));
            iterateDate = iterateDate.plusDays(1);
        }
        // add new days
        iterateDate = currentDate.plusDays(DAYS);
        while (!iterateDate.isEqual(now.plusDays(DAYS))){
            comingDays.add(new EmbeddedList(iterateDate.toString(),""));
            iterateDate = iterateDate.plusDays(1);
        }
    }
}
