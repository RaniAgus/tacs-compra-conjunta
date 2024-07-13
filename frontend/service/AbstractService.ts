"use server"

import { LOGIN_ERROR, TOKEN } from '@/app/utils/constants'
import { cookies } from "next/headers"

const base_url = process.env.BACKEND_URL!

export type HttpMethod = 'GET' | 'POST' | 'PUT' | 'DELETE' | 'PATCH'

export type Result<T> = { ok: true, data: T } | { ok: false, error: string };

function success<T>(data: T): Result<T> {
    return { ok: true, data };
}

function error<T>(error: string): Result<T> {
    return { ok: false, error };
}

export async function Request<T>(url: string, method: HttpMethod, body: any = {}, useToken = true): Promise<Result<T>> {
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

    if (response.status === 204) {
        return success(null as any)
    }

    const data = await response.json()

    if (response.ok) {
        return success(data)
    }

    if (response.status === 401) {
        return error(LOGIN_ERROR)
    }

    if (data.errors?.fields) {
        return error(Object.values(data.errors.fields).join(', '))
    }

    return error(data.error || data.title)
}
