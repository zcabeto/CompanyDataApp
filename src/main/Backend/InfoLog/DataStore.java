package main.Backend.InfoLog;

public class DataStore extends Item {
    private String inputData = "";

    public DataStore(String name) {
        super(name);
    }

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
