import React, {useLayoutEffect, useState} from 'react';
import {Button, Form, FormGroup, Modal} from "react-bootstrap";
import {getMediaTypes} from "../../apis/mediaTypeAPI";
import {Formik} from "formik";
import {object, string} from "yup";
import {CategorySelector} from "../selectors/categorySelector";


export function MediaFormPopup({show, setShow, onSubmit: handleSubmit, media}) {
    const [types, setTypes] = useState([]);
    const [selectedCategory, setSelectedCategory] = useState(media?.category);

    useLayoutEffect(() => {
        getMediaTypes().then(({data, error}) => {
            if (!error) setTypes(data);
        });
    }, []);

    const validationSchema = object({
        name: string().required("Введите имя").max(200, "Название должно быть меньше 200 символов"),
        description: string().max(10000, "Описание должно быть меньше 10 тыс. символов")
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
                                <FormGroup>
                                    <Form.Label>Категория</Form.Label>
                                    <CategorySelector selectedCategory={selectedCategory} setSelectedCategory={setSelectedCategory}/>
                                </FormGroup>
                                <FormGroup className={"d-flex justify-content-end"}>
                                    <Button type="submit">Готово</Button>
                                </FormGroup>
                            </Form>
                        )}
                    </Formik>
                </Modal.Body>
            }
        </Modal>
    );
}