"use server"
import { cookies } from 'next/headers'

const cookieStore = cookies()

export async function setToken(token: string) {
  cookieStore.set("token", token)
}
export async function getToken() {
  const token = cookieStore.get("token")
  return token?.value ?? ""
}

export async function deleteToken() {
  cookieStore.set("token", "")
}
