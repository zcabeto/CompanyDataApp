'use client'
import {usePathname} from 'next/navigation'

export default function Page() {
  const path = usePathname()
  const date = path.slice(path.lastIndexOf('/')+1)

  return <h1>Details of date {date}</h1>
}