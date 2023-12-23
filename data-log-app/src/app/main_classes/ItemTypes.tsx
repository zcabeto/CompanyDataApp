abstract class Item{
    private name: string;
    private description: string;

    constructor(name: string){
        this.name = name;
        this.description = "";
    }
    getName():string{
        return this.name;
    }
    setName(name: string):void{
        this.name = name;
    }
    getDescription():string{
        return this.description;
    }
    setDescription(description: string):void{
        this.description = description;
    }
    abstract getData():Object;
    abstract voidData():void;
}

export class Checkbox extends Item{
    private isChecked: boolean;

    constructor(name: string){
        super(name);
        this.isChecked = false;
    }

    getData():boolean {
        return this.isChecked;
    }
    checkbox():void {
        this.isChecked = !this.isChecked;
    }
    voidData():void {
        this.isChecked = false;
    }
}

export class DataStore extends Item {
    private data: string;

    constructor(name: string){
        super(name);
        this.data = "";
    }

    getData():string {
        return this.data;
    }
    setData(data: string):void {
        this.data = data;
    }
    voidData():void {
        this.data = "";
    }
}

export class ItemSublist extends Item {
    private list: Array<Item>;

    constructor(name: string){
        super(name);
        this.list = new Array;
    }

    getData():Array<Item> {
        return this.list;
    }
    addItem(item: Item):void {
        this.list.push(item)
    }
    removeItem(item: Item):void {
        const index = this.list.indexOf(item, 0);
        if (index > -1) {
            this.list.splice(index, 1);
        }
    }
    voidData():void {
        for (var i=0; i<this.list.length; i++){
            this.list[i].voidData();
        }
    }
}

export {}