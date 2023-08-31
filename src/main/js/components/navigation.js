import React, {useState} from "react"
import {Link} from "react-router-dom";
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import {Button, ButtonGroup, Col, Row} from "react-bootstrap";
import {useSelector} from "react-redux";
import {Roles} from "../enums/roles";
import LoginPopup from "./popups/loginPopup";
import {AuthService} from "../services/authService";
import RegisterPopup from "./popups/registerPopup";

export default function Navigation() {
    const [showLoginForm, setShowLoginForm] = useState(false);
    const [showRegisterForm, setShowRegisterForm] = useState(false);

    const user = useSelector(state => state.auth.user);

    return (
        <Row>
            <Col sm={"10"}>
                <Navbar expand="lg" className={"w-100"}>
                    <Navbar.Brand as={Link} to="/">MediaStorage</Navbar.Brand>
                    <Navbar.Toggle aria-controls="basic-navbar-nav"/>
                    <Navbar.Collapse id="basic-navbar-nav">
                        <Nav className="me-auto">
                            <Nav.Link as={Link} to="search">{Text.navbar.searchTab}</Nav.Link>
                            {user &&
                                <>
                                    <Nav.Link as={Link} to="profile">{Text.navbar.profileTab}</Nav.Link>
                                    {user.roles.includes(Roles.ADMIN) &&
                                        <Nav.Link as={Link} to="admin">{Text.navbar.adminTab}</Nav.Link>
                                    }
                                </>
                            }
                        </Nav>
                    </Navbar.Collapse>
                </Navbar>
            </Col>
            <Col sm={"2"} className={"d-flex align-items-center"}>
                {user ? (
                    <Button className={"w-100"} onClick={() => AuthService.logout()}>{Text.buttons.logout}</Button>
                ) : (
                    <>
                        <RegisterPopup setShow={setShowRegisterForm} show={showRegisterForm}/>
                        <LoginPopup onChangeShow={setShowLoginForm} show={showLoginForm}/>

                        <ButtonGroup>
                            <Button className={"w-100"} onClick={() => setShowRegisterForm(true)}>{Text.buttons.register}</Button>
                            <Button className={"w-100"} onClick={() => setShowLoginForm(true)}>{Text.buttons.login}</Button>
                        </ButtonGroup>
                    </>
                )}
            </Col>
        </Row>
    );
}