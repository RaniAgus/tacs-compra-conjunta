export type CrearArticuloDTO = {
  nombre: string
  descripcion: string
  imagen: File
  link?: string
  deadline?: string
  minPersonas: number
  maxPersonas: number
  precio: number
  tipoPrecio: string
}
