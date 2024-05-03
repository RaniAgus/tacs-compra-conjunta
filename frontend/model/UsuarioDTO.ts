import { ArticuloDTO } from './ArticuloDTO'

export type UsuarioDTO = {
  id: string
  nombre: string
  correo: string
  contrasena: string
  articulos: ArticuloDTO[]
  articulosComprados: ArticuloDTO[]
  articulosPublicados: ArticuloDTO[]
}
