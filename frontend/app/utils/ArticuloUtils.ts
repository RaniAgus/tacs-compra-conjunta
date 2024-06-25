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

export function compradoresFaltan(maxPersonas: number, compradoresLength: number) {
    if (maxPersonas - compradoresLength == 0) {
      return "¡Ya se completó!"
    }
    return "¡Faltan " + (maxPersonas - compradoresLength) + " compradores!"
  }

export const copiarLinkArticulo = async (id: string) => {
    await navigator.clipboard.writeText(window.location.origin + "/articulos/" + id)
    toast.success("Link copiado")
}