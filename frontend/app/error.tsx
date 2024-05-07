'use client' // Error components must be Client Components

import { Button } from '@nextui-org/react'
import { useEffect } from 'react'
import toast from 'react-hot-toast'

export default function Error({
    error,
    reset,
}: {
    error: Error & { digest?: string }
    reset: () => void
}) {
    useEffect(() => {
        toast.error(error.message)
    }, [error])

    return (
        <div className='flex flex-col justify-center items-center'>
            <h2>Ha ocurrido un error</h2>
            <Button onClick={() => reset()} color='primary'>
                Reintentar
            </Button>
        </div>
    )
}