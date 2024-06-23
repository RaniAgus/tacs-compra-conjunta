"use server"
import { cookies } from "next/headers";
import { map, Request } from "./AbstractService";
import { TOKEN } from '@/app/utils/constants';

export async function iniciarSesion(iniciarSesionDTO: IniciarSesionDTO) {
    const response = await Request("/iniciarSesion", "POST", iniciarSesionDTO, false)
    return map(response, ({ token }) => {
        cookies().set(TOKEN, token)
    })
}

export async function registrarse(registrarseDTO: RegistrarseDTO) {
    const response = await Request("/registrarse", "POST", registrarseDTO, false)
    return map(response, ({ token }) => {
        cookies().set(TOKEN, token)
    })
}

export async function cerrarSesion() {
    cookies().delete(TOKEN)
}
