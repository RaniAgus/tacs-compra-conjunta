import { ArticuloDTO } from "@/model/ArticuloDTO"
import { obtenerMisCompras } from "@/service/UsuarioService"
import ComprasClient from "./ComprasClient"
import { handleErrorServerSide } from '../utils/ServerErrorUtils'

const fetchData = async (): Promise<ArticuloDTO[]> => {
  return handleErrorServerSide(await obtenerMisCompras())
}

async function MisCompras() {
  const articulos = await fetchData();

  return <ComprasClient articulos={articulos} />
}

export default MisCompras
