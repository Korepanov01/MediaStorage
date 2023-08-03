import {AppNavbar} from "./Navbar.js";
import {SearchBar} from "./SearchBar";

const React = require('react');

export function Header() {
    return (
        <>
            <AppNavbar/>
            <SearchBar/>
        </>
    );
}