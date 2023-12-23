import { Metadata } from 'next'
import Link from 'next/link'
import {Item, Checkbox, DataStore, ItemSublist} from '/./src/app/main_classes/ItemTypes.tsx'
 
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

function data(): Item {
  return new DataStore("I_AM_A_CHECKBOX");
}

export default async function Page() {
  const obj: Item = data();
  const key = obj.getName();
  return (<>{whichPage()} {key}</>)
}