import React, {useLayoutEffect, useState} from 'react';
import {Button, Form, FormGroup, Modal} from "react-bootstrap";
import {CategorySelector} from "../selectors/categorySelector";
import {getMediaTypeById, getMediaTypes} from "../../apis/mediaTypeAPI";
import {Formik} from "formik";
import {putMedia} from "../../apis/mediaAPI";
import {object, string} from "yup";
import {toast} from "react-toastify";


export function MediaFormPopup({show, setShow, setMedia, media}) {
    const [types, setTypes] = useState([]);

    useLayoutEffect(() => {
        getMediaTypes().then(({data, error}) => {
            if (!error) setTypes(data);
        });
    }, []);

    const handleSubmit = (values) => {
        putMedia(media.id, values).then(({error: putMediaError}) => {
            if (!putMediaError) {
                getMediaTypeById(values.mediaTypeId).then(({error: getMediaTypeError, data: mediaType}) => {
                    if (!putMediaError) {
                        setMedia({...media, mediaType: mediaType, name: values.name, description: values.description});
                        setShow(false);
                        toast.success('Данные изменены');
                    }
                });
            }
        })
    };

    const validationSchema = object({
        name: string().required("Введите имя").max(200, "Название должно быть меньше 200 символов"),
        description: string().max(10000, "Описание должно быть меньше 10 тыс. символов")
    });

    return (
        <Modal show={show} onHide={() => setShow(false)}>
            <Modal.Body>
                <Formik
                    initialValues={{
                        name: media?.name ?? '',
                        description: media?.description ?? '',
                        categoryId: media?.category?.id,
                        mediaTypeId: media?.mediaType?.id
                    }}
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
                                <Form.Label>Описание</Form.Label>
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
                                <Form.Label>Тип</Form.Label>
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
                            <FormGroup className={"d-flex justify-content-end"}>
                                <Button type="submit">Изменить</Button>
                            </FormGroup>
                        </Form>
                    )}
                </Formik>
            </Modal.Body>
        </Modal>
    );
}