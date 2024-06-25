import { cierraEn, compradoresFaltan, copiarLinkArticulo } from '@/app/utils/ArticuloUtils'
import { ArticuloDTO } from '@/model/ArticuloDTO'
import { Button, Card, CardBody, Image } from '@nextui-org/react'
import { FaUsers } from 'react-icons/fa'
import { IoCopy } from 'react-icons/io5'

interface Props {
    articulo: ArticuloDTO
    accion: JSX.Element
}

function Articulo(props: Props) {
    return (
        <Card
            key={props.articulo.id}
            isBlurred
            className="border-none bg-background/60 dark:bg-slate-800 max-w-[610px] mx-auto w-full"
            shadow="sm"
        >
            <CardBody>
                <div className="flex flex-col md:flex-row gap-4">
                    <Image
                        className="w-full object-cover max-h-[150px] rounded-lg"
                        src={props.articulo.imagen}
                        alt="Articulo"
                        width={150}
                        height={150}
                    />
                    <div className="flex flex-row flex-1 gap-8">
                        <div className="flex flex-col justify-between">
                            <h3 className="font-semibold text-foreground/90">
                                {props.articulo.nombre}
                            </h3>
                            <p className="text-small text-foreground/80">
                                {props.articulo.publicador.nombre}
                            </p>
                            {props.articulo.deadline && (
                                <h4>
                                    <b color="warning">{cierraEn(props.articulo.deadline)}</b>
                                </h4>
                            )}
                            <h4>
                                {compradoresFaltan(
                                    props.articulo.maxPersonas,
                                    props.articulo.compradores.length
                                )}
                            </h4>

                            <div className="flex flex-row gap-2 items-center">
                                <FaUsers />
                                {props.articulo.minPersonas} - {props.articulo.maxPersonas}{" "}
                                personas
                            </div>
                        </div>

                        <div className="flex flex-col justify-between flex-1">
                            <Button
                                className="self-end"
                                isIconOnly
                                aria-label="Link"
                                onClick={() => copiarLinkArticulo(props.articulo.id)}
                            >
                                <IoCopy />
                            </Button>
                            <h3 className="text-end">${props.articulo.costo.monto}</h3>
                            {props.accion}
                        </div>
                    </div>
                </div>
            </CardBody>
        </Card>
    )
}

export default Articulo