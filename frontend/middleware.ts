import { NextResponse } from 'next/server'
import type { NextRequest } from 'next/server'

// This function can be marked `async` if using `await` inside
export function middleware(request: NextRequest) {
    const jwt = localStorage.getItem('token')
    if (!jwt) return NextResponse.redirect(new URL('/login', request.url))

    return NextResponse.redirect(new URL('/home', request.url))
}

// All routes except login, register and forgot-password
export const config = {
  matcher: [
    '/((?!login|register|forgot-password).*)',
  ],
}