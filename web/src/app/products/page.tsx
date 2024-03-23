"use client"
import { getAllProductEntities } from "../actions/productEntityActions"
import { DataTable } from "@/components/datatable"
import { entityColumns } from "../columns/entityCols"
import { useEffect, useState } from "react"
import ProductEntity from "../models/productEntity"
import toast from "react-hot-toast"
import ProductsInputFilter from "./ProductsInputFilter"
import { usePathname, useRouter, useSearchParams } from "next/navigation"
import { EntityActionsParam } from "../queryParams/productEntityQueryParams"
import EditDropdown from "./EditDropdown"
import InfoDropdown from "./InfoDropdown"
import DeleteDropdown from "./DeleteDropdown"

export type EntityDropdownProps = {
  clearParams: () => void
  id: string
}

export default function Products() {
  const [productEntities, setProductEntities] = useState<ProductEntity[]>([])
  const router = useRouter()
  const pathname = usePathname()
  const clearParams = () => {
    router.replace(pathname);
  };
  const params = useSearchParams()


  const LoadModalComponents = () => {
    const showInfoParam = params.get(EntityActionsParam.showInfo) ?? ""
    const deleteEntityParam = params.get(EntityActionsParam.deleteEntity) ?? ""
    const editEntityParam = params.get(EntityActionsParam.editEntity) ?? ""
    if (params.has(EntityActionsParam.showInfo)) return <InfoDropdown clearParams={clearParams} id={showInfoParam} />
    if (params.has(EntityActionsParam.deleteEntity)) return <DeleteDropdown clearParams={clearParams} id={deleteEntityParam} />
    if (params.has(EntityActionsParam.editEntity)) return <EditDropdown clearParams={clearParams} id={editEntityParam} />
    return null
  }

  useEffect(() => {
    getAllProductEntities({}).then(e => {
      if ('message' in e) {
        toast.error(e.message)
        return
      }
      setProductEntities(e)
    })
  }, [])
  return (
    <section className="flex flex-col p-4 gap-2">
      {LoadModalComponents() != null && LoadModalComponents()}
      <ProductsInputFilter setProducts={setProductEntities} products={productEntities} />
      <DataTable data={productEntities} columns={entityColumns} />
    </section>
  )
}
