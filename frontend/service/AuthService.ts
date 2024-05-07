"use server"
import { cookies } from "next/headers";
import { Request, TOKEN } from "./AbstractService";

export async function iniciarSesion(iniciarSesionDTO: IniciarSesionDTO) {
    const response = await Request("/iniciarSesion", "POST", iniciarSesionDTO, false)
    cookies().set(TOKEN, response.token)
}

export async function registrarse(registrarseDTO: RegistrarseDTO) {
    const response = await Request("/registrarse", "POST", registrarseDTO, false)
    cookies().set(TOKEN, response.token)
}

export async function cerrarSesion() {
    cookies().delete('token')
}
