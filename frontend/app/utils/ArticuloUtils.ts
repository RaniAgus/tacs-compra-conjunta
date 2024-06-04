import { UsuarioDTO } from "@/model/UsuarioDTO"
import toast from "react-hot-toast"

export const cierraEn = (deadline: string) => {
    const now = new Date().getTime()
    const diff = new Date(deadline).getTime() - now
    if (diff < 0) return "¡Cerrado!"
    const days = Math.floor(diff / (1000 * 60 * 60 * 24))

    if (days > 0) return "Cierra en " + days + " dias!"
    if (days == 0) return "Cierra hoy"
}

export const compradoresFaltan = (maxPersonas: number, compradores: UsuarioDTO[]) => {
    return "¡Faltan " + (maxPersonas - compradores.length) + " compradores!"
}

export const copiarLinkArticulo = async (id: string) => {
    await navigator.clipboard.writeText(window.location.origin + "/articulos/" + id)
    toast.success("Link copiado")
}