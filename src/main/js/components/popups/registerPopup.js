import React from 'react';
import {Button, Form, FormGroup, Modal} from 'react-bootstrap';
import {Formik} from 'formik';
import {object, string, ref} from 'yup';
import {AuthService} from '../../services/authService';
import {toast} from 'react-toastify';
import {Text} from "../../text";
import Title from "../decor/title";

export default function RegisterPopup({show, setShow}) {

    function handleRegisterClick(values) {
        AuthService.register(values.name, values.email, values.password)
            .then(({error}) => {
                if (!error) {
                    setShow(false);
                    toast.success(Text.toastsMessages.successRegister);
                }
            });
    }

    const validationSchema = object({
        name: string().required(Text.validationErrors.nameRequired).max(200, Text.validationErrors.userNameTooLong),
        email: string().required(Text.validationErrors.emailRequired).email(Text.validationErrors.invalidEmail).max(500, Text.validationErrors.emailTooLong),
        password: string().required(Text.validationErrors.passwordRequired).min(8, Text.validationErrors.passwordTooShort).max(100, Text.validationErrors.passwordTooLong),
        repeatPassword: string().required(Text.validationErrors.repeatPasswordRequired)
            .oneOf([ref('password')], Text.validationErrors.passwordsAreNotTheSame)
    });

    return (
        <Modal show={show} onHide={() => setShow(false)}>
            <Modal.Header>
                <Title>{Text.titles.register}</Title>
            </Modal.Header>
            <Modal.Body>
                <Formik
                    initialValues={{name: '', email: '', password: '', repeatPassword: ''}}
                    onSubmit={handleRegisterClick}
                    validationSchema={validationSchema}
                >
                    {({handleChange, handleSubmit, values, errors, touched}) => (
                        <Form onSubmit={handleSubmit}>
                            <FormGroup>
                                <Form.Label>Имя</Form.Label>
                                <Form.Control
                                    type="text"
                                    name="name"
                                    onChange={handleChange}
                                    value={values.name}
                                    isInvalid={touched.name && errors.name}
                                />
                                <Form.Control.Feedback type="invalid">{errors.name}</Form.Control.Feedback>
                            </FormGroup>
                            <FormGroup>
                                <Form.Label>{Text.formLabels.email}</Form.Label>
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
                                <Form.Label>{Text.formLabels.password}</Form.Label>
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
                                <Form.Label>{Text.formLabels.repeatPassword}</Form.Label>
                                <Form.Control
                                    type="password"
                                    name="repeatPassword"
                                    onChange={handleChange}
                                    value={values.repeatPassword}
                                    isInvalid={touched.repeatPassword && errors.repeatPassword}
                                />
                                <Form.Control.Feedback type="invalid">{errors.repeatPassword}</Form.Control.Feedback>
                            </FormGroup>
                            <FormGroup>
                                <Button className="w-100" type="submit">{Text.buttons.register}</Button>
                            </FormGroup>
                        </Form>
                    )}
                </Formik>
            </Modal.Body>
        </Modal>
    );
}
