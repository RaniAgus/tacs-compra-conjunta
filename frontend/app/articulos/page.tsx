import { Card, CardBody, Image, Button } from "@nextui-org/react"
import { ArticuloDTO } from "@/model/ArticuloDTO"
import { getArticulos } from "@/service/ArticulosService"
import { FaUsers } from "react-icons/fa"
import { IoCopy } from "react-icons/io5"
import { UsuarioDTO } from "@/model/UsuarioDTO"

const fetchData = async (): Promise<ArticuloDTO[]> => {
  return await getArticulos()
}

export default async function Articulos() {
  const articulos = await fetchData()

  function cierraEn(deadline: string) {
    const now = new Date().getTime()
    const diff = new Date(deadline).getTime() - now
    if (diff < 0) return "Cerrado!"
    const days = Math.floor(diff / (1000 * 60 * 60 * 24))

    if (days > 0) return "Cierra en " + days + " dias!"
    if (days == 0) return "Cierra hoy"
  }

  function compradoresFaltan(maxPersonas: number, compradores: UsuarioDTO[]) {
    return "¡Faltan " + (maxPersonas - compradores.length) + " compradores!"
  }

  return (
    <div className="flex flex-col gap-4 pb-10">
      {articulos.map((articulo) => (
        <Card
          key={articulo.id}
          isBlurred
          className="border-none bg-background/60 dark:bg-default-100/50 max-w-[610px] mx-auto w-full"
          shadow="sm"
        >
          <CardBody>
            <div className="flex flex-col md:flex-row gap-4">
              <Image
                src={articulo.imagen}
                alt="Articulo"
                width={150}
                height={150}
                className="rounded-lg"
              />

              <div className="flex flex-col justify-between">
                <h3 className="font-semibold text-foreground/90">
                  {articulo.nombre}
                </h3>
                <p className="text-small text-foreground/80">
                  {articulo.publicador.nombre}
                </p>
                <h4>{cierraEn(articulo.deadline)}</h4>
                <h4>
                  {compradoresFaltan(articulo.maxPersonas, articulo.compradores)}
                </h4>

                <div className="flex flex-row gap-2 items-center">
                  <FaUsers />{articulo.minPersonas} - {articulo.maxPersonas} personas
                </div>
              </div>

              <div className="flex flex-col justify-between flex-1">
                <Button className="self-end" isIconOnly aria-label="Link">
                  <IoCopy />
                </Button>
                <h3 className="text-end">${articulo.costo.monto}</h3>
                <Button color="success">Comprar</Button>
              </div>
            </div>
          </CardBody>
        </Card>
      ))}
    </div>
  )
}
