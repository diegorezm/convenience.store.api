"use server"
import { ax } from "@/config/axios";
import User, { Login } from "../models/user";
import { AxiosError } from "axios";
import ErrorMessage from "../models/errorMessage";
import { Order } from "../queryParams";
import { OrderByUsers } from "../queryParams/userQueryParams";

const URL = "/users"

async function handleAxiosError(error: AxiosError): Promise<ErrorMessage> {
  if (error.response) {
    if (error.response.status == 201) {
      return {
        message: "Could not create new user.",
        status: 500
      }
    }
    if (error.response.status == 403 || error.response.status == 401) {
      return {
        message: "unauthorized.",
        status: error.response.status
      }
    }
    if (error.response.status === 400 || error.response.status === 404) {
      return error.response.data as ErrorMessage;
    }
  }
  return {
    message: "Internal server error.",
    status: 500
  };
}

export async function login(data: Login) {
  type LoginResponse = {
    token: string
  }
  try {
    let reqUrl = `${URL}/login`
    const response = await ax.post(reqUrl, data)
    return response.data as LoginResponse
  } catch (error: any) {
    return handleAxiosError(error)
  }
}

export async function register(data: User) {
  try {
    const response = await ax.post(URL, data)
    return response.data as User
  } catch (error: any) {
    return handleAxiosError(error)
  }
}

export async function getAllUsers({ order = Order.desc, orderby = OrderByUsers.id }) {
  try {
    let reqUrl = `${URL}?order=${order}&orderby=${orderby}`
    const response = await ax.get(reqUrl)
    return response.data as User[]
  } catch (error: any) {
    return handleAxiosError(error)
  }
}

export async function updateUser(data: User) {
  try {
    let reqUrl = `${URL}/${data.id}`
    const response = await ax.put(reqUrl, data)
    return response.data as User
  } catch (error: any) {
    return handleAxiosError(error)
  }
}

export async function deleteUser(id: number) {
  try {
    let reqUrl = `${URL}/${id}`
    const response = await ax.delete(reqUrl)
    return response.data as User
  } catch (error: any) {
    return handleAxiosError(error)
  }
}
