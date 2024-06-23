//@ts-nocheck
import { Card, CardBody, Image, Button } from "@nextui-org/react"
import { ArticuloDTO } from "@/model/ArticuloDTO"
import { getArticulos } from "@/service/ArticulosService"
import { FaUsers } from "react-icons/fa"
import { IoCopy } from "react-icons/io5"
import { UsuarioDTO } from "@/model/UsuarioDTO"

import { useDisclosure } from "@nextui-org/react"
import ModalComprar from "./ModalComprar"
import ArticulosClient from "./ArticulosClient"
import toast from 'react-hot-toast'
import { handleErrorServerSide } from '../utils/ServerErrorUtils'

const fetchData = async (): Promise<ArticuloDTO[]> => {
  // Obtener articulos y mostrar los que tengan estado ABIERTO
  return handleErrorServerSide(await getArticulos()).filter((articulo) => articulo.estado === "ABIERTO")
}

export default async function Articulos() {
  const articulos = await fetchData()
  return <ArticulosClient articulos={articulos} />
}
