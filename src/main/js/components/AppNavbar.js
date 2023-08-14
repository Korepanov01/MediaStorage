import {Link} from "react-router-dom";

const React = require('react');

import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import {Button, Col, Row} from "react-bootstrap";
import {useState} from "react";
import {LoginPopup} from "./popups/LoginPopup";
import {useDispatch, useSelector} from "react-redux";
import {logout} from "../redux/authSlice";
import {Roles} from "../enums/roles";

const PROFILE_TAB_NAME = "Личный кабинет";
const SEARCH_TAB_NAME = "Поиск";
const ADMIN_TAB_NAME = "Админ-панель";

export function AppNavbar() {
    const [showLoginForm, setShowLoginForm] = useState(false);
    const isLoggedIn = useSelector(state => state.auth.isLoggedIn);
    const roles = useSelector(state => state.auth.user?.roles);
    const dispatch = useDispatch();

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
                    <Button className={"w-100"} onClick={() => dispatch(logout())}>Выйти</Button>
                ) : (
                    <>
                        <LoginPopup onChangeShow={setShowLoginForm} show={showLoginForm}/>
                        <Button className={"w-100"} onClick={() => setShowLoginForm(true)}>Войти</Button>
                    </>
                )}
            </Col>
        </Row>
    );
}