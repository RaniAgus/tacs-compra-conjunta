import Navbar from "@/components/Navbar";
import { UsuarioDTO } from "@/model/UsuarioDTO";
import { NextUIProvider } from "@nextui-org/react";
import { Provider, atom } from "jotai";
import { ThemeProvider as NextThemesProvider } from "next-themes";
import { Inter } from "next/font/google";
import { Toaster } from "react-hot-toast";
import "./globals.css";

const inter = Inter({ subsets: ["latin"] });

export const user = atom<UsuarioDTO | null | undefined>(null)

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