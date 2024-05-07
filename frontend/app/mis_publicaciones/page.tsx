import { ArticuloDTO } from "@/model/ArticuloDTO"
import { obtenerArticulosDelUsuario } from "@/service/UsuarioService"
import PublicacionesClient from "./PublicacionesClient"

const fetchData = async (): Promise<ArticuloDTO[]> => {
  return await obtenerArticulosDelUsuario()
}

async function MisPublicaciones() {
  const articulos = await fetchData()

  return <PublicacionesClient articulos={articulos!} />
}

export default MisPublicaciones
