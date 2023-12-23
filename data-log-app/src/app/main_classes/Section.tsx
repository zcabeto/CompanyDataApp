import ItemSublist from 'ItemTypes.tsx'

export class Section extends ItemSublist {
    private isLogged: boolean;
    private clearSection: boolean;

    constructor(name:string, isLogged:boolean, clearSection:boolean){
        super(name);
        this.isLogged = isLogged;
        this.clearSection = clearSection;
    }
    logSection():boolean {
        return this.isLogged;
    }
    clear():boolean {
        if (this.clearSection){
            voidData();
            return true;
        }
        return false;
    }
}