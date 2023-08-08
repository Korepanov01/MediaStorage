import React, {useEffect, useState} from 'react';
import {Button, Form, FormGroup, Modal} from "react-bootstrap";
import {MediaAPI} from "../apis/MediaAPI";
import {MediaTypeSelector} from "./MediaTypeSelector";
import {MediaTypeAPI} from "../apis/MediaTypeAPI";
import {CategorySelector} from "./CategorySelector";

export function AddMedia({onPost: handlePost, userId: userId, show: show, onChangeShow: handleChangeShow}) {
    const [types, setTypes] = useState([]);
    const [newMedia, setNewMedia] = useState({
        userId: 1,
        categoryId: null,
        name: "",
        description: "",
        mediaTypeId: null
    });

    useEffect(() => {
        MediaTypeAPI.get().then(response => {
            setTypes(response.data);
        });
    }, []);

    const handleInputChange = (event) => {
        const {name, value} = event.target;
        setNewMedia((prevMedia) => ({
            ...prevMedia,
            [name]: value
        }));
    };

    const handleFormSubmit = () => {
        console.log(JSON.stringify(newMedia))
        MediaAPI.post(newMedia)
            .then(() => {
                handleChangeShow(false);
                handlePost(true);
            });
    };

    return (
        <Modal show={show} onHide={() => handleChangeShow(false)}>
            <Modal.Header closeButton>
                <Modal.Title>Новое медиа</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form>
                    <Form.Group>
                        <Form.Label>Название</Form.Label>
                        <Form.Control
                            type="text"
                            name="name"
                            value={newMedia.name}
                            onChange={handleInputChange}
                        />
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Описание</Form.Label>
                        <Form.Control
                            as="textarea"
                            name="description"
                            value={newMedia.description}
                            onChange={handleInputChange}
                        />
                    </Form.Group>
                    <FormGroup>
                        <Form.Label>Тип</Form.Label>
                        <Form.Select
                            name="mediaTypeId"
                            onChange={handleInputChange}
                            aria-label="Тип">
                            {types.map((type) => (
                                <option key={type.id} value={type.id}>{type.name}</option>
                            ))};
                        </Form.Select>
                    </FormGroup>
                    <CategorySelector onSelect={(categoryId) => handleInputChange({target: {name: "categoryId", value: categoryId}})}/>
                </Form>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={() => handleChangeShow(false)}>
                    Отмена
                </Button>
                <Button variant="primary" onClick={handleFormSubmit}>
                    Сохранить
                </Button>
            </Modal.Footer>
        </Modal>
    );
}