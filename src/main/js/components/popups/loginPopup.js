import React from 'react';
import {Button, Form, FormGroup, Modal} from "react-bootstrap";
import {Formik} from "formik";
import {object, string} from "yup"
import {useNavigate} from "react-router-dom";
import {AuthService} from "../../services/authService";
import {Text} from "../../text";
import Title from "../decor/title";

export default function LoginPopup({show: show, onChangeShow: handleChangeShow}) {

    const navigate = useNavigate();

    function handleLoginClick(values) {
        AuthService.login(values.email, values.password)
            .then(({error}) => {
                if (!error) {
                    handleChangeShow(false);
                    navigate("/profile");
                }
            });
    }

    const validationSchema = object({
        email: string().required(Text.validationErrors.emailRequired),
        password: string().required(Text.validationErrors.passwordRequired)
    });

    return (
        <Modal show={show} onHide={() => handleChangeShow(false)}>
            <Modal.Header>
                <Title>{Text.titles.login}</Title>
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
                                <Button className="w-100" type="submit">{Text.buttons.login}</Button>
                            </FormGroup>
                        </Form>
                    )}
                </Formik>
            </Modal.Body>
        </Modal>
    );
}