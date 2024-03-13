"use server"
import { ax } from "@/config/axios";
import Product from "../models/product";
import { Order } from "../queryParams";
import { OrderByProducts, soldQueryParam } from "../queryParams/productsQueryParams";
import ErrorMessage from "../models/errorMessage";
import { AxiosError } from "axios";

const URL = '/products'

async function handleAxiosError(error: AxiosError): Promise<ErrorMessage> {
  if (error.response) {
    if (error.response.status == 201) {
      return {
        message: "Could not create the new product.",
        status: 500
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

export async function getAllProducts({
  orderby = OrderByProducts.id,
  order = Order.asc,
  sold
}: {
  orderby?: OrderByProducts;
  order?: Order;
  sold?: soldQueryParam;
}) {
  let reqUrl = `${URL}?orderby=${orderby}`
  if (sold) {
    reqUrl += `&sold=${sold}`
  }
  reqUrl += `&order=${order}`
  try {
    const response = await ax.get(reqUrl);
    return response.data as Product[];
  } catch (error: any) {
    return handleAxiosError(error)
  }
}

export async function getProductById(id: number) {
  try {
    let reqUrl = `${URL}/${id}`
    const response = await ax.get(reqUrl)
    return response.data as Product
  } catch (error: any) {
    return handleAxiosError(error)
  }
}

export async function registerNewProduct(data: Product) {
  try {
    const response = await ax.post(URL, data)
    return response.data
  } catch (error: any) {
    return handleAxiosError(error) 
  }
}

export async function updateItemStatus(data: Product) {
  try {
    let reqUrl = `${URL}/${data.id}`
    const response = await ax.put(reqUrl, data)
    return response.data
  } catch (error: any) {
    return handleAxiosError(error) 
    
  }
}

export async function deleteProduct(id: number) {
  try {
    let reqUrl = `${URL}/${id}`
    const response = await ax.delete(reqUrl)
    return response.data as Product
  } catch (error: any) {
    return handleAxiosError(error)
    
  }
}