import {Link} from "react-router-dom";

const React = require('react');

import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';

const PROFILE_TAB_NAME = "Личный кабинет";
const SEARCH_TAB_NAME = "Поиск";

export function AppNavbar() {
    return (
        <Navbar expand="lg">
            <Container fluid>
                <Navbar.Brand href="/">MediaStorage</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                        <Nav.Link as={Link} to="search">{SEARCH_TAB_NAME}</Nav.Link>
                        <Nav.Link as={Link} to="profile">{PROFILE_TAB_NAME}</Nav.Link>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}