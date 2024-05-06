import { ArticuloDTO } from "@/model/ArticuloDTO"
import {
  Modal,
  ModalContent,
  ModalHeader,
  ModalBody,
  ModalFooter,
  Button,
} from "@nextui-org/react"
import FormComprar from "./FormComprar"

type ModalComprarProps = {
  isOpen: boolean
  onOpenChange: () => void
  selectedArticulo: ArticuloDTO
}

function ModalComprar({
  isOpen,
  onOpenChange,
  selectedArticulo,
}: ModalComprarProps) {
  return (
    <Modal isOpen={isOpen} onOpenChange={onOpenChange}>
      <ModalContent>
        {(onClose) => (
          <>
            <ModalHeader className="flex flex-col gap-1">
              {selectedArticulo.nombre} - ${selectedArticulo.costo.monto}
            </ModalHeader>
            <ModalBody>
              <FormComprar articuloId={selectedArticulo.id} onClose={onClose} />
            </ModalBody>
            <ModalFooter>
              <Button color="danger" variant="light" onPress={onClose}>
                Cancelar
              </Button>
              <Button
                type="submit"
                form={`form-${selectedArticulo.id}`}
                color="success"
              >
                Comprar
              </Button>
            </ModalFooter>
          </>
        )}
      </ModalContent>
    </Modal>
  )
}

export default ModalComprar
