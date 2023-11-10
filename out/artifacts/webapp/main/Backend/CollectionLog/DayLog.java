package main.Backend.CollectionLog;

import java.time.LocalDate;
import java.util.ArrayList;

public class DayLog {
    private LocalDate day;
    public ArrayList<Section> sections = new ArrayList<>();

    public DayLog(LocalDate day){
        this.day = day;
    }
    public DayLog(){
        this.day = LocalDate.now();
    }
    public DayLog(DayLog day){
        // copy another log information
        this.sections = day.sections;
    }
    public LocalDate getDay(){
        return day;
    }

    public void dayComplete(){
        this.sections.removeIf(section -> !section.logSection());
    }
    public void clearInfo(){
        for (Section section : this.sections){
            section.clear();
        }
    }
    public String toString(){
        return this.day.toString();
    }
}
