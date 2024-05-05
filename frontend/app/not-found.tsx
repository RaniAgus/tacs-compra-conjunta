import { Button } from '@nextui-org/react'
import Link from 'next/link'

export default function NotFound() {
    return (
        <div className='flex flex-col justify-center items-center gap-4 absolute right-0 left-0 bottom-0 top-0'>
            <h1 className='text-4xl font-bold'>404</h1>
            <h2 className='text-2xl font-bold'>Pagina no encontrada</h2>
            <Button color="primary" as={Link} href='/'>
                Volver al inicio
            </Button>
        </div>
    )
}