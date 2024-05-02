// app/components/ThemeSwitcher.tsx
"use client";

import {useTheme} from "next-themes";
import { useEffect, useState } from "react";

export function ThemeSwitcher() {
  const [mounted, setMounted] = useState(false)
  const { theme, setTheme } = useTheme()

  useEffect(() => {
    setMounted(true)
  }, [])

  if(!mounted) return null

  return (
    <div>
        {theme === 'dark' ? (
            <button onClick={() => setTheme('light')}>L</button>
        ) : (
            <button onClick={() => setTheme('dark')}>D</button>
        )}
    </div>
  )
};