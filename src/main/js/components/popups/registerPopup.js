import React from 'react';
import {Button, Form, FormGroup, Modal} from 'react-bootstrap';
import {Formik} from 'formik';
import {object, string, ref} from 'yup';
import {AuthService} from '../../services/authService';
import {toast} from 'react-toastify';

export function RegisterPopup({show, setShow}) {

    function handleRegisterClick(values) {
        AuthService.register(values.name, values.email, values.password)
            .then(({error}) => {
                if (!error) {
                    setShow(false);
                    toast.success('Упешная регистрация');
                }
            });
    }

    const validationSchema = object({
        name: string().required('Введите имя').max(200, 'Имя должно быть не больше 200 символов'),
        email: string().required('Введите почту').email('Некорректный адрес электронной почты').max(500, 'Почта должна быть не больше 500 символов'),
        password: string().required('Введите пароль').min(8, 'Пароль должен иметь больше 8 символов').max(100, 'Пароль не должен быть больше 100 символов'),
        repeatPassword: string().required('Повторите пароль')
            .oneOf([ref('password')], 'Пароли должны совпадать')
    });

    return (
        <Modal show={show} onHide={() => setShow(false)}>
            <Modal.Header>
                <h1 className={'text-center w-100'}>Регистрация</h1>
            </Modal.Header>
            <Modal.Body>
                <Formik
                    initialValues={{ name: '', email: '', password: '', repeatPassword: '' }}
                    onSubmit={handleRegisterClick}
                    validationSchema={validationSchema}
                >
                    {({ handleChange, handleSubmit, values, errors, touched }) => (
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
                                <Form.Label>Повторите пароль</Form.Label>
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
                                <Button type="submit">Зарегистрироваться</Button>
                            </FormGroup>
                        </Form>
                    )}
                </Formik>
            </Modal.Body>
        </Modal>
    );
}
