import { Metadata } from 'next'
import Link from 'next/link'
import {LogHandler,DayLog,LocalDate} from '/./src/app/main_classes/backend.tsx'

const log:LogHandler = new LogHandler();

export const metadata: Metadata = {
  title: 'CALENDAR'
}

export function whichPage() {
  return (
    <h1 className="text-3xl font-bold">
      <Link href={"/today"}>TODAY</Link>      <ins>CALENDAR</ins>
    </h1>
  )
}

function getDayLog(): DayLog {
  return log.getLog(true,null);
}

export default async function Page() {
  const day: DayLog = getDayLog();
  const key = day.toString();
  return (<>{whichPage()} {key}</>)
}