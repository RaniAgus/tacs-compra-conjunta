"use client"
import useUsuario from "@/hooks/useUsuario"
import {
  Button,
  Link as NextUILink,
  Navbar as Nav,
  NavbarBrand,
  NavbarContent,
  NavbarItem,
  NavbarMenu,
  NavbarMenuItem,
  NavbarMenuToggle,
} from "@nextui-org/react"
import Link from "next/link"
import { usePathname, useRouter } from "next/navigation"
import React, { useEffect } from "react"
import Session from "./Session"
import { ThemeSwitcher } from "./ThemeSwitcher"
import { useAtom } from "jotai"
import { user } from "@/app/layout"
import { cerrarSesion } from "@/service/AuthService"
import toast from "react-hot-toast"
import Rol from "./Rol"

const menuItems = [
  { name: "Crear Publicacion", page: "/crear_publicacion", rol: null },
  { name: "Mis Publicaciones", page: "/mis_publicaciones", rol: "USUARIO" },
  { name: "Mis Compras", page: "/mis_compras", rol: "USUARIO" },
]

export default function Navbar() {
  const usuario = useUsuario()
  const pathname = usePathname()
  const router = useRouter()
  const [_, setUsuario] = useAtom(user)
  const [isMenuOpen, setIsMenuOpen] = React.useState(false)

  const handleLogout = async () => {
    await cerrarSesion()
      .then(() => {
        setUsuario(null)
        router.replace("/login")
        toast.success("Cierre de sesion exitoso")
      })
      .catch((error) => toast.error(error.message))
  }

  useEffect(() => {
    setIsMenuOpen(false)
  }, [pathname])

  return (
    <Nav isBordered isMenuOpen={isMenuOpen} onMenuOpenChange={setIsMenuOpen}>
      <NavbarContent className="sm:hidden" justify="start">
        <NavbarBrand>
          <Link href="/" className="font-bold text-inherit">
            Compra Conjunta
          </Link>
        </NavbarBrand>
      </NavbarContent>

      <NavbarContent className="sm:hidden" justify="end">
        <ThemeSwitcher />
        <NavbarMenuToggle
          aria-label={isMenuOpen ? "Close menu" : "Open menu"}
        />
      </NavbarContent>

      <NavbarContent className="hidden sm:flex gap-4" justify="center">
        <NavbarBrand>
          <Link href="/" className="font-bold text-inherit">
            Compra Conjunta
          </Link>
        </NavbarBrand>
        <NavbarItem isActive={pathname == "/articulos"}>
          <Link color="foreground" href="/articulos">
            Ver Articulos
          </Link>
        </NavbarItem>

        {usuario && (
          <>
            {menuItems.map((item, index) => (
              <Rol rol={item.rol!} key={`${item}-${index}`}>
                <NavbarItem isActive={pathname == item.page}>
                  <Link color="foreground" href={item.page}>
                    {item.name}
                  </Link>
                </NavbarItem>
              </Rol>
            ))}
          </>
        )}
      </NavbarContent>

      <NavbarContent className="hidden sm:flex" justify="end">
        <ThemeSwitcher />
        <Session usuario={usuario} handleLogout={handleLogout} />
      </NavbarContent>

      <NavbarMenu>
        <NavbarMenuItem>
          <Link className="w-full" color="foreground" href="/articulos">
            Ver Articulos
          </Link>
        </NavbarMenuItem>
        {menuItems.map((item, index) => (
          <Rol rol={item.rol!} key={`${item}-${index}`}>
            <NavbarMenuItem>
              <Link className="w-full" color="foreground" href={item.page}>
                {item.name}
              </Link>
            </NavbarMenuItem>
          </Rol>
        ))}
        {usuario ? (
          <NavbarMenuItem>
            <NextUILink color="danger" onClick={handleLogout}>
              Cerrar Sesion
            </NextUILink>
          </NavbarMenuItem>
        ) : (
          <>
            <NavbarMenuItem>
              <SpecialLink color="primary" href="/login">
                Iniciar Sesion
              </SpecialLink>
            </NavbarMenuItem>
            <NavbarMenuItem>
              <SpecialLink color="warning" href="/registrarse">
                Registrarse
              </SpecialLink>
            </NavbarMenuItem>
          </>
        )}
      </NavbarMenu>
    </Nav>
  )
}

export function SpecialLink({ color, href, children }: { color: "primary" | "secondary" | "success" | "warning" | "danger" | "foreground", href: string, children: React.ReactNode }) {
  return (
    <NextUILink
      as={Link}
      className="w-full"
      color={color}
      href={href}
    >
      {children}
    </NextUILink>
  )
}
