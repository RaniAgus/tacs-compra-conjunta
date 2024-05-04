"use server"

import { Request } from "./AbstractService"

export async function getEstadisticas() {
    return await Request("/estadisticas/basicas", "GET")
}