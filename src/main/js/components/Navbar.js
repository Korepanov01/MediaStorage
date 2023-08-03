const React = require('react');

import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';

const VIDEO_TAB_NAME = "Видео";
const IMAGE_TAB_NAME = "Изображения";
const MUSIC_TAB_NAME = "Музыка";

export function AppNavbar() {
    return (
        <Navbar expand="lg" className="bg-body-tertiary">
            <Navbar.Brand href="/">MediaStorage</Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav">
                <Nav className="me-auto">
                    <Nav.Link href="/video">{VIDEO_TAB_NAME}</Nav.Link>
                    <Nav.Link href="/image">{IMAGE_TAB_NAME}</Nav.Link>
                    <Nav.Link href="/music">{MUSIC_TAB_NAME}</Nav.Link>
                </Nav>
            </Navbar.Collapse>
        </Navbar>
    );
}