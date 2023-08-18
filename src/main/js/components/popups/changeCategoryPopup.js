import React from 'react';
import {Button, Form, FormGroup, Modal} from "react-bootstrap";
import {Formik} from "formik";
import {object, string} from "yup"

export function ChangeCategoryPopup({show, setShow: setShow, onSubmit: handleSubmit, selectedCategory}) {

    const validationSchema = object({
        name: string().required("Имя не может быть пустым").max(200, "Имя категории не может быть длиннее 200 символов!")
    });

    return (
        <Modal show={show} onHide={() => setShow(false)}>
            <Modal.Body>
                <Formik
                    initialValues={{name: selectedCategory?.name ?? ''}}
                    onSubmit={handleSubmit}
                    validationSchema={validationSchema}
                >
                    {({handleChange, handleSubmit, values, errors, touched}) => (
                        <Form onSubmit={handleSubmit}>
                            <FormGroup>
                                <Form.Label>Название</Form.Label>
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
                                <Button type="submit">Готово</Button>
                            </FormGroup>
                        </Form>
                    )}
                </Formik>
            </Modal.Body>
        </Modal>
    );
}