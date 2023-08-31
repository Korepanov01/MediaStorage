import React from 'react';
import {Button, Form, FormGroup} from "react-bootstrap";
import {Formik} from "formik";
import {object, string} from "yup"
import {toast} from "react-toastify";
import {postTag} from "../../apis/tagAPI";

export default function AddTagForm({onSubmit: handleSubmit}) {
    const initialValues={tagName: ""};

    function handlePostLogic(values, resetForm) {
        postTag({name: values.tagName}).then(result => {
            if (!result.error) {
                toast.success(`Добавлен тег "${values.tagName}"`);
                handleSubmit({id: result.data.id, name: values.tagName});
                resetForm(initialValues);
            }
        });
    }

    const validationSchema = object({
        tagName: string().required("Имя не может быть пустым").max(200, "Название не может быть больше 200 символов!")
    });

    return (
        <Formik
            initialValues={initialValues}
            onSubmit={(values, {resetForm}) => handlePostLogic(values, resetForm)}
            validationSchema={validationSchema}
        >
            {({ handleChange, handleSubmit, values, errors, touched }) => (
                <Form onSubmit={handleSubmit} className={'row'}>
                    <FormGroup className={'col'}>
                        <Form.Control
                            placeholder={'Название'}
                            type="text"
                            name="tagName"
                            onChange={handleChange}
                            value={values.tagName}
                            isInvalid={touched.tagName && errors.tagName}
                        />
                        <Form.Control.Feedback type="invalid">{errors.tagName}</Form.Control.Feedback>
                    </FormGroup>
                    <FormGroup className={'col d-flex justify-content-end align-content-center'}>
                        <Button type="submit">Добавить</Button>
                    </FormGroup>
                </Form>
            )}
        </Formik>
    );
}