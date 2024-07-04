"use client"

import { useAtom } from 'jotai'
import { user } from '../layout'
import { useRouter } from 'next/navigation'
import { useEffect, useState } from 'react'
import { handleErrorClientSide } from '../utils/ClientErrorUtils'
import { iniciarSesion } from '@/service/AuthService'
import toast from 'react-hot-toast'
import { Button, Input } from '@nextui-org/react'
import { IoEyeOffSharp, IoEyeSharp } from 'react-icons/io5'
import Link from 'next/link'
import { LOGIN_ERROR } from '../utils/constants'

function Login() {
    const [usuario, setUsuario] = useAtom(user)
    const router = useRouter()
    const [isVisible, setIsVisible] = useState(false)
    const [formState, setFormState] = useState({
        username: { value: "", error: "" },
        password: { value: "", error: "" }
    })

    useEffect(() => {
      setUsuario(null)
    }, [setUsuario]);

    const isValidData = () => {
        const isValidUsername = formState.username.value !== ""
        const isValidPassword = formState.password.value !== ""

        if (!isValidUsername && !isValidPassword) {
            setFormState({
                username: { value: formState.username.value, error: "El nombre de usuario es requerido" },
                password: { value: formState.password.value, error: "La contraseña es requerida" }
            })
        } else if (!isValidUsername) {
            setFormState({
                username: { value: formState.username.value, error: "El nombre de usuario es requerido" },
                password: { value: formState.password.value, error: "" }
            })
        } else if (!isValidPassword) {
            setFormState({
                username: { value: formState.username.value, error: "" },
                password: { value: formState.password.value, error: "La contraseña es requerida" }
            })
        }

        return isValidUsername && isValidPassword
    }

    async function handleSubmit(event: React.FormEvent<HTMLFormElement>) {
        event.preventDefault()

        if (!isValidData()) return

        const iniciarSesionDTO: IniciarSesionDTO = {
            nombreDeUsuario: formState.username.value,
            contrasenia: formState.password.value
        }

        await iniciarSesion(iniciarSesionDTO)
            .then(handleErrorClientSide())
            .then(() => {
                setUsuario(null) // This is to trigger the useUsuario hook
                toast.success("Inicio de sesion exitoso")
                router.push("/")
            })
            .catch((error) => error.message === LOGIN_ERROR ?
                  toast.error("Usuario o contraseña incorrectos")
                : toast.error(error.message)
            );

    }

    return (
        <div className="flex flex-col gap-8 items-center w-full max-w-xl mx-auto">
            <h1 className="text-center font-bold text-2xl">Inciar Sesion</h1>
            <form className="flex flex-col items-center gap-4" onSubmit={handleSubmit}>
                <Input
                    placeholder="Ingrese su nombre de usuario"
                    label="Nombre de usuario"
                    labelPlacement="outside"
                    isClearable
                    value={formState.username.value}
                    onChange={(e) => setFormState({ ...formState, username: { value: e.target.value, error: "" } })}
                    isInvalid={formState.username.error !== ""}
                    errorMessage={formState.username.error}
                />
                <Input
                    placeholder="Ingrese su contraseña"
                    label="Contraseña"
                    labelPlacement="outside"
                    endContent={
                        <button className="focus:outline-none" type="button" onClick={() => setIsVisible(!isVisible)}>
                            {isVisible ? (
                                <IoEyeSharp />
                            ) : (
                                <IoEyeOffSharp />
                            )}
                        </button>
                    }
                    type={isVisible ? "text" : "password"}
                    value={formState.password.value}
                    onChange={(e) => setFormState({ ...formState, password: { value: e.target.value, error: "" } })}
                    isInvalid={formState.password.error !== ""}
                    errorMessage={formState.password.error}
                />
                <Button type="submit" color="primary" fullWidth>Iniciar Sesion</Button>
            </form>
            <div className="flex flex-col items-center gap-4">
                <span>
                    ¿No tienes cuenta? <Link href="/register" className="underline">Registrate</Link>
                </span>
            </div>
        </div>
    )
}

export default Login
