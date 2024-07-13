"use server";
import { cookies } from "next/headers";
import { Request } from "./AbstractService";
import { TOKEN } from '@/app/utils/constants';

export async function iniciarSesion(iniciarSesionDTO: IniciarSesionDTO) {
    const response = await Request<{ token: string }>("/iniciarSesion", "POST", iniciarSesionDTO, false);
    if (response.ok) {
        cookies().set(TOKEN, response.data.token);
    }
    return response;
}

export async function registrarse(registrarseDTO: RegistrarseDTO) {
    const response = await Request<{ token: string }>("/registrarse", "POST", registrarseDTO, false);
    if (response.ok) {
        cookies().set(TOKEN, response.data.token);
    }
    return response;
}

export async function cerrarSesion() {
    cookies().delete(TOKEN)
}
