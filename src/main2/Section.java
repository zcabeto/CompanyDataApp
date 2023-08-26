package main2;

public class Section extends ItemSublist
{
    private boolean logSection;     // should the section still be recorded once the day has passed?
    private boolean clearSection;   // if TODAY, may want to empty information

    public boolean logSection(){
        return logSection;     // return false if section is to be deleted
    }
    public void clear(){
        if (clearSection){
            voidData();
        }
    }
}
