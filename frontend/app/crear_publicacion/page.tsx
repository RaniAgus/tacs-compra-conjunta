import { Button, Checkbox, DatePicker, Input } from "@nextui-org/react"
import React from "react"

export default function CrearPublicacion() {
  return (
    <section className="max-w-xl mx-auto">
      <h1 className="font-semibold text-foreground/90 mb-8">
        Crear Publicacion
      </h1>
      <form action="" className="grid gap-2">
        <label htmlFor="titulo">Titulo</label>
        <Input type="text" name="titulo" id="titulo" placeholder="Ingrese el titulo"/>
        <label htmlFor="link">Link</label>
        <Input type="text" name="link" id="link" placeholder="Ingrese el link"/>
        <Checkbox>¿Costo Fijo?</Checkbox>
        <label htmlFor="costo">Costo</label>
        <Input type="number" name="costo" id="costo" placeholder="Ingrese el costo"/>
        <label htmlFor="fecha">Fecha Limite</label>
        <DatePicker />
        <label htmlFor="recibe">¿Que recibe el comprador?</label>
        <Input type="text" placeholder="Ingrese el que recibe el comprador"/>
        <Button
          type="submit"
          className="mt-4 dark:bg-blue-400 text-foreground-50 bg-blue-700 font-semibold text-lg"
        >
          Crear
        </Button>
      </form>
    </section>
  )
}
