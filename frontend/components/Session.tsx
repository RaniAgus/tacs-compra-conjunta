import { user } from '@/app/layout'
import { UsuarioDTO } from '@/model/UsuarioDTO'
import { cerrarSesion } from '@/service/AuthService'
import { Avatar, Button, Dropdown, DropdownItem, DropdownMenu, DropdownTrigger, NavbarItem } from '@nextui-org/react'
import { useAtom } from 'jotai'
import Link from 'next/link'
import { useRouter } from 'next/navigation'
import toast from 'react-hot-toast'

interface Props {
    usuario: UsuarioDTO | null
}

function Session(props: Props) {
    const router = useRouter()
    const [usuario, setUsuario] = useAtom(user)

    const handleLogout = async () => {
        await cerrarSesion().then(() => {
            setUsuario(null)
            router.replace("/login")
            toast.success("Cierre de sesion exitoso")
        }).catch((error) => toast.error(error.message))
    }

    return (
        !props.usuario ? (
            <>
                <NavbarItem className="hidden lg:flex">
                    <Link href="/login">Iniciar Sesion</Link>
                </NavbarItem>
                <NavbarItem>
                    <Button as={Link} color="warning" href="#" variant="flat">
                        Registrarse
                    </Button>
                </NavbarItem>
            </>
        ) : (


            <Dropdown placement="bottom-end">
                <DropdownTrigger>
                    <Avatar
                        isBordered
                        as="button"
                        className="transition-transform"
                        color="secondary"
                        name="Jason Hughes"
                        size="sm"
                        src="https://i.pravatar.cc/150?u=a042581f4e29026704d"
                    />
                </DropdownTrigger>
                <DropdownMenu aria-label="Profile Actions" variant="flat">
                    <DropdownItem key="profile" className="h-14 gap-2">
                        <p className="font-semibold">Signed in as</p>
                        <p className="font-semibold">zoey@example.com</p>
                    </DropdownItem>
                    <DropdownItem key="settings">My Settings</DropdownItem>
                    <DropdownItem key="team_settings">Team Settings</DropdownItem>
                    <DropdownItem key="analytics">Analytics</DropdownItem>
                    <DropdownItem key="system">System</DropdownItem>
                    <DropdownItem key="configurations">Configurations</DropdownItem>
                    <DropdownItem key="help_and_feedback">Help & Feedback</DropdownItem>
                    <DropdownItem key="logout" color="danger" onClick={handleLogout}>
                        Log Out
                    </DropdownItem>
                </DropdownMenu>
            </Dropdown>
        )
    )
}

export default Session