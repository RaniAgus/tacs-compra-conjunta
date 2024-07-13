"use server"

import { EstadisticaDTO } from '@/model/EstadisticaDTO'
import { Request } from "./AbstractService"

export async function getEstadisticas() {
    return await Request<EstadisticaDTO>("/estadisticas/basicas", "GET")
}
