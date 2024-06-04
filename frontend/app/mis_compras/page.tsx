import { ArticuloDTO } from "@/model/ArticuloDTO"
import { obtenerMisCompras } from "@/service/UsuarioService"
import ComprasClient from "./ComprasClient"

const fetchData = async (): Promise<ArticuloDTO[]> => {
  return await obtenerMisCompras()
}

async function MisCompras() {
  const articulos = await fetchData();

  return <ComprasClient articulos={articulos} />
}

export default MisCompras
