import React from "react"

export default async function Estadisticas() {
  const estadisticas = await import(process.env.URL + "/estadisticas/basicas")
  return (
    <section>
      <div className="flex flex-row justify-between">
        <h3 className="font-semibold text-foreground/90">
          Cantidad de publicaciones creadas:
        </h3>
        <h3>{estadisticas.cantPublicacionesCreadas}</h3>
      </div>

      <div className="flex flex-row justify-between">
        <h3 className="font-semibold text-foreground/90">
          Cantidad de usuarios unicos:
        </h3>
        <h3>{estadisticas.cantUsuariosInteractivos}</h3>
      </div>
    </section>
  )
}
