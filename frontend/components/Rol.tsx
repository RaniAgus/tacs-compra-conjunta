"use client"
import useUsuario from '@/hooks/useUsuario'
import React from 'react'

function Rol({ children, rol }: { children: React.ReactNode, rol: "USUARIO" | "ADMIN" | string }) {
    const usuario = useUsuario()

    if (usuario && rol == null || usuario?.roles.includes(rol) ) {
        return <>{children}</>
    } else {
        return <></>
    }
}

export default Rol