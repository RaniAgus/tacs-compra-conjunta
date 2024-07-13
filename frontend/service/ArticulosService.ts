"use server"
import { ArticuloDTO } from '@/model/ArticuloDTO'
import { Request, Result } from "./AbstractService"

export async function getArticulos() {
  return await Request<ArticuloDTO[]>("/articulos", "GET", {}, false)
}

export async function crearArticulo(articulo: CrearArticuloDTO) {
  return await Request<ArticuloDTO>("/articulos", "POST", articulo)
}

export async function comprarArticulo(articuloId: string) {
  return await Request<ArticuloDTO>(`/articulos/${articuloId}/compradores`, "POST")
}

export async function cancelarCompraArticulo(articuloId: string) {
  return await Request<void>(`/articulos/${articuloId}/compradores`, "DELETE")
}

export async function cerrarPublicacion(articuloId: string) {
  return await Request<ArticuloDTO>(`/articulos/${articuloId}?estado=VENDIDO`, "PATCH")
}

export async function cancelarPublicacion(articuloId: string) {
  return await Request<ArticuloDTO>(`/articulos/${articuloId}?estado=CANCELADO`, "PATCH")
}
