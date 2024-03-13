"use client"
import { ColumnDef } from "@tanstack/react-table";
import ProductEntity from "../models/productEntity";
import { formatDistance } from "date-fns";
import { Button } from "@/components/ui/button";
import { Info, ArrowUpDown, MoreHorizontal, Trash2, Pencil, LucideIcon } from "lucide-react";
type Actions = {
  name: string
  Icon: LucideIcon
}
const actions: Actions[] = [
  {
    name: "edit",
    Icon: Pencil
  },
  {
    name: "delete",
    Icon: Trash2
  }
]

import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu"

export const entityColumns: ColumnDef<ProductEntity>[] = [
  {
    accessorKey: "id",
    header: ({ column }) => {
      return (
        <div className="text-center">
          <Button
            variant="ghost"
            onClick={() => column.toggleSorting(column.getIsSorted() === "asc")}
          >
            Id
            <ArrowUpDown className="ml-2 h-4 w-4" />
          </Button>
        </div>
      )
    },
  },

  {
    accessorKey: "name",
    header: ({ column }) => {
      return (
        <div className="text-center">
          <Button
            variant="ghost"
            onClick={() => column.toggleSorting(column.getIsSorted() === "asc")}
          >
            Name
            <ArrowUpDown className="ml-2 h-4 w-4" />
          </Button>
        </div>
      )
    },
  },
  {
    accessorKey: "createdAt",
    header: ({ column }) => {
      return (
        <div className="text-center">
          <Button
            variant="ghost"
            onClick={() => column.toggleSorting(column.getIsSorted() === "asc")}
          >
            Created
            <ArrowUpDown className="ml-2 h-4 w-4" />
          </Button>
        </div>
      )
    },
    cell: ({ row }) => {
      const day = row.getValue("createdAt") as Date
      const d = formatDistance(day, new Date(), { addSuffix: true })
      return <div>{d}</div>
    }
  },
  {
    accessorKey: "updatedAt",
    header: ({ column }) => {
      return (
        <div className="text-center">
          <Button
            variant="ghost"
            onClick={() => column.toggleSorting(column.getIsSorted() === "asc")}
          >
            Updated
            <ArrowUpDown className="ml-2 h-4 w-4" />
          </Button>
        </div>
      )
    },
    cell: ({ row }) => {
      const day = row.getValue("updatedAt") as Date
      const d = formatDistance(day, new Date(), { addSuffix: true })
      return <div>{d}</div>
    }
  },
  {
    id: "actions",
    cell: ({ row }) => {
      const entity = row.original
      return (
        <DropdownMenu>
          <DropdownMenuTrigger asChild>
            <Button variant="ghost" className="h-8 w-8 p-0">
              <span className="sr-only">Open menu</span>
              <MoreHorizontal className="h-4 w-4" />
            </Button>
          </DropdownMenuTrigger>
          <DropdownMenuContent align="end">
            <DropdownMenuLabel>Actions</DropdownMenuLabel>
            <DropdownMenuItem
              className="flex flex-row gap-2 items-center"
              onClick={() => navigator.clipboard.writeText(entity.id.toString())}
            >
              <Info className="w-4 h-4" /> Product info
            </DropdownMenuItem>
            <DropdownMenuSeparator />
            {actions.map((e, u) => (
              <DropdownMenuItem key={u}
                className="flex flex-row gap-2 items-center">
                <e.Icon className="w-4 h-4" />
                {e.name}
              </DropdownMenuItem>
            ))}
          </DropdownMenuContent>
        </DropdownMenu>)
    },
  },
]
