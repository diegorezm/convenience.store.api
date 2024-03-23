import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTitle,
} from "@/components/ui/dialog"
import { EntityDropdownProps } from "./page";

export default function InfoDropdown({ clearParams, id }: EntityDropdownProps) {
  const pId = parseInt(id)
  return (
    <Dialog defaultOpen onOpenChange={clearParams}>
      <DialogContent>
        <DialogHeader>
          <DialogTitle>Info</DialogTitle>
          <DialogDescription>
            Infor for id: {pId}
          </DialogDescription>
        </DialogHeader>
      </DialogContent>
    </Dialog>
  )
}
