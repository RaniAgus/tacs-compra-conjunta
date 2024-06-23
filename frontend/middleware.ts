import { NextRequest, NextResponse } from 'next/server'
import { obtenerUsuario } from './service/UsuarioService';

export async function middleware(request: NextRequest) {
  const [usuario, error] = await obtenerUsuario()

  const isLoginOrRegisterPath =
    request.nextUrl.pathname.startsWith('/login') ||
    request.nextUrl.pathname.startsWith('/register')

  const isUserLoggedIn = usuario !== undefined && error === undefined

  if (isLoginOrRegisterPath && isUserLoggedIn) {
    return NextResponse.redirect(new URL('/', request.url))
  }

  if (!isLoginOrRegisterPath && !isUserLoggedIn) {
    return NextResponse.redirect(new URL('/login', request.url))
  }

  return NextResponse.next()
}

export const config = {
  matcher: [
    '/((?!_next|articulos).*)',
  ],
}
