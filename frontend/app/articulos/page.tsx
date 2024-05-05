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

const fetchData = async (): Promise<ArticuloDTO[]> => {
  return await getArticulos()
}

export default async function Articulos() {
  // Obtener articulos y mostrar los que tengan estado ABIERTO
  const articulos = await fetchData().then((articulos) =>
    articulos.filter((articulo) => articulo.estado === "ABIERTO")
  )

  return <ArticulosClient articulos={articulos} />
}
