"use client"
import { usePathname } from 'next/navigation'
import React from 'react'

function Articulo() {
    const pathname = usePathname()
    return (
        <div>{pathname}</div>
    )
}

export default Articulo