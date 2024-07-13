"use server"
import { UsuarioDTO } from "@/model/UsuarioDTO"
import { Request } from "./AbstractService"
import { ArticuloDTO } from "@/model/ArticuloDTO"

export async function obtenerUsuario() {
    return await Request<UsuarioDTO>("/usuarios/me", "GET")
}

export async function obtenerArticulosDelUsuario() {
    return await Request<ArticuloDTO[]>("/usuarios/me/articulos", "GET")
}

export async function obtenerMisCompras() {
    return await Request<ArticuloDTO[]>("/usuarios/me/compras", "GET")
}
