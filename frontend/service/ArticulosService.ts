"use server"

import { Request } from "./AbstractService"

export async function getArticulos() {
    return await Request("/articulos", "GET", {}, false)
}