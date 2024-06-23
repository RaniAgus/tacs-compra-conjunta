"use client"
import { useAtom } from 'jotai'
import React, { useState } from 'react'
import { user } from '../layout'
import { useRouter } from 'next/navigation'
import { registrarse } from '@/service/AuthService'
import toast from 'react-hot-toast'
import { Button, Input } from '@nextui-org/react'
import { IoEyeOffSharp, IoEyeSharp } from 'react-icons/io5'
import Link from 'next/link'
import { handleErrorClientSide } from '../utils/ClientErrorUtils'

function Register() {
  const [usuario, setUsuario] = useAtom(user)
  const router = useRouter()
  const [isVisible, setIsVisible] = useState(false)
  const [formState, setFormState] = useState({
    username: { value: "", error: "" },
    email: { value: "", error: "" },
    password: { value: "", error: "" },
    confirmPassword: { value: "", error: "" }
  })

  const isValidData = () => {
    const isValidUsername = formState.username.value !== ""
    const isValidEmail = formState.email.value !== "" && formState.email.value.includes("@")
    const isValidPassword = formState.password.value !== ""

    if (!isValidUsername) {
      setFormState({ ...formState, username: { ...formState.username, error: "El nombre de usuario es requerido" } })
    }

    if (!isValidEmail) {
      setFormState({ ...formState, email: { ...formState.email, error: "El correo electronico es requerido" } })
    }

    if (!isValidPassword) {
      setFormState({ ...formState, password: { ...formState.password, error: "La contraseña es requerida" } })
    }

    if (formState.confirmPassword.value !== formState.password.value) {
      setFormState({ ...formState, confirmPassword: { ...formState.confirmPassword, error: "Las contraseñas no coinciden" } })
    }

    return isValidUsername && isValidEmail && isValidPassword && formState.confirmPassword.value === formState.password.value
  }

  async function handleSubmit() {
    if (!isValidData()) return

    const registrarseDTO: RegistrarseDTO = {
      nombreDeUsuario: formState.username.value,
      email: formState.email.value,
      contrasenia: formState.password.value
    }

    await registrarse(registrarseDTO)
      .then(handleErrorClientSide(router))
      .then(() => {
        setUsuario(null) // This is to trigger the useUsuario hook
        toast.success("Registro exitoso")
        router.replace("/")
      })
      .catch((error) => toast.error(error.message))

  }

  return (
    <div className="flex flex-col gap-8 items-center w-full max-w-xl mx-auto">
      <h1 className="text-center font-bold text-2xl">Registrarse</h1>
      <div className="flex flex-col items-center gap-4">
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
          placeholder="Ingrese su correo electronico"
          label="Correo Electronico"
          labelPlacement="outside"
          isClearable
          value={formState.email.value}
          onChange={(e) => setFormState({ ...formState, email: { value: e.target.value, error: "" } })}
          isInvalid={formState.email.error !== ""}
          errorMessage={formState.email.error}
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
        <Input
          placeholder="Confirme su contraseña"
          label="Confirmar Contraseña"
          labelPlacement="outside"
          type='password'
          value={formState.confirmPassword.value}
          onChange={(e) => setFormState({ ...formState, confirmPassword: { value: e.target.value, error: "" } })}
          isInvalid={formState.confirmPassword.error !== ""}
          errorMessage={formState.confirmPassword.error}
        />
        <Button color="primary" fullWidth onClick={handleSubmit}>Registrarse</Button>
      </div>
      <div className="flex flex-col items-center gap-4">
        <span>
          ¿Ya tienes cuenta? <Link href="/login" className="underline">Iniciar Sesion</Link>
        </span>
      </div>
    </div>
  )
}

export default Register
