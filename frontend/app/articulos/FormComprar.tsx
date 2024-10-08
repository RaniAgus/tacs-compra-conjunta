"use client"
import React, { useState } from "react"
import { Input } from "@nextui-org/react"
import toast from "react-hot-toast"
import { FaCreditCard } from "react-icons/fa"

import { comprarArticulo } from "@/service/ArticulosService"
import { handleErrorClientSide } from "../utils/ClientErrorUtils"
import { useRouter } from "next/navigation"

type FormComprarState = {
  articuloId: string
  onClose: () => void
}

type FormState = {
  numeroTarjeta: { value: string | undefined; error: string }
  nombreTitular: { value: string | undefined; error: string }
  documentoIdentidad: { value: string | undefined; error: string }
  fechaExpiracion: { value: string | undefined; error: string }
  cvv: { value: string | undefined; error: string }
}

function FormComprar({ articuloId, onClose }: FormComprarState) {
  const router = useRouter()
  const [formState, setFormState] = useState<FormState>({
    numeroTarjeta: { value: undefined, error: "" },
    nombreTitular: { value: undefined, error: "" },
    documentoIdentidad: { value: undefined, error: "" },
    fechaExpiracion: { value: undefined, error: "" },
    cvv: { value: undefined, error: "" },
  })

  function handleChangeInput(event: React.ChangeEvent<HTMLInputElement>) {
    const { value, maxLength, name } = event.target
    const formatedValue = value.slice(0, maxLength)

    if (name === "fechaExpiracion") {
      const formatedDate = value
        .split("-")
        .map((part, index) => {
          if (index === 0) return part
          return part.length === 1 ? `0${part}` : part
        })
        .join("-")
      setFormState({
        ...formState,
        [name]: { value: formatedDate, error: "" },
      })
    } else {
      setFormState({
        ...formState,
        [name]: { value: formatedValue, error: "" },
      })
    }
  }

  function isValidData() {
    const isValidNumeroTarjeta = formState.numeroTarjeta.value?.length === 16
    const isValidNombreTitular = (/^[a-zA-ZÀ-ÿ' -]+$/).test(formState.nombreTitular.value || "")
    const isValidDocumentoIdentidad =
      formState.documentoIdentidad.value?.length === 8
    const isValidFechaExpiracion = formState.fechaExpiracion.value?.length === 7
    const isValidCVV = formState.cvv.value?.length === 3

    const newFormState = { ...formState }

    if (!isValidNumeroTarjeta) {
      newFormState.numeroTarjeta = {
        value: formState.numeroTarjeta.value,
        error: "El numero de tarjeta debe tener 16 digitos",
      }
    }
    if (!isValidNombreTitular) {
      newFormState.nombreTitular = {
        value: formState.nombreTitular.value,
        error: "El nombre del titular es requerido",
      }
    }
    if (!isValidDocumentoIdentidad) {
      newFormState.documentoIdentidad = {
        value: formState.documentoIdentidad.value,
        error: "El documento de identidad debe tener 8 digitos",
      }
    }
    if (!isValidFechaExpiracion) {
      newFormState.fechaExpiracion = {
        value: formState.fechaExpiracion.value,
        error: "La fecha de expiracion es requerida",
      }
    }
    if (!isValidCVV) {
      newFormState.cvv = {
        value: formState.cvv.value,
        error: "El CVV debe tener 3 digitos",
      }
    }
    setFormState(newFormState)

    return (
      isValidNumeroTarjeta &&
      isValidNombreTitular &&
      isValidDocumentoIdentidad &&
      isValidFechaExpiracion &&
      isValidCVV
    )
  }

  async function handleSubmit(event: React.FormEvent<HTMLFormElement>) {
    event.preventDefault()
    // Envía los datos del formulario
    if (!isValidData()) {
      return
    }
    await comprarArticulo(articuloId)
      .then(handleErrorClientSide(router))
      .then(() => {
        toast.success("Articulo comprado exitosamente")
        onClose()
      })
      .catch((error) => {
        toast.error(error.message)
      })
  }

  return (
    <form
      id={`form-${articuloId}`}
      className="grid gap-2"
      onSubmit={handleSubmit}
    >
      <Input
        isRequired
        id="numeroTarjeta"
        name="numeroTarjeta"
        label="Numero de tarjeta"
        labelPlacement="outside"
        placeholder="1234 5678 9012 3456"
        type="number"
        maxLength={16}
        endContent={
          <div className="pointer-events-none flex items-center">
            <FaCreditCard />
          </div>
        }
        value={formState.numeroTarjeta.value}
        onChange={handleChangeInput}
        isInvalid={formState.numeroTarjeta.error.length > 0}
        errorMessage={formState.numeroTarjeta.error}
      />
      <Input
        isRequired
        id="nombreTitular"
        name="nombreTitular"
        label="Nombre del titular"
        labelPlacement="outside"
        placeholder="Ingrese el nombre del titular"
        type="text"
        maxLength={50}
        value={formState.nombreTitular.value}
        onChange={handleChangeInput}
        isInvalid={formState.nombreTitular.error.length > 0}
        errorMessage={formState.nombreTitular.error}
      />
      <Input
        isRequired
        id="documentoIdentidad"
        name="documentoIdentidad"
        label="Documento de identidad"
        labelPlacement="outside"
        placeholder="12 345 678"
        type="number"
        maxLength={8}
        value={formState.documentoIdentidad.value}
        onChange={handleChangeInput}
        isInvalid={formState.documentoIdentidad.error.length > 0}
        errorMessage={formState.documentoIdentidad.error}
      />
      <div className="grid grid-cols-2 gap-4">
        <Input
          isRequired
          id="fechaExpiracion"
          name="fechaExpiracion"
          label="Fecha de expiracion"
          labelPlacement="outside"
          type="month"
          value={formState.fechaExpiracion.value}
          onChange={handleChangeInput}
          isInvalid={formState.fechaExpiracion.error.length > 0}
          errorMessage={formState.fechaExpiracion.error}
        />
        <Input
          isRequired
          id="cvv"
          name="cvv"
          label="CVV"
          labelPlacement="outside"
          placeholder="123"
          maxLength={3}
          type="number"
          value={formState.cvv.value}
          onChange={handleChangeInput}
          isInvalid={formState.cvv.error.length > 0}
          errorMessage={formState.cvv.error}
        />
      </div>
    </form>
  )
}

export default FormComprar
