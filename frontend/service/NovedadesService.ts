import { Request } from "./AbstractService";

export async function getNovedades(): Promise<[NovedadDTO[]?, string?]> {
    return await Request(`/novedades`, "GET")
}

export async function leerNovedad(novedadId: string): Promise<[void?, string?]> {
    return await Request(`/novedades/${novedadId}`, "DELETE")
}

export async function leerTodasNovedades(): Promise<[void?, string?]> {
    return await Request(`/novedades`, "DELETE")
}