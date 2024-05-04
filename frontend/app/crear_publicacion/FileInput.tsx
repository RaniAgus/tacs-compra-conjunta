import React from "react"

type FileInputProps = {
  isRequired?: boolean
  id: string
  name: string
  label?: string
  accept?: string
  handleFileChange: (e: React.ChangeEvent<HTMLInputElement>) => void
}

function FileInput({
  isRequired,
  id,
  name,
  label,
  accept,
  handleFileChange,
}: FileInputProps) {
  return (
    <>
      <label
        data-slot="label"
        htmlFor={id}
        className={isRequired ? "after\:content-\['\*'\] after\:text-danger after\:ml-0\.5" : undefined}
      >
        {label}
      </label>
      <div className="flex w-full items-center justify-between space-x-4 p-2 border-2  hover:border-gray-400 rounded-xl transition-colors duration-300">
        <input
          required={isRequired}
          id={id}
          type="file"
          name={name}
          accept={accept}
          onChange={handleFileChange}
          className="block w-full text-sm text-slate-500 file:mr-4 file:py-2 file:px-4 file:rounded-md file:border-0 file:text-sm file:font-semibold file:bg-pink-50 file:text-pink-700 hover:file:bg-pink-100"
        />
      </div>
    </>
  )
}

export default FileInput
