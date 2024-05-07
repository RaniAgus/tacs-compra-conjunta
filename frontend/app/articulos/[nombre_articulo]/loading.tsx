import { Spinner } from '@nextui-org/react'

function loading() {
  return (
    <div className='absolute left-0 right-0 top-0 bottom-0 flex justify-center items-center bg-gray-400'>
      <Spinner />
    </div>
  )
}

export default loading