import { NextRequest, NextResponse } from 'next/server'
import { cookies } from "next/headers";

export function middleware(request: NextRequest) {
    const token = cookies().get('token')

    if (!token?.value) {
        const url = request.nextUrl.clone()
        url.pathname = '/login'
        return NextResponse.redirect(url)
    }

    return NextResponse.next()
}

export const config = {
  matcher: [
    '/((?!login|register|forgot-password|_next).*)',
  ],
}