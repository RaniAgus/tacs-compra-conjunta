import { cookies } from "next/headers"

const base_url = process.env.BASE_URL!

export type HttpMethod = 'GET' | 'POST' | 'PUT' | 'DELETE'

export const TOKEN = "token"

export async function Request(url: string, method: HttpMethod, body: any = {}, useToken = true) {
    const defaultHeaders: HeadersInit = {
        'Content-Type': 'application/json',
    }

    const tokenHeaders: HeadersInit = {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${cookies().get(TOKEN)?.value}`
    }

    if (useToken && !cookies().get(TOKEN)) {
        console.log("No hay token")
        return
    }

    const response = await fetch(base_url + url, {
        method,
        headers: useToken ? tokenHeaders : defaultHeaders,
        body: method !== 'GET' ? JSON.stringify(body) : undefined
    })

    const data = await response.json()

    if (!response.ok) {
        throw new Error(data.error)
    }

    return data
}