package main2;

import main2.Item;

public class Checkbox extends Item {
    private Boolean isChecked;

    public Boolean getData(){
        return this.isChecked;
    }
    public void checkbox(String data){
        this.isChecked = !this.isChecked;
    }
    public void voidData() { this.isChecked = false; }
}
