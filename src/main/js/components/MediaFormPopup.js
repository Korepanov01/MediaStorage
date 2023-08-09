import React, {useEffect, useLayoutEffect, useState} from 'react';
import {Button, Form, FormGroup, Modal} from "react-bootstrap";
import {MediaTypeAPI} from "../apis/MediaTypeAPI";
import {CategorySelector} from "./CategorySelector";

export function MediaFormPopup({show: show, onChangeShow: handleChangeShow, onSubmit: handleSubmit, initialData: initialData}) {
    console.log(JSON.stringify(initialData))
    const [types, setTypes] = useState([]);
    const [postPutMediaRequest, setPostPutMediaRequest] = useState(initialData);

    useEffect(() => {
        setPostPutMediaRequest(initialData);
    }, [initialData]);

    useLayoutEffect(() => {
        MediaTypeAPI.get().then(response => {
            setTypes(response.data);
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
        <Modal show={show} onHide={() => handleChangeShow(false)}>
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
                <Button variant="secondary" onClick={() => handleChangeShow(false)}>
                    Отмена
                </Button>
                <Button variant="primary" onClick={() => handleSubmit(postPutMediaRequest)}>
                    Сохранить
                </Button>
            </Modal.Footer>
        </Modal>
    );
}