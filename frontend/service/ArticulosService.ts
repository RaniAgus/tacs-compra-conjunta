"use server"
import { ArticuloDTO } from '@/model/ArticuloDTO'
import { Request } from "./AbstractService"

export async function getArticulos() {
  return await Request("/articulos", "GET", {}, false)
}

export async function crearArticulo(articulo: CrearArticuloDTO): Promise<[ArticuloDTO?, string?]> {
  return await Request("/articulos", "POST", articulo)
}

export async function comprarArticulo(articuloId: string): Promise<[ArticuloDTO?, string?]> {
  return await Request(`/articulos/${articuloId}/compradores`, "POST")
}

export async function cancelarCompraArticulo(articuloId: string): Promise<[ArticuloDTO?, string?]> {
  return await Request(`/articulos/${articuloId}/compradores`, "DELETE")
}
