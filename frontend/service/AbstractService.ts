import { cookies } from "next/headers"

const base_url = process.env.BACKEND_URL!

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
        return undefined
    }

    const response = await fetch(base_url + url, {
        method,
        headers: useToken ? tokenHeaders : defaultHeaders,
        body: method !== 'GET' ? JSON.stringify(body) : undefined,
        next: { revalidate: 0 }
    })

    const data = await response.json()

    if (!response.ok) {
        console.log(data)
        if (data.errors?.fields) {
            throw new Error(Object.values(data.errors.fields).join("\n"))
        }
        throw new Error(data.error || data.title)
    }

    return data
}
