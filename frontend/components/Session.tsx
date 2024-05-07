import { UsuarioDTO } from '@/model/UsuarioDTO'
import { Avatar, Button, Dropdown, DropdownItem, DropdownMenu, DropdownTrigger, NavbarItem } from '@nextui-org/react'
import Link from 'next/link'
import { SpecialLink } from './Navbar'

interface Props {
    usuario: UsuarioDTO | null | undefined
    handleLogout: () => void
}

function Session(props: Props) {
    return (
        !props.usuario ? (
            <>
                <NavbarItem className="hidden sm:flex">
                    <Link href="/login">Iniciar Sesion</Link>
                </NavbarItem>
                <NavbarItem className="hidden sm:flex">
                    <SpecialLink href="/register" color="warning">
                        Registrarse
                    </SpecialLink>
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
                        <p className="font-semibold">Sesion Iniciada como</p>
                        <p className="font-semibold">{props.usuario?.email}</p>
                    </DropdownItem>
                    <DropdownItem key="settings">Ajustes</DropdownItem>
                    <DropdownItem key="logout" color="danger" onClick={props.handleLogout}>
                        Cerrar Sesion
                    </DropdownItem>
                </DropdownMenu>
            </Dropdown>
        )
    )
}

export default Session