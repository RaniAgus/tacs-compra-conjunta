import { Card, CardBody, Image, Button } from "@nextui-org/react"
import { ArticuloDTO } from "@/model/ArticuloDTO"

export default async function Articulos() {
  const articulos = await fetch(process.env.URL+'/articulos')

  function cierraEn(deadline) {
    const now = new Date().getTime()
    const diff = new Date(deadline).getTime() - now
    if (diff < 0) return "Cerrado!"
    const days = Math.floor(diff / (1000 * 60 * 60 * 24))

    if (days > 0) return "Cierra en " + days + " dias!"
    if (days == 0) return "Cierra hoy"
  }

  function compradoresFaltan(maxPersonas, compradores) {
    return "¡Faltan " + (maxPersonas - compradores.length) + " compradores!"
  }

  return (
    <>
      {articulos.map((articulo) => (
        <Card
          key={articulo.id}
          isBlurred
          className="border-none bg-background/60 dark:bg-default-100/50 max-w-[610px] mx-auto"
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
                  {compradoresFaltan(
                    articulo.maxPersonas,
                    articulo.compradores
                  )}
                </h4>

                <div className="flex flex-row gap-2">
                  <svg
                    focusable="false"
                    aria-hidden="true"
                    viewBox="0 0 24 24"
                    data-testid="GroupIcon"
                    width={24}
                    height={24}
                  >
                    <path d="M16 11c1.66 0 2.99-1.34 2.99-3S17.66 5 16 5c-1.66 0-3 1.34-3 3s1.34 3 3 3m-8 0c1.66 0 2.99-1.34 2.99-3S9.66 5 8 5C6.34 5 5 6.34 5 8s1.34 3 3 3m0 2c-2.33 0-7 1.17-7 3.5V19h14v-2.5c0-2.33-4.67-3.5-7-3.5m8 0c-.29 0-.62.02-.97.05 1.16.84 1.97 1.97 1.97 3.45V19h6v-2.5c0-2.33-4.67-3.5-7-3.5"></path>
                  </svg>
                  <div>
                    {articulo.minPersonas} - {articulo.maxPersonas} personas
                  </div>
                </div>
              </div>

              <div className="flex flex-col justify-between flex-1">
                <Button className="self-end" isIconOnly aria-label="Link">
                  <svg
                    aria-hidden="true"
                    fill="none"
                    focusable="false"
                    height="16"
                    role="presentation"
                    viewBox="0 0 24 24"
                    width="16"
                    className="text-white dark:text-zinc-500"
                  >
                    <path
                      d="M16 12.9V17.1C16 20.6 14.6 22 11.1 22H6.9C3.4 22 2 20.6 2 17.1V12.9C2 9.4 3.4 8 6.9 8H11.1C14.6 8 16 9.4 16 12.9Z"
                      stroke="currentColor"
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      stroke-width="1.5"
                    ></path>
                    <path
                      d="M22 6.9V11.1C22 14.6 20.6 16 17.1 16H16V12.9C16 9.4 14.6 8 11.1 8H8V6.9C8 3.4 9.4 2 12.9 2H17.1C20.6 2 22 3.4 22 6.9Z"
                      stroke="currentColor"
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      stroke-width="1.5"
                    ></path>
                  </svg>
                </Button>
                <h3 className="text-end">${articulo.costo.precio}</h3>
                <Button className="bg-[#45D483]">Comprar</Button>
              </div>
            </div>
          </CardBody>
        </Card>
      ))}
    </>
  )
}
