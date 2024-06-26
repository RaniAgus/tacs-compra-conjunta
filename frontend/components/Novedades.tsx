import { handleErrorClientSide } from "@/app/utils/ClientErrorUtils"
import { getNovedades, leerNovedad, leerTodasNovedades } from "@/service/NovedadesService"
import { Button, Dropdown, DropdownItem, DropdownMenu, DropdownTrigger } from "@nextui-org/react"
import { useRouter } from "next/navigation"
import { useEffect, useState } from "react"

export const Novedades = () => {
    const [unreadNotifications, setUnreadNotifications] = useState<(NovedadDTO & { isRead?: true })[]>([])
    const [generate, setGenerate] = useState(false)
    const router = useRouter();
    useEffect(() => { import ('@github/relative-time-element') }, []);
    useEffect(() => {
        getNovedades()
            .then(handleErrorClientSide(router))
            .then((novedades) => setUnreadNotifications((notifications) => {
                const newNotifications = novedades.filter((novedad) => !notifications.some((notification) => notification.id === novedad.id))
                return [...newNotifications, ...notifications];
            }))
    }, [router, generate])


    const markAsRead = async (id: string) => {
        await leerNovedad(id).then(handleErrorClientSide(router))
        setUnreadNotifications(unreadNotifications.map((notification) => notification.id === id ?
            { ...notification, isRead: true } : notification))
    }

    const markAllAsRead = async () => {
        await leerTodasNovedades().then(handleErrorClientSide(router))
        setUnreadNotifications(unreadNotifications.map((notification) => ({ ...notification, isRead: true })));
    }

    const hasUnreadNotifications = unreadNotifications.filter((notification) => !notification.isRead).length > 0

    return (
        <Dropdown className="relative" closeOnSelect={false}>
            <DropdownTrigger>
                <Button variant="light" isIconOnly={true}>
                    <BellIcon className="h-6 w-6" />
                    {hasUnreadNotifications && <span className="absolute top-1 right-1 h-2 w-2 rounded-lg bg-red-500" />}
                </Button>
            </DropdownTrigger>

            <DropdownMenu className="w-[400px] p-4 max-h-screen overflow-auto">
                <DropdownItem
                    className="nextuidropdown"
                    onClick={(e) => e.preventDefault()}
                >
                    <div className="flex flex-row gap-2 items-center justify-between">
                        <h3 className="text-lg font-medium">Novedades</h3>
                        <div className="flex items-center gap-2">
                            <Button
                                variant="light"
                                size="sm"
                                onClick={markAllAsRead}
                                isDisabled={!hasUnreadNotifications}
                            >
                                Leer todas
                            </Button>
                            <Button variant="light" isIconOnly={true} onClick={() => setGenerate(g => !g)}>
                                <RefreshCwIcon className="h-4 w-4" />
                            </Button>
                        </div>
                    </div>
                </DropdownItem>
                <DropdownItem className="nextuidropdown h-full" onClick={(e) => e.preventDefault()}>
                    {unreadNotifications.map((notification) => (
                        <div
                            key={notification.id}
                            className={`grid grid-cols-[1fr_9em_2em] items-center rounded-md p-2 transition-colors ${notification.isRead
                                ? "opacity-60 cursor-default hover:bg-transparent"
                                : "cursor-pointer hover:bg-neutral-200 dark:hover:bg-neutral-700"
                                }`}
                            onClick={notification.isRead ? undefined : () => markAsRead(notification.id)}
                        >
                            <div>
                                <div className="flex flex-row gap-2">
                                    <p className="font-medium">{notification.nombre}</p>
                                </div>
                                <p className="text-sm text-neutral-600 dark:text-neutral-400">
                                    {notification.estado === "VENDIDO" ? `Comprado por \$${notification.monto} ` : "Cancelado "}
                                </p>
                            </div>
                            <relative-time datetime={notification.hora}></relative-time>
                            {!notification.isRead && <span className="z-10 ml-4 h-2 w-2 rounded-full bg-red-500" />}
                        </div>
                    ))}
                </DropdownItem>

            </DropdownMenu>
        </Dropdown>
    )
}

function BellIcon(props: any) {
    return (
        <svg
            {...props}
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            strokeWidth="2"
            strokeLinecap="round"
            strokeLinejoin="round"
        >
            <path d="M6 8a6 6 0 0 1 12 0c0 7 3 9 3 9H3s3-2 3-9" />
            <path d="M10.3 21a1.94 1.94 0 0 0 3.4 0" />
        </svg>
    )
}


function RefreshCwIcon(props: any) {
    return (
        <svg
            {...props}
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            strokeWidth="2"
            strokeLinecap="round"
            strokeLinejoin="round"
        >
            <path d="M3 12a9 9 0 0 1 9-9 9.75 9.75 0 0 1 6.74 2.74L21 8" />
            <path d="M21 3v5h-5" />
            <path d="M21 12a9 9 0 0 1-9 9 9.75 9.75 0 0 1-6.74-2.74L3 16" />
            <path d="M8 16H3v5" />
        </svg>
    )
}
