"use client"
import Articulo from "@/components/Articulo"
import useUsuario from "@/hooks/useUsuario"
import { ArticuloDTO } from "@/model/ArticuloDTO"
import { Button, useDisclosure } from "@nextui-org/react"
import { useState } from "react"
import toast from "react-hot-toast"
import ModalComprar from "./ModalComprar"

function ArticulosClient({ articulos }: { articulos: ArticuloDTO[] }) {
  const { isOpen, onOpenChange } = useDisclosure()
  const [selectedArticulo, setSelectedArticulo] = useState<ArticuloDTO | null>(null)
  const usuario = useUsuario()

  return (
    <>
      {articulos.length === 0 && (
        <>
          <h2 className="text-center text-2xl">
            ¡Ups!, parece que no hay articulos disponibles
          </h2>
          <h3 className="text-center ">Intenta más tarde</h3>
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
                    color="success"
                    onClick={() => {
                      if (!usuario) {
                        toast.error("Debes iniciar sesion para comprar")
                        return
                      }
                      onOpenChange()
                      setSelectedArticulo(articulo)
                    }}
                  >
                    Comprar
                  </Button>
                )}
              />
            ))}
          </div>
          <ModalComprar
            isOpen={isOpen && !!usuario}
            onOpenChange={onOpenChange}
            selectedArticulo={selectedArticulo!}
          />
        </>
      )}
    </>
  )
}

export default ArticulosClient
