"use client"
import { getAllProductEntities } from "../actions/productEntityActions"
import { DataTable } from "@/components/datatable"
import { entityColumns } from "../columns/entityCols"
import { useEffect, useState } from "react"
import ProductEntity from "../models/productEntity"
import toast from "react-hot-toast"
import ProductsInputFilter from "./ProductsInputFilter"

export default function Products() {
  const [productEntities, setProductEntities] = useState<ProductEntity[]>([])
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
      <ProductsInputFilter setProducts={setProductEntities} products={productEntities}/>
      <DataTable data={productEntities} columns={entityColumns} />
    </section>
  )
}
