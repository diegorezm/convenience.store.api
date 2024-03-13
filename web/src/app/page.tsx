import { LucideIcon, Receipt, ShoppingBasket, ShoppingCart } from "lucide-react"

export default function Home() {
  type Action = {
    description: string,
    Icon: LucideIcon
  }
  const actionList: Action[] = [{
    description: "Ability to have full control of every item inside that the store has in stock.",
    Icon: ShoppingCart
  },
  {
    description: "Ability to have full control of the every transaction made in the store.",
    Icon: ShoppingBasket
  },
  {
    description: "Ability to generate the receipts for every transaction.",
    Icon: Receipt
  },
  {
    description: "Ability to generate the receipts for every transaction.",
    Icon: Receipt
  },
  ]
  return (
    <section className="p-4">
      <div>
        <h1 className="text-4xl text-primary text-center">Convenience Store Api</h1>
      </div>
      <div className="flex flex-col justify-center items-center my-10 border border-border p-2 rounded-[var(--radius)]">
        <div className="w-full">
          <h2 className="text-2xl px-5">Features: </h2>
        </div>
        <ul className="grid grid-cols-2 2xl:grid-cols-3 gap-10 my-5">
          {actionList.map((e, i) => {
            return (
              <li key={i} className="flex flex-row gap-5 items-center">
                <e.Icon className="h-10 w-10" />
                <p>{e.description}</p>
              </li>
            )
          })}
        </ul>
      </div>
    </section>
  )
}
