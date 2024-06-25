"use client"
import { ArticuloDTO } from "@/model/ArticuloDTO"
import {
  cancelarPublicacion,
  cerrarPublicacion,
} from "@/service/ArticulosService"
import { Button } from "@nextui-org/button"
import {
  Modal,
  ModalBody,
  ModalContent,
  ModalFooter,
  ModalHeader,
} from "@nextui-org/modal"
import { useRouter } from "next/navigation"
import toast from "react-hot-toast"
import { handleErrorClientSide } from "../utils/ClientErrorUtils"

type ModalCompradoresProps = {
  isOpen: boolean
  onOpenChange: (isOpen: boolean) => void
  selectedArticulo: ArticuloDTO
  setArticulosClient: (callback: (articulos: ArticuloDTO[]) => ArticuloDTO[]) => void
}

function ModalCompradores({
  isOpen,
  onOpenChange,
  selectedArticulo,
  setArticulosClient,
}: ModalCompradoresProps) {
  const router = useRouter()

  async function handleCancelarPublicacion() {
    await cancelarPublicacion(selectedArticulo.id)
      .then(handleErrorClientSide(router))
      .then((nuevoArticulo) => {
        setArticulosClient((articulos) =>
          articulos.map((articulo) =>
            articulo.id === selectedArticulo.id
              ? nuevoArticulo
              : articulo
          )
        )
        toast.success("La publicacion se ha cancelado")
        onOpenChange(false)
      })
      .catch((error) => {
        toast.error("Ocurrio un error al cancelar la publicacion")
        console.error(error)
      })
  }

  async function handleCerrarPublicacion() {
    await cerrarPublicacion(selectedArticulo.id)
      .then(handleErrorClientSide(router))
      .then((nuevoArticulo) => {
        setArticulosClient((articulos) =>
          articulos.map((articulo) =>
            articulo.id === selectedArticulo.id
              ? nuevoArticulo
              : articulo
          )
        )
        toast.success("La publicacion se ha cerrado")
        onOpenChange(false)
      })
      .catch((error) => {
        toast.error("Ocurrio un error al cerrar la publicacion")
        console.error(error)
      })
  }

  return (
    <Modal isOpen={isOpen} onOpenChange={onOpenChange}>
      <ModalContent>
        {(onClose) => (
          <>
            <ModalHeader className="flex flex-col gap-1">
              Lista de Compradores de: {selectedArticulo.nombre}
            </ModalHeader>
            <ModalBody>
              {selectedArticulo.compradores.map((comprador) => (
                <div key={comprador.id}>
                  <h3>{comprador.nombre}</h3>
                  <p>{comprador.email}</p>
                </div>
              ))}
            </ModalBody>
            <ModalFooter className="flex-col">
              {selectedArticulo.compradores.length <
                selectedArticulo.minPersonas && (
                <p className="opacity-70">
                  La compra solo podra ser cerrada cuando se cumpla el minimo de
                  compradores
                </p>
              )}
              {

              <div className="w-full flex flex-row items-center gap-4">
                <Button
                  className="w-full flex-1"
                  color="danger"
                  onClick={handleCancelarPublicacion}
                >
                  Cancelar Publicacion
                </Button>
                <Button
                  className="w-full flex-1"
                  color="success"
                  onClick={handleCerrarPublicacion}
                  isDisabled={
                    selectedArticulo.compradores.length <
                    selectedArticulo.minPersonas
                  }
                >
                  Cerrar publicacion
                </Button>
              </div>
              }
            </ModalFooter>
          </>
        )}
      </ModalContent>
    </Modal>
  )
}

export default ModalCompradores
