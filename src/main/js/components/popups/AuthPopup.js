import React, {useState} from 'react';
import {Button, Form, FormGroup, Modal} from "react-bootstrap";

export function AuthPopup({show: show, onChangeShow: handleChangeShow}) {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    function handleLoginClick() {
        handleChangeShow(false);
    }

    return (
        <Modal show={show} onHide={() => handleChangeShow(false)}>
            <Modal.Body>
                <Form>
                    <Form.Text>Войти</Form.Text>
                    <FormGroup>
                        <Form.Label>Почта</Form.Label>
                        <Form.Control
                            type="email"
                            onChange={(e) => setEmail(e.target.value)}
                            value={email}
                        />
                    </FormGroup>
                    <FormGroup>
                        <Form.Label>Пароль</Form.Label>
                        <Form.Control
                            type="password"
                            onChange={(e) => setPassword(e.target.value)}
                            value={password}
                        />
                    </FormGroup>
                </Form>
            </Modal.Body>
            <Modal.Footer>
                <Button onClick={handleLoginClick}>Войти</Button>
                <Button variant="secondary" onClick={() => handleChangeShow(false)}>
                    Отмена
                </Button>
            </Modal.Footer>
        </Modal>
    );
}