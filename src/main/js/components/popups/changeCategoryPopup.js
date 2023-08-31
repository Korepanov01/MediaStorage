import React from 'react';
import {Button, Form, FormGroup, Modal} from "react-bootstrap";
import {Formik} from "formik";
import {object, string} from "yup"
import {Text} from "../../text";

export default function ChangeCategoryPopup({show, setShow: setShow, onSubmit: handleSubmit, selectedCategory}) {

    const validationSchema = object({
        name: string().required(Text.validationErrors.nameRequired).max(200, Text.validationErrors.categoryNameTooLong)
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
                                <Form.Label>{Text.formLabels.name}</Form.Label>
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
                                <Button type="submit">{Text.buttons.submit}</Button>
                            </FormGroup>
                        </Form>
                    )}
                </Formik>
            </Modal.Body>
        </Modal>
    );
}