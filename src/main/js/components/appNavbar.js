import {Link} from "react-router-dom";

const React = require('react');

import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import {Button, ButtonGroup, Col, Row} from "react-bootstrap";
import {useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import {Roles} from "../enums/roles";
import {LoginPopup} from "./popups/loginPopup";
import {AuthService} from "../services/authService";
import {RegisterPopup} from "./popups/registerPopup";

const PROFILE_TAB_NAME = "Личный кабинет";
const SEARCH_TAB_NAME = "Поиск";
const ADMIN_TAB_NAME = "Админ-панель";

export function AppNavbar() {
    const [showLoginForm, setShowLoginForm] = useState(false);
    const [showRegisterForm, setShowRegisterForm] = useState(false);

    const isLoggedIn = useSelector(state => state.auth.isLoggedIn);
    const roles = useSelector(state => state.auth.user?.roles);

    return (
        <Row className={"w-100"}>
            <Col lg={"10"}>
                <Navbar expand="lg" className={"w-100"}>
                    <Navbar.Brand as={Link} to="/">MediaStorage</Navbar.Brand>
                    <Navbar.Toggle aria-controls="basic-navbar-nav"/>
                    <Navbar.Collapse id="basic-navbar-nav">
                        <Nav className="me-auto">
                            <Nav.Link as={Link} to="search">{SEARCH_TAB_NAME}</Nav.Link>
                            {isLoggedIn &&
                                <>
                                    <Nav.Link as={Link} to="profile">{PROFILE_TAB_NAME}</Nav.Link>
                                    {roles.includes(Roles.ADMIN) &&
                                        <Nav.Link as={Link} to="admin">{ADMIN_TAB_NAME}</Nav.Link>
                                    }
                                </>
                            }
                        </Nav>
                    </Navbar.Collapse>
                </Navbar>
            </Col>
            <Col lg={"2"} className={"d-flex align-items-center"}>
                {isLoggedIn ? (
                    <Button className={"w-100"} onClick={() => AuthService.logout()}>Выйти</Button>
                ) : (
                    <>
                        <RegisterPopup setShow={setShowRegisterForm} show={showRegisterForm}/>
                        <LoginPopup onChangeShow={setShowLoginForm} show={showLoginForm}/>

                        <ButtonGroup>
                            <Button className={"w-100"} onClick={() => setShowRegisterForm(true)}>Регистрация</Button>
                            <Button className={"w-100"} onClick={() => setShowLoginForm(true)}>Вход</Button>
                        </ButtonGroup>
                    </>
                )}
            </Col>
        </Row>
    );
}