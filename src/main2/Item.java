package main2;

abstract class Item {
    private String name;
    private String description;
    public Item(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String new_name) {
        this.name = new_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String new_description) {
        this.description = new_description;
    }

    abstract Object getData();      // retrieve info
    abstract void voidData();       // refresh info
}
