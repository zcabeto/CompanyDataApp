import Section from 'Section.tsx'

export class DayLog {
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
    combineDayLogs(day:DayLog){
        var newLog = new DayLog(this.date);
        for (var i=0; i<this.sections.length; i++){
            newLog.sections.push(this.sections[i]);
        }
        for (var i=0; i<day.sections.length; i++){
            newLog.sections.push(day.sections[i]);
        }
        return newLog;
    }
}

export class YearLog {
    private DAYS: number;
    private currentDate: Date;
    // following not public so only accessed directly by LogHandler
    previousDays: Array<DayLog>;        
    comingDays: Array<DayLog>;
    today: DayLog;

    constructor(DAYS:number){
        this.DAYS = DAYS;
        this.previousDays = new Array;
        this.comingDays = new Array;
        this.today = new DayLog(this.currentDate);

        for (var i=1; i<this.DAYS; i++){
            this.previousDays.push(new DayLog(this.addDays(this.currentDate, -i)));
            this.comingDays.push(new DayLog(this.addDays(this.currentDate, i-1)));
        }
    }

    addToDaily(section:Section):void {
        this.today.sections.push(section);
    }
    addToCalendar(section:Section, log:DayLog):void {
        log.sections.push(section);
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
}

    }
        }
        }
        }
    }
}