import { obtenerArticulosDelUsuario } from "@/service/UsuarioService"
import PublicacionesClient from "./PublicacionesClient"
import { UsuarioDTO } from "@/model/UsuarioDTO"

const fetchData = async (): Promise<UsuarioDTO> => {
  return await obtenerArticulosDelUsuario()
}

async function MisPublicaciones() {
  const articulos = await fetchData().then((data) => data.articulosPublicados)

  return <PublicacionesClient articulos={articulos} />
}

export default MisPublicaciones
