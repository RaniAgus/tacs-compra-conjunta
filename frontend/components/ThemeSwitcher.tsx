// app/components/ThemeSwitcher.tsx
"use client"

import {useTheme} from "next-themes";
import { useEffect, useState } from "react";
import { IoMoon, IoSunny } from "react-icons/io5";

export function ThemeSwitcher() {
  const [mounted, setMounted] = useState(false)
  const { theme, setTheme } = useTheme()

  useEffect(() => {
    setMounted(true)
  }, [])

  if (!mounted) return null

  return (
    <>
        {theme === 'dark' ? (
            <button onClick={() => setTheme('light')}><IoMoon /></button>
        ) : (
            <button onClick={() => setTheme('dark')}><IoSunny /></button>
        )}
    </>
  )
}
