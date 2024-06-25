import { UsuarioDTO } from "./UsuarioDTO"
import { CostoDTO } from "./CostoDTO"

export type ArticuloDTO = {
  id: string
  nombre: string
  imagen: string
  deadline?: string
  minPersonas: number
  maxPersonas: number
  costo: CostoDTO
  recepcion: string
  compradores: {id:string, nombreDeUsuario:string}[]
  publicador: UsuarioDTO
  estado: string
}
