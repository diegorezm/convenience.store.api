import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTitle,
} from "@/components/ui/dialog"
import { EntityDropdownProps } from "./page"
export default function EditEntityDropdown({ clearParams, id }: EntityDropdownProps) {
  const pId = parseInt(id)
  return (
    <Dialog defaultOpen onOpenChange={clearParams}>
      <DialogContent>
        <DialogHeader>
          <DialogTitle>Edit {pId}</DialogTitle>
          <DialogDescription>
            Editing {pId}
          </DialogDescription>
        </DialogHeader>
      </DialogContent>
    </Dialog>
  )
}
