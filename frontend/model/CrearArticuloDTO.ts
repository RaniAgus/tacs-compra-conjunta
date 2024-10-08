type CrearArticuloDTO = {
  nombre: string
  descripcion: string
  imagen: string
  link?: string
  deadline?: string
  minPersonas: number
  maxPersonas: number
  precio: number
  tipoPrecio: TipoPrecio
}

type TipoPrecio = "POR_PERSONA" | "TOTAL"
