package main.Backend.CollectionLog;

import main.Backend.InfoLog.Item;
import main.Backend.InfoLog.ItemSublist;

import java.util.ArrayList;

public class Section extends ItemSublist
{
    private boolean logSection;     // should the section still be recorded once the day has passed?
    private boolean clearSection;   // if TODAY, may want to empty information
    public Section(String name, boolean logSection, boolean clearSection){
        super(name);
        this.logSection = logSection;
        this.clearSection = clearSection;
    }

    public boolean logSection(){
        return this.logSection;     // return false if section is to be deleted
    }
    public boolean clear(){
        if (this.clearSection){
            voidData();
            return true;
        }
        return false;
    }
}
