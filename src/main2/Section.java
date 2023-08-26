package main2;

public class Section extends ItemSublist
{
    private boolean keepData;       // should the system retain the data input once the day has passed?
    private boolean keepSection;    // should the section still be recorded once the day has passed?

    public Section(String name) {
        super(name);
    }

    public void resetData(){
        if (!keepData){
            voidData();
        }
    }
    public boolean logSection(){
        return keepSection;
    }
}
