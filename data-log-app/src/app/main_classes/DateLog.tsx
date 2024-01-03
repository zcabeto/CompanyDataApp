import Section from 'Section.tsx'

export class DayLog {
    private date: LocalDate;
    public sections: Array<Section>;

    constructor(date: LocalDate){
        this.date = date;
        this.sections = new Array;
    }
    getDate():LocalDate {
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
    private currentDate: LocalDate;
    // following not public so only accessed directly by LogHandler
    previousDays: Array<DayLog>;        
    comingDays: Array<DayLog>;
    today: DayLog;

    constructor(DAYS:number){
        this.DAYS = DAYS;
        this.previousDays = new Array;
        this.comingDays = new Array;
        var tempDate = new Date();
        this.currentDate = new LocalDate(tempDate.getDay(), tempDate.getMonth(), tempDate.getFullYear());
        this.today = new DayLog(this.currentDate);
        for (var i=1; i<this.DAYS; i++){       // store this.DAYS days surrounding today
            this.previousDays.push(new DayLog(this.currentDate.subDays(i)));
            this.comingDays.push(new DayLog(this.currentDate.addDays(i-1)));
        }
    }

    addToDaily(section:Section):void {
        this.today.sections.push(section);
    }
    addToCalendar(section:Section, log:DayLog):void {
        log.sections.push(section);
    }

    updateDay():void {
        var tempDate = new Date();
        if (this.currentDate.isEqual(new LocalDate(tempDate.getDay(), tempDate.getMonth(), tempDate.getFullYear()))) {
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
        var tempDate = new Date();
        while (!this.currentDate.isEqual(new LocalDate(tempDate.getDay(), tempDate.getMonth(), tempDate.getFullYear()))){
            // move future to past as necessary
            day = this.comingDays[0];
            this.comingDays.splice(0,1);
            day.dayComplete();
            this.previousDays.push(day);
            // fix array lengths
            this.previousDays.splice(0,1);
            this.comingDays.push(new Section(this.currentDate.addDays(this.DAYS)));
            // iterate days
            this.currentDate = this.currentDate.addDays(1);
        }
    }
}


export class LocalDate {
    day: number;
    month: number;
    year: number;
    dayOfYear: number;
    constructor(day:number, month:number, year:number) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.dayOfYear = this.dateToIndex();
    }

    public addDays(n:number):LocalDate {
        var endOfYear:LocalDate = new LocalDate(31,12,this.year);
        var daysToEOY = endOfYear.dayOfYear-this.dayOfYear;
        if (daysToEOY > n) {    // found date is in same year
            return this.indexToDate(this.dayOfYear+n, this.year);
        } else {
            return this.indexToDate(n - daysToEOY, this.year+1);
        }
    }
    public subDays(n:number):LocalDate {
        if (this.dayOfYear > n) {    // found date is in same year
            return this.indexToDate(this.dayOfYear-n, this.year);
        } else {
            return this.indexToDate(n - this.dayOfYear, this.year-1);
        }
    }
    public daysUntil(date:LocalDate):number {
        if (this.year == date.year) {
            return date.dateToIndex() - this.dateToIndex();
        } else {
            var endOfYear:LocalDate = new LocalDate(31,12,this.year);
            return date.dateToIndex() + endOfYear.dayOfYear - this.dateToIndex();
        }
    }
    public isBefore(date:LocalDate):boolean {
        if (this.year == date.year){
            return this.dayOfYear <= date.dayOfYear;
        }
        return this.year < date.year;
    }
    public isEqual(date:LocalDate):boolean {
        if (this.dayOfYear==date.dayOfYear && this.year == date.year) {
            return true;
        }
        return false;
    }

    private indexToDate(index:number, year:number):LocalDate {
        var month = 0;
        if (index > 31){                    // January
            index -= 31;
            month += 1;
        }
        if (index > 29 && (year % 4==0)){   // February
            index -= 29;
            month += 1;
        } else if (index > 28 && (year % 4!=0)){
            index -= 28;
            month += 1;
        }
        if (index > 31){                    // March
            index -= 31;
            month += 1;
        }
        if (index > 30){                    // April
            index -= 30;
            month += 1;
        }
        if (index > 31){                    // May
            index -= 31;
            month += 1;
        }
        if (index > 30){                    // June
            index -= 31;
            month += 1;
        }
        if (index > 31){                    // July
            index -= 31;
            month += 1;
        }
        if (index > 31){                    // August
            index -= 31;
            month += 1;
        }
        if (index > 30){                    // September
            index -= 31;
            month += 1;
        }
        if (index > 31){                    // October
            index -= 31;
            month += 1;
        }
        if (index > 30){                    // November
            index -= 31;
            month += 1;
        }
        return new LocalDate(index, month, year);
    }

    private dateToIndex():number {
        var index:number = 0
        if (this.month > 1) index += 31;
        if (this.month > 2) {
            if (this.year % 4 == 0) index += 29;
            else index += 28;
        }
        if (this.month > 3) index += 31;
        if (this.month > 4) index += 30;
        if (this.month > 5) index += 31;
        if (this.month > 6) index += 30;
        if (this.month > 7) index += 31;
        if (this.month > 8) index += 31;
        if (this.month > 9) index += 30;
        if (this.month > 10) index += 31;
        if (this.month > 11) index += 30;
        return index + this.day;
    }
}