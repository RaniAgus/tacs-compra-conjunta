"use client"
import { useState } from "react"
import { useRouter } from "next/navigation"
import { Button, Checkbox, DatePicker, Input } from "@nextui-org/react"
import { DateValue } from "@internationalized/date"

import toast from "react-hot-toast"
import { CrearArticuloDTO } from "@/model/CrearArticuloDTO"
import { crearArticulo } from "@/service/ArticulosService"

type FormState = {
  nombre: { value: string; error: string }
  link: { value: string; error: string }
  imagen: { value: string; error: string }
  precio: { value: number; error: string }
  deadline: { value: DateValue | null; error: string }
  descripcion: { value: string; error: string }
  maxPersonas: { value: number; error: string }
  minPersonas: { value: number; error: string }
  tipoPrecio: string
}

export default function CrearPublicacion() {
  const router = useRouter()
  const [formState, setFormState] = useState<FormState>({
    nombre: { value: "", error: "" },
    link: { value: "", error: "" },
    imagen: { value: "", error: "" },
    precio: { value: 0, error: "" },
    deadline: {
      value: null,
      error: "",
    },
    descripcion: { value: "", error: "" },
    maxPersonas: { value: 0, error: "" },
    minPersonas: { value: 0, error: "" },
    tipoPrecio: "Fijo",
  })

  const isValidData = () => {
    const isValidNombre = formState.nombre.value !== ""
    const isValidDescripcion = formState.descripcion.value !== ""
    const isValidImagen = formState.imagen.value !== ""
    const isValidPrecio = formState.precio.value > 0
    const isValidMinPersonas =
      formState.minPersonas.value > 0 &&
      formState.minPersonas.value < formState.maxPersonas.value
    const isValidMaxPersonas =
      formState.maxPersonas.value > 0 &&
      formState.maxPersonas.value > formState.minPersonas.value

    if (!isValidNombre) {
      setFormState({
        ...formState,
        nombre: {
          value: formState.nombre.value,
          error: "El nombre es requerido",
        },
      })
    } else if (!isValidDescripcion) {
      setFormState({
        ...formState,
        descripcion: {
          value: formState.descripcion.value,
          error: "La descripcion es requerida",
        },
      })
    } else if (!isValidImagen) {
      setFormState({
        ...formState,
        imagen: {
          value: formState.imagen.value,
          error: "La imagen es requerida",
        },
      })
    } else if (!isValidPrecio) {
      setFormState({
        ...formState,
        precio: {
          value: formState.precio.value,
          error: "El precio es requerido",
        },
      })
    } else if (!isValidMinPersonas) {
      setFormState({
        ...formState,
        minPersonas: {
          value: formState.minPersonas.value,
          error: "El minimo de personas es requerido",
        },
      })
      if (formState.minPersonas.value > formState.maxPersonas.value) {
        setFormState({
          ...formState,
          minPersonas: {
            value: formState.minPersonas.value,
            error: "El minimo de personas debe ser menor al maximo",
          },
          maxPersonas: {
            value: formState.maxPersonas.value,
            error: "El maximo de personas debe ser mayor al minimo",
          },
        })
      }
    } else if (!isValidMaxPersonas) {
      setFormState({
        ...formState,
        maxPersonas: {
          value: formState.maxPersonas.value,
          error: "El maximo de personas es requerido",
        },
      })
    }

    return (
      isValidNombre &&
      isValidDescripcion &&
      isValidImagen &&
      isValidPrecio &&
      isValidMinPersonas &&
      isValidMaxPersonas
    )
  }

  async function handleSubmit() {
    if (!isValidData()) return

    const crearArticuloDTO: CrearArticuloDTO = {
      nombre: formState.nombre.value,
      descripcion: formState.descripcion.value,
      imagen: formState.imagen.value,
      link: formState.link.value ? formState.link.value : undefined,
      deadline: formState.deadline.value
        ? formState.deadline.value.toString()
        : undefined,
      minPersonas: formState.minPersonas.value,
      maxPersonas: formState.maxPersonas.value,
      precio: formState.precio.value,
      tipoPrecio: formState.tipoPrecio,
    }

    await crearArticulo(crearArticuloDTO)
      .then((_) => {
        toast.success("Publicacion creada exitosamente")
        router.replace("/")
      })
      .catch((error) => toast.error(error.message))
  }

  return (
    <section className="max-w-xl mx-auto">
      <h1 className="font-semibold text-foreground/90 mb-8">
        Crear Publicacion
      </h1>
      <form action="" className="grid gap-2" onSubmit={handleSubmit}>
        <Input
          required
          label="Nombre"
          labelPlacement="outside"
          type="text"
          name="nombre"
          id="nombre"
          placeholder="Ingrese el nombre"
          onChange={(e) =>
            setFormState({
              ...formState,
              nombre: { value: e.target.value, error: "" },
            })
          }
          errorMessage={formState.nombre.error}
        />
        <Input
          label="Link"
          labelPlacement="outside"
          type="text"
          name="link"
          id="link"
          placeholder="Ingrese el link"
          onChange={(e) =>
            setFormState({
              ...formState,
              link: { value: e.target.value, error: "" },
            })
          }
          errorMessage={formState.link.error}
        />
        <Checkbox
          name="tipoPrecio"
          id="tipoPrecio"
          required
          onChange={(e) =>
            setFormState({
              ...formState,
              tipoPrecio: e.target.checked ? "Fijo" : "Variable",
            })
          }
          checked={formState.tipoPrecio === "Fijo"}
        >
          ¿Precio Fijo?
        </Checkbox>
        <Input
          required
          label="Precio"
          labelPlacement="outside"
          type="number"
          name="precio"
          id="precio"
          placeholder="Ingrese el precio"
          startContent={
            <div className="pointer-events-none flex items-center">
              <span className="text-default-400 text-small">$</span>
            </div>
          }
          onChange={(e) =>
            setFormState({
              ...formState,
              precio: { value: Number(e.target.value), error: "" },
            })
          }
          errorMessage={formState.precio.error}
        />
        <div className="grid grid-cols-2 gap-4">
          <Input
            required
            label="Minimo de personas"
            labelPlacement="outside"
            type="number"
            name="minPersonas"
            id="minPersonas"
            placeholder="Ingrese el minimo de personas"
            onChange={(e) =>
              setFormState({
                ...formState,
                minPersonas: { value: Number(e.target.value), error: "" },
              })
            }
            errorMessage={formState.minPersonas.error}
          />
          <Input
            required
            label="Maximo de personas"
            labelPlacement="outside"
            type="number"
            name="maxPersonas"
            id="maxPersonas"
            placeholder="Ingrese el maximo de personas"
            onChange={(e) =>
              setFormState({
                ...formState,
                maxPersonas: { value: Number(e.target.value), error: "" },
              })
            }
            errorMessage={formState.maxPersonas.error}
          />
        </div>
        <DatePicker
          label="Fecha Limite"
          labelPlacement="outside"
          name="deadline"
          id="deadline"
          value={formState.deadline.value}
          onChange={(e) =>
            setFormState({
              ...formState,
              deadline: { value: e, error: "" },
            })
          }
          errorMessage={formState.deadline.error}
        />
        <Input
          required
          label="Descripcion"
          labelPlacement="outside"
          type="text"
          name="descripcion"
          placeholder="Ingrese el que recibe el comprador"
          onChange={(e) =>
            setFormState({
              ...formState,
              descripcion: { value: e.target.value, error: "" },
            })
          }
          errorMessage={formState.descripcion.error}
        />
        <Button type="submit" color="primary">
          Crear
        </Button>
      </form>
    </section>
  )
}
