"use client"

import ModeToggle from "./modeToggle"
import { LucideIcon, HomeIcon, ScanBarcode, ArrowLeftRight } from 'lucide-react'
import Link from "next/link"
import { usePathname } from "next/navigation"

export default function Navbar() {
  type Tag = {
    name: string
    Icon: LucideIcon
    path: string
  }
  const tags: Tag[] = [
    {
      name: "home",
      Icon: HomeIcon,
      path: "/"

    },
    {
      name: "products",
      Icon: ScanBarcode,
      path: "/products"

    },
    {
      name: "transaction",
      Icon: ArrowLeftRight,
      path: "/transactions"
    }
  ]
  const pathName = usePathname()
  return (
    <nav className="flex justify-end p-1 items-center space-x-3">
      <ul className="flex flex-row p-2 gap-5">
        {tags.map((e, i) => (
          <li key={i}>
            <Link href={e.path} className={`flex flex-row justify-center items-center text-center  gap-1 text-lg ${pathName === e.path ? "border-b-2 border-b-primary" : ""}`}>
              <e.Icon className="w-5 h-5" /> {e.name}
            </Link>
          </li>
        )
        )}
      </ul>
      <ModeToggle />
    </nav>
  )
}
