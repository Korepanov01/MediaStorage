import React from 'react';
import {Button, Form, FormGroup, Modal} from "react-bootstrap";
import {Formik} from "formik";
import {object, string} from "yup"
import {useNavigate} from "react-router-dom";
import {useDispatch} from "react-redux";
import {login} from "../../redux/authSlice";
import {AuthService} from "../../services/authService";

export function LoginPopup({show: show, onChangeShow: handleChangeShow}) {

    const navigate = useNavigate();
    const dispatch = useDispatch();

    function handleLoginClick(values) {
        AuthService.login(values.email, values.password)
            .then(({error, user}) => {
                if (!error) {
                    dispatch(login(user));
                    handleChangeShow(false);
                    navigate("/profile");
                }
            });
    }

    const validationSchema = object({
        email: string().required("Введите почту"),
        password: string().required("Введите пароль")
    });

    return (
        <Modal show={show} onHide={() => handleChangeShow(false)}>
            <Modal.Header>
                <h1 className={"text-center w-100"}>Вход</h1>
            </Modal.Header>
            <Modal.Body>
                <Formik
                    initialValues={{ email: '', password: '' }}
                    onSubmit={(values) => handleLoginClick(values)}
                    validationSchema={validationSchema}
                >
                    {({ handleChange, handleSubmit, values, errors, touched }) => (
                        <Form onSubmit={handleSubmit}>
                            <FormGroup>
                                <Form.Label>Почта</Form.Label>
                                <Form.Control
                                    type="text"
                                    name="email"
                                    onChange={handleChange}
                                    value={values.email}
                                    isInvalid={touched.email && errors.email}
                                />
                                <Form.Control.Feedback type="invalid">{errors.email}</Form.Control.Feedback>
                            </FormGroup>
                            <FormGroup>
                                <Form.Label>Пароль</Form.Label>
                                <Form.Control
                                    type="password"
                                    name="password"
                                    onChange={handleChange}
                                    value={values.password}
                                    isInvalid={touched.password && errors.password}
                                />
                                <Form.Control.Feedback type="invalid">{errors.password}</Form.Control.Feedback>
                            </FormGroup>
                            <FormGroup>
                                <Button type="submit">Войти</Button>
                            </FormGroup>
                        </Form>
                    )}
                </Formik>
            </Modal.Body>
        </Modal>
    );
}