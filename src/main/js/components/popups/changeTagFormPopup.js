import React from 'react';
import {Button, Form, FormGroup, Modal} from "react-bootstrap";
import {Formik} from "formik";
import {object, string} from "yup"
import {putTag} from "../../apis/TagAPI";
import {toastErrors} from "../../services/toastService";
import {TagBuilder} from "../../models/Tag";
import {toast} from "react-toastify";

ChangeTagFormPopup.defaultProps = {
    tag: {name: "", id: 0}
};

export function ChangeTagFormPopup({show: show, onChangeShow: handleChangeShow, tag: tag, onSubmit: handleSubmit}) {

    function handlePutLogic(values) {
        putTag(tag.id, {name: values.tagName}).then(result => {
            if (result.error)
                toastErrors(result.error.messages)
            else {
                toast.success(`Изменен тег "${tag.name}" -> "${values.tagName}"`)
                handleSubmit({...tag, name: values.tagName})
            }
        });
    }

    const validationSchema = object({
        tagName: string().required("Имя не может быть пустым").max(200, "Название не может быть больше 200 символов!")
    });

    return (
        <Modal show={show} onHide={() => handleChangeShow(false)}>
            <Modal.Header>
                <h1 className={"text-center w-100"}>Изменить</h1>
            </Modal.Header>
            <Modal.Body>
                <Formik
                    initialValues={{ tagName: tag.name }}
                    onSubmit={(values) => handlePutLogic(values)}
                    validationSchema={validationSchema}
                >
                    {({ handleChange, handleSubmit, values, errors, touched }) => (
                        <Form onSubmit={handleSubmit}>
                            <FormGroup>
                                <Form.Label>Название</Form.Label>
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
                                <Button type="submit">Измменить</Button>
                            </FormGroup>
                        </Form>
                    )}
                </Formik>
            </Modal.Body>
        </Modal>
    );
}