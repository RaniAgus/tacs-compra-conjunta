"use client"
import ModalComprar from "./ModalComprar"
import { Button, Card, CardBody, Image, useDisclosure } from "@nextui-org/react"
import { UsuarioDTO } from "@/model/UsuarioDTO"
import { ArticuloDTO } from "@/model/ArticuloDTO"
import { FaUsers } from "react-icons/fa6"
import { IoCopy } from "react-icons/io5"
import { useState } from "react"
import toast from "react-hot-toast"
import useUsuario from "@/hooks/useUsuario"

function ArticulosClient({ articulos }: { articulos: ArticuloDTO[] }) {
  const { isOpen, onOpenChange } = useDisclosure()
  const [selectedArticulo, setSelectedArticulo] = useState<ArticuloDTO | null>(
    null
  )
  const usuario = useUsuario()

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

  async function copiarLink(link: string) {
    await navigator.clipboard.writeText(link)
    toast.success("Link copiado")
  }

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
                        <p className="text-small text-foreground/80">
                          {articulo.publicador.nombre}
                        </p>
                        {articulo.deadline && (
                          <h4>
                            <b color="warning">{cierraEn(articulo.deadline)}</b>
                          </h4>
                        )}
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
                        {articulo.link && (
                          <Button
                            className="self-end"
                            isIconOnly
                            aria-label="Link"
                            onClick={() => copiarLink(articulo.link!)}
                          >
                            <IoCopy />
                          </Button>
                        )}
                        <h3 className="text-end">${articulo.costo.monto}</h3>
                        <Button
                          className="hidden md:block"
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
                      </div>
                    </div>
                    <Button
                      className="md:hidden"
                      color="success"
                      onClick={() => {
                        onOpenChange()
                        setSelectedArticulo(articulo)
                      }}
                    >
                      Comprar
                    </Button>
                  </div>
                </CardBody>
              </Card>
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
