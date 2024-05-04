import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";
import { NextUIProvider } from "@nextui-org/react";
import { ThemeProvider as NextThemesProvider } from "next-themes";
import Navbar from "@/components/Navbar";
import { Toaster } from "react-hot-toast";
import { Provider, atom } from "jotai";
import { UsuarioDTO } from "@/model/UsuarioDTO";

const inter = Inter({ subsets: ["latin"] });

export const user = atom<UsuarioDTO | null>(null)

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="es" className='dark'>
      <body className={inter.className}>
        <Providers>
          <Toaster position="top-center" />
          <Navbar />

          <main className="container mx-auto max-w-7xl pt-8 flex-grow">
            {children}
          </main>
        </Providers>
      </body>
    </html>
  );
}

function Providers({ children }: Readonly<{ children: React.ReactNode }>) {
  return (
    <Provider>
      <NextUIProvider>
        <NextThemesProvider attribute="class" defaultTheme="dark">
          {children}
        </NextThemesProvider>
      </NextUIProvider>
    </Provider>
  )
}