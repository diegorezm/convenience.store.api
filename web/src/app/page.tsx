import { LucideIcon, ShoppingBasket, ShoppingCart } from "lucide-react"

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
    description: "Ability to have full control of the every transaction made in the store and generate receipts.",
    Icon: ShoppingBasket
  },
  ]
  return (
    <section className="p-4">
      <div>
        <h1 className="text-4xl text-primary">Convenience Store Api</h1>
      </div>
      <div className="my-10">
        <h2 className="text-2xl">Some of the functionalities: </h2>
        <ul className="grid grid-cols-2 2xl:grid-cols-3 gap-10 my-5">
          {actionList.map((e, i) => {
            return (
              <li key={i} className="flex flex-row gap-5">
                <e.Icon className="h-10 w-10"/>
                <p>{e.description}</p>
              </li>
            )
          })}
        </ul>
      </div>
    </section>
  )
}
