"use client"
import { EstadisticaDTO } from "@/model/EstadisticaDTO"
import Rol from "./Rol"

interface Props {
  estadisticas: EstadisticaDTO
}

export default function Estadisticas(props: Props) {
  return (
    <Rol rol="ADMIN">
      <section className="mt-4">
        <h2 className="text-2xl font-semibold text-foreground/90">
          Estadisticas
        </h2>
        <div className="flex flex-row gap-2 mt-4">
          <h3 className="font-semibold text-foreground/90">
            Cantidad de publicaciones creadas:
          </h3>
          <h3>{props.estadisticas.cantPublicacionesCreadas}</h3>
        </div>

        <div className="flex flex-row gap-2">
          <h3 className="font-semibold text-foreground/90">
            Cantidad de usuarios unicos:
          </h3>
          <h3>{props.estadisticas.cantUsuariosInteractivos}</h3>
        </div>
      </section>
    </Rol>
  )
}
