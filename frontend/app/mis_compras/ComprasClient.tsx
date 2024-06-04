"use client"
import { Button } from "@nextui-org/button"
import { useState } from "react"

import Articulo from "@/components/Articulo"
import { ArticuloDTO } from "@/model/ArticuloDTO"
import { useDisclosure } from "@nextui-org/use-disclosure"
import Link from "next/link"
import ModalCancelarCompra from "./ModalCancelarCompra"

function ComprasClient({ articulos }: { articulos: ArticuloDTO[] }) {
  const [selectedArticulo, setSelectedArticulo] = useState<ArticuloDTO | null>(null)
  const { isOpen, onOpenChange } = useDisclosure()

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
              <Articulo
                articulo={articulo}
                key={articulo.id}
                accion={(
                  <Button
                    color="danger"
                    onClick={() => {
                      onOpenChange()
                      setSelectedArticulo(articulo)
                    }}
                  >
                    Eliminar Compra
                  </Button>
                )}
              />
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
