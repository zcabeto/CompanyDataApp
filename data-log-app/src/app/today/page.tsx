import { Metadata } from 'next'
import Link from 'next/link'
import {Item, Checkbox, DataStore, ItemSublist} from '/./src/app/main_classes/ItemTypes.tsx'
 
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

function data(): Item {
  return new Checkbox("I_AM_A_CHECKBOX");
}

export default async function Page() {
  const obj: Item = data();
  const key = obj.getName();
  return (<>{whichPage()} {key}</>)
}