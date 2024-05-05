import { UsuarioDTO } from "./UsuarioDTO"
import { CostoDTO } from "./CostoDTO"

export type ArticuloDTO = {
  id: string
  nombre: string
  imagen: string
  link?: string
  deadline?: string
  minPersonas: number
  maxPersonas: number
  costo: CostoDTO
  recepcion: string
  compradores: UsuarioDTO[]
  publicador: UsuarioDTO
  estado: string
}
