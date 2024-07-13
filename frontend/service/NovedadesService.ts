import { Request } from "./AbstractService";

export async function getNovedades() {
    return await Request<NovedadDTO[]>(`/novedades`, "GET")
}

export async function leerNovedad(novedadId: string) {
    return await Request<void>(`/novedades/${novedadId}`, "DELETE")
}

export async function leerTodasNovedades() {
    return await Request<void>(`/novedades`, "DELETE")
}
