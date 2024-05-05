"use server"

import { Request } from "./AbstractService"
import { CrearArticuloDTO } from "@/model/CrearArticuloDTO"


export async function getArticulos() {
    return await Request("/articulos", "GET", {}, false)
}

export async function crearArticulo(articulo: CrearArticuloDTO) {
    return await Request("/articulos", "POST", articulo)
}