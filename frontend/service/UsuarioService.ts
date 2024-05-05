"use server"
import { UsuarioDTO } from "@/model/UsuarioDTO"
import { Request } from "./AbstractService"

export async function obtenerUsuario(): Promise<UsuarioDTO> {
    return await Request("/usuarios/me", "GET")
}

export async function obtenerArticulosDelUsuario(): Promise<UsuarioDTO> {
    return await Request("/usuarios/me/articulos", "GET")
}