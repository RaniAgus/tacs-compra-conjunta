"use client"
import { useState } from "react"
import { Card, CardBody } from "@nextui-org/card"
import { Image } from "@nextui-org/image"
import { Button } from "@nextui-org/button"
import toast from "react-hot-toast"
import { FaUsers } from "react-icons/fa"
import { IoCopy } from "react-icons/io5"

import { ArticuloDTO } from "@/model/ArticuloDTO"
import { UsuarioDTO } from "@/model/UsuarioDTO"
import { useDisclosure } from "@nextui-org/use-disclosure"
import Link from "next/link"
import ModalCancelarCompra from "./ModalCancelarCompra"

function ComprasClient({ articulos }: { articulos: ArticuloDTO[] }) {
  const [selectedArticulo, setSelectedArticulo] = useState<ArticuloDTO | null>(
    null
  )

  const { isOpen, onOpenChange } = useDisclosure()

  function cierraEn(deadline: string) {
    const now = new Date().getTime()
    const diff = new Date(deadline).getTime() - now
    if (diff < 0) return "¡Cerrado!"
    const days = Math.floor(diff / (1000 * 60 * 60 * 24))

    if (days > 0) return "Cierra en " + days + " dias!"
    if (days == 0) return "Cierra hoy"
  }

  function compradoresFaltan(maxPersonas: number, compradores: UsuarioDTO[]) {
    return "¡Faltan " + (maxPersonas - compradores.length) + " compradores!"
  }

  async function copiarLinkArticulo(id: string) {
    await navigator.clipboard.writeText(window.location.origin + "/articulos/" + id)
    toast.success("Link copiado")
  }

  return (
    <>
      {articulos.length === 0 && (
        <>
          <h2 className="text-center text-2xl">
            ¡Ups!, parece que no tenes compras realizadas
          </h2>
          <h3 className="text-center ">
            Vuelve luego de comprar algun articulo en{" "}
            <Link href="articulos" className="underline">
              Ver Articulos
            </Link>
          </h3>
        </>
      )}
      {articulos.length > 0 && (
        <>
          <div className="flex flex-col gap-4 pb-10">
            {articulos.map((articulo) => (
              <Card
                key={articulo.id}
                isBlurred
                className="border-none bg-background/60 dark:bg-slate-800 max-w-[610px] mx-auto w-full"
                shadow="sm"
              >
                <CardBody>
                  <div className="flex flex-col md:flex-row gap-4">
                    <Image
                      className="w-full object-cover max-h-[150px] rounded-lg"
                      src={articulo.imagen}
                      alt="Articulo"
                      width="100%"
                      height={150}
                    />
                    <div className="flex flex-row flex-1 gap-8">
                      <div className="flex flex-col justify-between">
                        <h3 className="font-semibold text-foreground/90">
                          {articulo.nombre}
                        </h3>
                        {articulo.deadline && (
                          <h4>
                            <b color="warning">{cierraEn(articulo.deadline)}</b>
                          </h4>
                        )}
                        <p>{articulo.estado}</p>
                        <h4>
                          {compradoresFaltan(
                            articulo.maxPersonas,
                            articulo.compradores
                          )}
                        </h4>

                        <div className="flex flex-row gap-2 items-center">
                          <FaUsers />
                          {articulo.minPersonas} - {articulo.maxPersonas}{" "}
                          personas
                        </div>
                      </div>

                      <div className="flex flex-col justify-between flex-1">
                        <Button
                          className="self-end"
                          isIconOnly
                          aria-label="Link"
                          onClick={() => copiarLinkArticulo(articulo.id)}
                        >
                          <IoCopy />
                        </Button>
                        <h3 className="text-end">${articulo.costo.monto}</h3>
                        <Button
                          className="hidden md:block"
                          color="danger"
                          onClick={() => {
                            onOpenChange()
                            setSelectedArticulo(articulo)
                          }}
                        >
                          Eliminar Compra
                        </Button>
                      </div>
                    </div>
                    <Button
                      className="md:hidden"
                      color="danger"
                      onClick={() => {
                        onOpenChange()
                        setSelectedArticulo(articulo)
                      }}
                    >
                      Eliminar Compra
                    </Button>
                  </div>
                </CardBody>
              </Card>
            ))}
          </div>
          <ModalCancelarCompra
            isOpen={isOpen}
            onOpenChange={onOpenChange}
            selectedArticulo={selectedArticulo!}
          />
        </>
      )}
    </>
  )
}

export default ComprasClient
