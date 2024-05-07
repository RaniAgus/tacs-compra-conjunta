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

const menuItems = [
  { name: "Ver Articulos", page: "/articulos" },
  { name: "Crear Publicacion", page: "/crear_publicacion" },
  { name: "Mis Publicaciones", page: "/mis_publicaciones" },
  { name: "Mis Compras", page: "/mis_compras" },
]

export default function Navbar() {
  const usuario = useUsuario()
  const pathname = usePathname()
  const router = useRouter()
  const [_, setUsuario] = useAtom(user)
  const [isMenuOpen, setIsMenuOpen] = React.useState(false)

  const handleLogout = async () => {
    await cerrarSesion().then(() => {
      setUsuario(null)
      router.replace("/login")
      toast.success("Cierre de sesion exitoso")
    }).catch((error) => toast.error(error.message))
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
            <NavbarItem isActive={pathname == "/crear_publicacion"}>
              <Link color="foreground" href="/crear_publicacion">
                Crear Publicacion
              </Link>
            </NavbarItem>
            <NavbarItem isActive={pathname == "/mis_publicaciones"}>
              <Link color="foreground" href="/mis_publicaciones">
                Mis Publicaciones
              </Link>
            </NavbarItem>
            <NavbarItem isActive={pathname == "/mis_compras"}>
              <Link color="foreground" href="/mis_compras">
                Mis Compras
              </Link>
            </NavbarItem>
          </>
        )}
      </NavbarContent>

      <NavbarContent className="hidden sm:flex" justify="end">
        <ThemeSwitcher />
        <Session usuario={usuario} handleLogout={handleLogout}/>
      </NavbarContent>

      <NavbarMenu>
        {menuItems.map((item, index) => (
          <NavbarMenuItem key={`${item}-${index}`}>
            <Link className="w-full" color="foreground" href={item.page}>
              {item.name}
            </Link>
          </NavbarMenuItem>
        ))}
        {usuario ? (
          <NavbarMenuItem key={"logout"}>
            <NextUILink color="danger" onClick={handleLogout}>
              Cerrar Sesion
            </NextUILink>
          </NavbarMenuItem>
        ) : (
          <>
            <NavbarMenuItem key={"login-2"}>
              <SpecialLink color="primary" href="/login">
                Iniciar Sesion
              </SpecialLink>
            </NavbarMenuItem>
            <NavbarMenuItem key={"login-2"}>
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