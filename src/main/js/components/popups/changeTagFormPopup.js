import React from 'react';
import {Button, Form, FormGroup, Modal} from "react-bootstrap";
import {Formik} from "formik";
import {object, string} from "yup"
import {putTag} from "../../apis/tagAPI";
import {toast} from "react-toastify";
import Title from "../decor/title";
import {Text} from "../../text";

export default function ChangeTagFormPopup({show, onChangeShow: handleChangeShow, tag, onSubmit: handleSubmit}) {

    function handlePutLogic(values) {
        putTag(tag.id, {name: values.tagName}).then(result => {
            if (!result.error) {
                toast.success(Text.toastsMessages.successChangeTag(tag.name, values.tagName));
                handleSubmit({...tag, name: values.tagName});
                handleChangeShow(false);
            }
        });
    }

    const validationSchema = object({
        tagName: string().required(Text.validationErrors.nameRequired).max(200, Text.validationErrors.tagNameTooLong)
    });

    return (
        <Modal show={show} onHide={() => handleChangeShow(false)}>
            <Modal.Header>
                <Title>{Text.titles.changeDataMenu}</Title>
            </Modal.Header>
            <Modal.Body>
                <Formik
                    initialValues={{tagName: tag.name}}
                    onSubmit={(values) => handlePutLogic(values)}
                    validationSchema={validationSchema}
                >
                    {({ handleChange, handleSubmit, values, errors, touched }) => (
                        <Form onSubmit={handleSubmit}>
                            <FormGroup>
                                <Form.Label>{Text.formLabels.name}</Form.Label>
                                <Form.Control
                                    type="text"
                                    name="tagName"
                                    onChange={handleChange}
                                    value={values.tagName}
                                    isInvalid={touched.tagName && errors.tagName}
                                />
                                <Form.Control.Feedback type="invalid">{errors.tagName}</Form.Control.Feedback>
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