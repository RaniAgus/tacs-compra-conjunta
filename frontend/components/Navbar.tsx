"use client"
import React from "react";
import { Navbar as Nav, NavbarBrand, NavbarMenuToggle, NavbarMenuItem, NavbarMenu, NavbarContent, NavbarItem, Link, Button, Dropdown, DropdownTrigger, Avatar, DropdownMenu, DropdownItem, Input, Kbd } from "@nextui-org/react";
import Session from "./Session";
import { ThemeSwitcher } from "./ThemeSwitcher";

export default function Navbar() {
    const [isMenuOpen, setIsMenuOpen] = React.useState(false);

    const menuItems = [
        "Profile",
    ];

    const searchInput = (
        <Input
            aria-label="Search"
            classNames={{
                inputWrapper: "bg-default-100",
                input: "text-sm",
            }}
            endContent={<Kbd className="hidden lg:inline-block" keys={["command"]}>K</Kbd>}
            labelPlacement="outside"
            placeholder="Search..."
            type="search"
        />
    );

    return (
        <Nav
            isBordered
            isMenuOpen={isMenuOpen}
            onMenuOpenChange={setIsMenuOpen}
        >
            <NavbarContent className="sm:hidden" justify="start">
                <NavbarBrand>
                    <p className="font-bold text-inherit">ACME</p>
                </NavbarBrand>
            </NavbarContent>

            <NavbarContent className="sm:hidden" justify="end">
                <NavbarMenuToggle aria-label={isMenuOpen ? "Close menu" : "Open menu"} />
            </NavbarContent>

            <NavbarContent className="hidden sm:flex gap-4" justify="center">
                <NavbarBrand>
                    <p className="font-bold text-inherit">ACME</p>
                </NavbarBrand>
                <NavbarItem>
                    <Link color="foreground" href="#">
                        Features
                    </Link>
                </NavbarItem>
                <NavbarItem isActive>
                    <Link href="#" aria-current="page">
                        Customers
                    </Link>
                </NavbarItem>
                <NavbarItem>
                    <Link color="foreground" href="#">
                        Integrations
                    </Link>
                </NavbarItem>
            </NavbarContent>

            <NavbarContent className="hidden sm:flex" justify="end">
                <ThemeSwitcher />
                <NavbarItem className="hidden lg:flex">{searchInput}</NavbarItem>
                <Session />
            </NavbarContent>

            <NavbarMenu>
                {searchInput}
                {menuItems.map((item, index) => (
                    <NavbarMenuItem key={`${item}-${index}`}>
                        <Link
                            className="w-full"
                            color="foreground"
                            href="#"
                            size="lg"
                        >
                            {item}
                        </Link>
                    </NavbarMenuItem>
                ))}
                <NavbarMenuItem key={"logout-2"}>
                    <Link
                        className="w-full"
                        color="danger"
                        href="#"
                        size="lg"
                    >
                        Logout
                    </Link>
                </NavbarMenuItem>
            </NavbarMenu>
        </Nav>
    );
}
