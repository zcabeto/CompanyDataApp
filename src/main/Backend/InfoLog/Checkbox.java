package main.Backend.InfoLog;


public class Checkbox extends Item {
    private Boolean isChecked = false;

    public Checkbox(String name) {
        super(name);
    }

    public Boolean getData(){
        return this.isChecked;
    }
    public void checkbox(){
        this.isChecked = !this.isChecked;
    }
    public void voidData() { this.isChecked = false; }
}
