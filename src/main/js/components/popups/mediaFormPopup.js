import React, {useEffect, useLayoutEffect, useState} from 'react';
import {Button, Form, FormGroup, Modal} from "react-bootstrap";
import {CategorySelector} from "../selectors/categorySelector";
import {getMediaTypes} from "../../apis/mediaTypeAPI";

export function MediaFormPopup({show, setShow, setMedia, media}) {
    const [types, setTypes] = useState([]);
    const [postPutMediaRequest, setPostPutMediaRequest] = useState(initialData);

    useEffect(() => {
        setPostPutMediaRequest(initialData);
    }, [initialData]);

    useLayoutEffect(() => {
        getMediaTypes().then(({data, error}) => {
            if (!error) setTypes(data);
        });
    }, []);

    const handleInputChange = (event) => {
        const {name, value} = event.target;
        setPostPutMediaRequest((prevRequest) => ({
            ...prevRequest,
            [name]: value
        }));
    };

    return (
        <Modal show={show} onHide={() => setShow(false)}>
            <Modal.Body>
                <Form>
                    <Form.Group>
                        <Form.Label>Название</Form.Label>
                        <Form.Control
                            type="text"
                            name="name"
                            value={postPutMediaRequest.name}
                            onChange={handleInputChange}
                        />
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Описание</Form.Label>
                        <Form.Control
                            as="textarea"
                            name="description"
                            value={postPutMediaRequest.description || ""}
                            onChange={handleInputChange}
                        />
                    </Form.Group>
                    <FormGroup>
                        <Form.Label>Тип</Form.Label>
                        <Form.Select
                            defaultValue={postPutMediaRequest.mediaTypeId}
                            name="mediaTypeId"
                            onChange={handleInputChange}
                            aria-label="Тип">
                            {types.map((type) => (
                                <option key={type.id} value={type.id}>{type.name}</option>
                            ))};
                        </Form.Select>
                    </FormGroup>
                    <FormGroup>
                        <Form.Label>Категория</Form.Label>
                        <CategorySelector onSelect={(categoryId) => handleInputChange({target: {name: "categoryId", value: categoryId}})}/>
                    </FormGroup>
                </Form>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={() => setShow(false)}>
                    Отмена
                </Button>
                <Button variant="primary" onClick={() => setMedia(postPutMediaRequest)}>
                    Сохранить
                </Button>
            </Modal.Footer>
        </Modal>
    );
}