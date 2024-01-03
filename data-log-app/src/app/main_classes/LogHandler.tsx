'use client'

import {DayLog, YearLog, LocalDate} from './DateLog.tsx'

export class LogHandler {
    // when more companies added, expand to static Array of YearLogs
    private static log: YearLog;    // static so all webpages use same dataset
    private DAYS: number;
    constructor() {
        this.DAYS = 180;
        if (!LogHandler.log) {              // if not yet initiated...
            LogHandler.log = new YearLog(this.DAYS);     // ...initiate log
        }
        LogHandler.log.updateDay();         // each time seen update days
    }

    // main interface for input (TODAY / CALENDAR & click date) and output (relevant DayLog)
    // edit DayLog on specialised DayLog page that has access to specific DayLog only
    // edit Sections on specialised Section page that has access to specific Section only (extension from DayLog page)

    getLog(isToday:boolean, date:LocalDate):DayLog {
        if (isToday) {
            // return combination of TODAY tab and CALENDAR entries for today
            return LogHandler.log.today.combineDayLogs(LogHandler.log.comingDays[0]);
        } else {
            // find date in YearLog's Arrays and return corresponding DayLog
            var todayDate = LogHandler.log.currentDate;
            if (todayDate.isBefore(date)){
                return LogHandler.log.comingDays[todayDate.daysUntil(date)];
            } else {
                return LogHandler.log.previousDays[this.DAYS - date.daysUntil(todayDate)]
            }
        }
    }
}

export {}