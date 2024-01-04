'use client'
import Link from 'next/link'
import {LogHandler,DayLog,LocalDate, Section} from './/../main_classes/backend.tsx'

const log:LogHandler = new LogHandler();
const today:DayLog = log.getLog(true,null);
today.sections.push(new Section("init",true,true));
export function whichPage(){
  return (
    <h1 className="text-3xl font-bold">
      <ins>TODAY</ins>      <Link href={"/calendar"}>CALENDAR</Link>
    </h1>
  )
}

export function addSectionExport(){
  // must create a new empty section and link to section page
  return (
    <div>
      <button
            onClick={() => {
              today.sections.push(new Section("name",true,true));
            }}
          >
          AddSection
      </button>
    </div>
  )
}
export function editSection(){
  // must link to section page with this section
}

export function getSections():string {
  var key:string = "";
  for (var i=0; i<today.sections.length; i++){
    key = key.concat(today.sections[i].getName());
  }
  return key
}

export default function Page(){
  return (<>{whichPage()} {addSectionExport()} {getSections()}</>)
}