import { obtenerArticulosDelUsuario } from "@/service/UsuarioService"
import PublicacionesClient from "./PublicacionesClient"
import { ArticuloDTO } from '@/model/ArticuloDTO'
import { handleErrorServerSide } from '../utils/ServerErrorUtils'

const fetchData = async (): Promise<ArticuloDTO[]> => {
  return handleErrorServerSide(await obtenerArticulosDelUsuario())
}

async function MisPublicaciones() {
  const articulos = await fetchData()
  return <PublicacionesClient articulos={articulos!} />
}

export default MisPublicaciones
