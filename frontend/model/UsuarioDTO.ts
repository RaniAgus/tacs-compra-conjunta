import { ArticuloDTO } from './ArticuloDTO'

export type UsuarioDTO = {
  id: string
  nombre: string
  email: string
  contrasena: string
  articulos: ArticuloDTO[]
  articulosComprados: ArticuloDTO[]
  articulosPublicados: ArticuloDTO[]
}
