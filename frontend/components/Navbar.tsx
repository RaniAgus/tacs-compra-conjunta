"use client"
import {
  Navbar as Nav,
  NavbarBrand,
  NavbarContent,
  NavbarItem,
  NavbarMenu,
  NavbarMenuItem,
  NavbarMenuToggle,
} from "@nextui-org/react"
import { usePathname } from "next/navigation"
import React, { useState } from "react"
import Session from "./Session"
import { ThemeSwitcher } from "./ThemeSwitcher"
import Link from "next/link"

export default function Navbar() {
  const [isMenuOpen, setIsMenuOpen] = React.useState(false)
  const [loggedIn, setLoggedIn] = useState(false)

  const menuItems = [
    { name: "Ver Articulos", page: "/articulos" },
    { name: "Crear Publicacion", page: "/crear_publicacion" },
    { name: "Mis Publicaciones", page: "/mis_publicaciones" },
    { name: "Mis Compras", page: "/mis_compras" },
  ]

  const pathname = usePathname()

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

        {loggedIn && (
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
        <Session />
      </NavbarContent>

      <NavbarMenu>
        {menuItems.map((item, index) => (
          <NavbarMenuItem key={`${item}-${index}`}>
            <Link className="w-full" color="foreground" href={item.page}>
              {item.name}
            </Link>
          </NavbarMenuItem>
        ))}
        {loggedIn ? (
          <NavbarMenuItem key={"logout-2"}>
            <Link className="w-full" color="danger" href="/cerrar_sesion">
              Logout
            </Link>
          </NavbarMenuItem>
        ) : (
          <>
            <NavbarMenuItem key={"login-2"}>
              <Link
                className="w-full"
                color="primary"
                href="/iniciar_sesion"
              >
                Iniciar Sesion
              </Link>
            </NavbarMenuItem>
            <NavbarMenuItem key={"login-2"}>
              <Link
                className="w-full"
                color="warning"
                href="/registrarse"
              >
                Registrase
              </Link>
            </NavbarMenuItem>
          </>
        )}
      </NavbarMenu>
    </Nav>
  )
}
