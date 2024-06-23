import { NextRequest, NextResponse } from 'next/server'
import { obtenerUsuario } from './service/UsuarioService';

export async function middleware(request: NextRequest) {
  const [usuario] = await obtenerUsuario()
  if (request.nextUrl.pathname.startsWith('/login') || request.nextUrl.pathname.startsWith('/register')) {
    return usuario ? NextResponse.redirect(new URL('/', request.url)) : NextResponse.next()
  }

  if (!usuario) {
    return NextResponse.redirect(new URL('/login', request.url))
  }

  return NextResponse.next()
}

export const config = {
  matcher: [
    '/((?!_next|articulos).*)',
  ],
}
