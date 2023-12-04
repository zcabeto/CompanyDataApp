import Section from 'Section.tsx'

class DayLog {
    private date: Date;
    public sections: Array<Section>;

    constructor(date: Date){
        this.date = date;
        this.sections = new Array;
    }
    getDate():Date {
        return this.date;
    }
    dayComplete():void {
        for (var i=0; i<this.sections.length; i++){
            if (!this.sections[i].logSection()){
                this.sections.splice(i, 1);
            }
        }
    }
    clearInfo():void{
        for (var i=0; i<this.sections.length; i++){
            this.sections[i].clear();
        }
    }
    toString():string {
        return this.date.toString();
    }
}

class YearLog {
    private DAYS: number;
    private previousDays: Array<DayLog>;
    private comingDays: Array<DayLog>;
    private currentDate: Date;
    private today: DayLog;

    constructor(){
        this.DAYS = 180;
        this.previousDays = new Array;
        this.comingDays = new Array;
        this.currentDate = new Date();
        this.today = new DayLog(this.currentDate);

        for (var i=1; i<this.DAYS; i++){
            this.previousDays.push(new DayLog(this.addDays(this.currentDate, -i)));
            this.comingDays.push(new DayLog(this.addDays(this.currentDate, i-1)));
        }
    }

    getDayInfo(date:Date){
        if (date < this.currentDate){
            // search previousDays then return
        } else {
            // search currentDays then return
        }
    }
    getTodayInfo():DayLog {
        // use a copy
        const todayTotal = new DayLog(new Date());
        for (var i=0; i<this.today.sections.length; i++){
            todayTotal.sections.push(this.today.sections[i]);
        }
        for (var i=0; i<this.comingDays[0].sections.length; i++){
            todayTotal.sections.push(this.comingDays[0].sections[i]);
        }
        return todayTotal;
    }
    addToDaily(section:Section):void {
        this.today.sections.push(section);
    }
    addToCalendar(section:Section, date:Date):void {
        if (date < this.currentDate){
            // search previousDays then push
        } else {
            // search currentDays then push
        }
    }

    updateDay():void {
        if (this.equalDates(this.currentDate, new Date())) {
            return;
        }
        for (let i=0; i<this.today.sections.length; i++){
            if (this.today.sections[i].logSection()){
                this.comingDays[0].sections.push(this.today.sections[i]);
            }
        }
        this.today.clearInfo();
        this.updateCalendar();
    }
    private updateCalendar():void {
        let day:DayLog;
        while (!this.equalDates(this.currentDate, new Date())){
            // move future to past as necessary
            day = this.comingDays[0];
            this.comingDays.splice(0,1);
            day.dayComplete();
            this.previousDays.push(day);
            // fix array lengths
            this.previousDays.splice(0,1);
            this.comingDays.push(new Section(this.addDays(this.currentDate, this.DAYS)));
            // iterate days
            this.currentDate = this.addDays(this.currentDate, 1);
        }
    }

    addDays(date:Date, i:number):Date {
        return new Date(date.getFullYear(), date.getMonth(), date.getDate()+i)
    }
    equalDates(d1:Date, d2:Date):boolean {
        if (d1.getDate()!=d2.getDate()){
            return false;
        }
        if (d1.getMonth()!=d2.getMonth()){
            return false;
        }
        if (d1.getFullYear()!=d2.getFullYear()){
            return false;
        }
        return true;
    }
}