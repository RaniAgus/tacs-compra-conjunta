"use server"

import { LOGIN_ERROR, TOKEN } from '@/app/utils/constants'
import { cookies } from "next/headers"

const base_url = process.env.BACKEND_URL!

export type HttpMethod = 'GET' | 'POST' | 'PUT' | 'DELETE'

export async function Request(url: string, method: HttpMethod, body: any = {}, useToken = true) {
    const defaultHeaders: HeadersInit = {
        'Content-Type': 'application/json',
    }

    const tokenHeaders: HeadersInit = {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${cookies().get(TOKEN)?.value}`
    }

    if (useToken && !cookies().get(TOKEN)) {
        return error(LOGIN_ERROR)
    }

    const response = await fetch(base_url + url, {
        method,
        headers: useToken ? tokenHeaders : defaultHeaders,
        body: method !== 'GET' ? JSON.stringify(body) : undefined,
        next: { revalidate: 0 }
    })

    const data = await response.json()

    if (response.ok) {
        return success(data)
    }

    if (response.status === 401) {
        return error(LOGIN_ERROR)
    }

    if (data.errors?.fields) {
        return error(Object.values(data.errors.fields).join("\n"))
    }

    return error(data.error || data.title)
}

export async function success<T>(data: T): Promise<[T?, string?]> {
    return [data, undefined]
}

export async function error<T>(error: string): Promise<[T?, string?]> {
    return [undefined, error]
}

export async function map<T, U>([data, error]: [T?, string?], f: (data: T) => U): Promise<[U?, string?]> {
    if (error) {
        return [undefined, error]
    }
    return [f(data!), undefined]
}
