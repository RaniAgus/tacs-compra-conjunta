import { NextRequest, NextResponse } from 'next/server'
import { obtenerUsuario } from './service/UsuarioService';

export async function middleware(request: NextRequest) {
  const { ok } = await obtenerUsuario()

  if (ok !== [
    '/login',
    '/register',
  ].includes(request.nextUrl.pathname)) {
    return NextResponse.next()
  }

  if (ok) {
    return NextResponse.redirect(new URL('/', request.url))
  }

  return NextResponse.redirect(new URL('/login', request.url))
}

export const config = {
  matcher: [
    '/((?!_next|articulos).*)',
  ],
}
