import { Metadata } from 'next'
import Link from 'next/link'
import {Checkbox} from '/./src/app/main_classes/ItemTypes.tsx'
 
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

async function data(){
  const obj = new Checkbox();
  return obj.getName();
}

export default async function Page() {
  const obj = new Checkbox();
  const key = obj.getName();
  return (<>{whichPage()} {key}</>)
}