import Estadisticas from "@/components/Estadisticas";
import { EstadisticaDTO } from "@/model/EstadisticaDTO";
import { getEstadisticas } from "@/service/EstadisticasService";
import { Button, Image } from "@nextui-org/react";
import { Metadata } from "next";
import Link from "next/link";
import { handleErrorServerSide } from './utils/ServerErrorUtils';

export const metadata: Metadata = {
  title: "Compra Conjunta",
  description: "La mejor pagina para comprar articulos entre varias personas",
};

const fetchData = async (): Promise<EstadisticaDTO> => {
  return handleErrorServerSide(await getEstadisticas())
}

export default async function Home() {
  const estadisticas = await fetchData()
  return (
    <section className="flex flex-col">
      <div className="flex flex-row">
        <div className="flex justify-center items-center w-full">
          <div className="flex flex-col gap-8 justify-center w-3/4 mt-8">
            <h1 className="font-semibold text-2xl text-foreground/90 text-center">
              La mejor pagina para comprar articulos entre varias personas
            </h1>
            <h2 className="font-semibold text-xl text-foreground/90 text-center">
              Aprovecha la oportunidad de comprar a costo variable, mientras mas compradores, menos pagas
            </h2>
            <h3 className="font-semibold text-lg text-foreground/90 text-center">
              ¿Que estas esperando? ¡Sumate!
            </h3>
            <Button color="primary" className="mx-auto" as={Link} href="/articulos">
              Ver Articulos
            </Button>
          </div>
        </div>
        <div className="sm:flex justify-center items-center center hidden w-full">
          <Image src="/homePic.png" alt="Hero" width={400} height={400} className="mx-auto" />
        </div>
      </div>
      <Estadisticas estadisticas={estadisticas} />
    </section>
  );
}
