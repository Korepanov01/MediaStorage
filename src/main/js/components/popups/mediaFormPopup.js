import React, {useLayoutEffect, useState} from 'react';
import {Button, Form, FormGroup, Modal} from "react-bootstrap";
import {getMediaTypes} from "../../apis/mediaTypeAPI";
import {Formik} from "formik";
import {object, string} from "yup";
import CategorySelector from "../selectors/categorySelector";
export default function MediaFormPopup({show, setShow, onSubmit: handleSubmit, media}) {
    const [types, setTypes] = useState([]);
    const [selectedCategory, setSelectedCategory] = useState(media?.category);

    useLayoutEffect(() => {
        getMediaTypes().then(({data, error}) => {
            if (!error) setTypes(data);
        });
    }, []);

    const validationSchema = object({
        name: string().required(Text.validationErrors.nameRequired).max(200, Text.validationErrors.mediaNameTooLong),
        description: string().max(10000, Text.validationErrors.mediaDescriptionTooLong)
    });

    return (
        <Modal show={show} onHide={() => setShow(false)}>
            {types.length !== 0 &&
                <Modal.Body>
                    <Formik
                        initialValues={{
                            name: media?.name ?? '',
                            description: media?.description ?? '',
                            mediaTypeId: media?.mediaType?.id ?? types[0].id
                        }}
                        onSubmit={(values) => handleSubmit({...values, category: selectedCategory})}
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
                                    <Form.Label>{Text.formLabels.description}</Form.Label>
                                    <Form.Control
                                        as="textarea"
                                        name="description"
                                        onChange={handleChange}
                                        value={values.description}
                                        isInvalid={touched.description && errors.description}
                                    />
                                    <Form.Control.Feedback type="invalid">{errors.description}</Form.Control.Feedback>
                                </FormGroup>
                                <FormGroup>
                                    <Form.Label>{Text.formLabels.type}</Form.Label>
                                    <Form.Select
                                        name="mediaTypeId"
                                        onChange={handleChange}
                                        value={values.mediaTypeId}
                                        defaultValue={values.mediaTypeId}>
                                        {types.map((type) => (
                                            <option key={type.id} value={type.id}>{type.name}</option>
                                        ))};
                                    </Form.Select>
                                </FormGroup>
                                <FormGroup>
                                    <Form.Label>{Text.formLabels.category}</Form.Label>
                                    <CategorySelector selectedCategory={selectedCategory} setSelectedCategory={setSelectedCategory}/>
                                </FormGroup>
                                <FormGroup className={"d-flex justify-content-end"}>
                                    <Button type="submit">{Text.buttons.submit}</Button>
                                </FormGroup>
                            </Form>
                        )}
                    </Formik>
                </Modal.Body>
            }
        </Modal>
    );
}