import { user } from "@/app/layout"
import { obtenerUsuario } from "@/service/UsuarioService"
import { useAtom } from "jotai"
import { useEffect } from "react"

export default function useUsuario() {
    const [usuario, setUsuario] = useAtom(user)

    useEffect(() => {
        /* if !usuario and is not the initial render */
        if (usuario !== undefined && !usuario && typeof window !== 'undefined') {
            const usuarioDeToken = obtenerUsuario()
            Promise.resolve(usuarioDeToken)
                .then(usuario => setUsuario(usuario))
                .catch(() => setUsuario(undefined))
        }
    }, [usuario, setUsuario])

    return usuario
}