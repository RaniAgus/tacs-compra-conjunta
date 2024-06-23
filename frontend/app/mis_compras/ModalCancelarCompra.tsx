import { ArticuloDTO } from "@/model/ArticuloDTO"
import { cancelarCompraArticulo } from "@/service/ArticulosService"
import {
  Modal,
  ModalContent,
  ModalHeader,
  ModalBody,
  ModalFooter,
  Button,
} from "@nextui-org/react"
import toast from "react-hot-toast"
import { handleErrorClientSide } from '../utils/ClientErrorUtils'
import { useRouter } from 'next/navigation'

type ModalCancelarCompraProps = {
  isOpen: boolean
  onOpenChange: () => void
  selectedArticulo: ArticuloDTO
}

function ModalCancelarCompra({
  isOpen,
  onOpenChange,
  selectedArticulo,
}: ModalCancelarCompraProps) {
  const router = useRouter()

  async function handleCancelarCompra(onClose: () => void) {
    await cancelarCompraArticulo(selectedArticulo.id)
      .then(handleErrorClientSide(router))
      .then(() => {
        toast.success("Compra cancelada exitosamente")
        onClose()
      })
      .catch(() => {
        toast.error("Error al cancelar la compra")
      })
  }

  return (
    <Modal isOpen={isOpen} onOpenChange={onOpenChange}>
      <ModalContent>
        {(onClose) => (
          <>
            <ModalHeader className="flex flex-col gap-1">
              Cancelar Compra
            </ModalHeader>
            <ModalBody>
              <h3>
                ¿Estás seguro que deseas cancelar la compra del articulo{" "}
                {selectedArticulo.nombre}?
              </h3>
              {selectedArticulo.costo.tipo === "TOTAL" && (
                <h4>
                  Al cancelar la compra se afectara al precio que pagaran el
                  resto de compradores
                </h4>
              )}
            </ModalBody>
            <ModalFooter>
              <Button color="primary" variant="light" onPress={onClose}>
                No, mantener compra
              </Button>
              <Button
                color="danger"
                onClick={() => handleCancelarCompra(onClose)}
              >
                Si, cancelar compra
              </Button>
            </ModalFooter>
          </>
        )}
      </ModalContent>
    </Modal>
  )
}

export default ModalCancelarCompra
