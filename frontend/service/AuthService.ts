"use server"
import { cookies } from "next/headers";
import { Request } from "./AbstractService";

export async function iniciarSesion(iniciarSesionDTO: IniciarSesionDTO) {
    const response = await Request("/iniciarSesion", "POST", iniciarSesionDTO, false)
    cookies().set('token', response.token)
}

export async function cerrarSesion() {
    cookies().delete('token')
}
