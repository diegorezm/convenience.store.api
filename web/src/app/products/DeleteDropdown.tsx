import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTitle,
} from "@/components/ui/dialog"
import { EntityDropdownProps } from "./page"
export default function DeleteDropdown({ clearParams , id}: EntityDropdownProps) {
  const pId = parseInt(id)
  return (
    <Dialog defaultOpen onOpenChange={clearParams}>
      <DialogContent>
        <DialogHeader>
          <DialogTitle>Delete</DialogTitle>
          <DialogDescription>
            Are you sure you want to delete entry: {pId}
          </DialogDescription>
        </DialogHeader>
      </DialogContent>
    </Dialog>
  )
}
