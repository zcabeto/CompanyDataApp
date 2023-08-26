package main2;

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

    public void dayComplete(){
        this.sections.removeIf(section -> !section.logSection());
    }
    public void clearInfo(){
        for (Section section : this.sections){
            section.clear();
        }
    }
}
