import { UsuarioDTO } from "@/model/UsuarioDTO"
import { obtenerMisCompras } from "@/service/UsuarioService"
import ComprasClient from "./ComprasClient"

const fetchData = async (): Promise<UsuarioDTO> => {
  return await obtenerMisCompras()
}

async function MisCompras() {
  const articulos = await fetchData().then((data) => data.articulosComprados)

  return <ComprasClient articulos={articulos} />
}

export default MisCompras
