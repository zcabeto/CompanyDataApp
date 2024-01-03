import { Metadata } from 'next'
import Link from 'next/link'
import LogHandler from '/./src/app/main_classes/LogHandler.tsx'
import DayLog from '/./src/app/main_classes/DateLog.tsx'

const log = new LogHandler();
 
export const metadata: Metadata = {
  title: 'TODAY',
}

export function whichPage(){
  return (
    <h1 className="text-3xl font-bold">
      <ins>TODAY</ins>      <Link href={"/calendar"}>CALENDAR</Link>
    </h1>
  )
}

function getDayLog(): DayLog {
  return log.getLog(true, null);
}

export default async function Page() {
  const day: DayLog = getDayLog();
  const key = day.toString();
  return (<>{whichPage()} {key}</>)
}