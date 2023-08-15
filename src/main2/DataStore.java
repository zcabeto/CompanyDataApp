package main2;

import main2.Item;

public class DataStore extends Item {
    private String inputData;
    public String getData() {
        return this.inputData;
    }

    public void voidData() {
        this.inputData = "";
    }

    public void setData(String data) {
        this.inputData = data;
    }
}
