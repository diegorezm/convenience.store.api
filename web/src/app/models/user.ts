export default interface User{
  id: number 
  email: string
  password?: string
  role: String
  createdAt: Date,
  updatedAt: Date,
}

export type Login = {
  email: string
  password: string
}
