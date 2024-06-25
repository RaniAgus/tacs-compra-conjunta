import { handleErrorClientSide } from "@/app/utils/ClientErrorUtils"
import { getNovedades, leerNovedad, leerTodasNovedades } from "@/service/NovedadesService"
import { Button, Dropdown, DropdownItem, DropdownMenu, DropdownTrigger } from "@nextui-org/react"
import { useRouter } from "next/navigation"
import { useEffect, useState } from "react"

export const Novedades = () => {
    const [unreadNotifications, setUnreadNotifications] = useState<NovedadDTO[]>([] as NovedadDTO[])
    const router = useRouter();

    const setNovedades = async () => {
        const novedades = await getNovedades().then(handleErrorClientSide(router))
        if (novedades) {
            setUnreadNotifications(novedades)
        }
    }

    useEffect(() => {
        setNovedades()
    }, [])


    const markAsRead = async (id) => {
        await leerNovedad(id).then(handleErrorClientSide(router))
        setUnreadNotifications(unreadNotifications.filter((notification) => notification.id !== id))
    }
    const markAllAsRead = async () => {
        await leerTodasNovedades().then(handleErrorClientSide(router))
        setUnreadNotifications([])
    }
    const hasUnreadNotifications = unreadNotifications.length > 0

    return (
        <Dropdown className="relative">
            <DropdownTrigger>
                <Button variant="ghost">
                    <BellIcon className="h-6 w-6" />
                    {hasUnreadNotifications && <span className="absolute top-0 right-0 h-2 w-2 rounded-full bg-red-500" />}
                </Button>
            </DropdownTrigger>

            <DropdownMenu className="w-[400px] p-4 max-h-screen overflow-auto bg-gray-200 dark:bg-gray-500 rounded-lg">
                <DropdownItem className="nextuidropdown">
                    <div className="flex flex-row gap-2 items-center">
                        <h3 className="text-lg font-medium">Novedades</h3>
                        <div className="flex items-center gap-2">
                            <Button
                                variant="ghost"
                                size="sm"
                                onClick={markAllAsRead}
                                className={`${!hasUnreadNotifications ? "hidden" : ""}`}
                            >
                                Leer todas
                            </Button>
                            <Button variant="ghost" onClick={setNovedades}>
                                <RefreshCwIcon className="h-4 w-4" />
                            </Button>
                        </div>
                    </div>
                </DropdownItem>
                <DropdownItem className="nextuidropdown h-full">
                    {unreadNotifications.map((notification) => (
                        <div
                            key={notification.id}
                            className={`flex items-center justify-between rounded-md p-2 transition-colors ${notification
                                ? "opacity-50 cursor-default hover:bg-transparent"
                                : "cursor-pointer hover:bg-muted novedad"
                                }`}
                            onClick={() => markAsRead(notification.id)}
                        >
                            <div>
                                <div className="flex flex-row gap-2">
                                    <p className="font-medium">{notification.nombre}</p>
                                    <p className="text-sm">${notification.monto}</p>
                                </div>
                                <p className="text-sm text-muted-foreground">{notification.estado} el {new Date(notification.hora).toUTCString()}</p>
                            </div>
                            {!notification && <span className="ml-4 h-2 w-2 rounded-full bg-red-500" />}
                        </div>
                    ))}
                </DropdownItem>

            </DropdownMenu>
        </Dropdown>
    )
}

function BellIcon(props) {
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


function RefreshCwIcon(props) {
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