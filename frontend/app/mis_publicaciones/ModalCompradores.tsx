import { ArticuloDTO } from "@/model/ArticuloDTO"
import { Button } from "@nextui-org/button"
import {
  Modal,
  ModalBody,
  ModalContent,
  ModalFooter,
  ModalHeader,
} from "@nextui-org/modal"

type ModalCompradoresProps = {
  isOpen: boolean
  onOpenChange: (isOpen: boolean) => void
  selectedArticulo: ArticuloDTO
}

function ModalCompradores({
  isOpen,
  onOpenChange,
  selectedArticulo,
}: ModalCompradoresProps) {
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
          </>
        )}
      </ModalContent>
    </Modal>
  )
}

export default ModalCompradores
