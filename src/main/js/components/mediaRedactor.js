import React, {useEffect, useState} from 'react';
import {Accordion, Button, ListGroup} from "react-bootstrap";
import {FilesFormPopup} from "./popups/filesFormPopup";
import {MediaFormPopup} from "./popups/mediaFormPopup";
import {TagsFormPopup} from "./popups/tagsFormPopup";
import {useNavigate} from "react-router-dom";
import {deleteMedia} from "../apis/mediaAPI";
import {toast} from "react-toastify";

export function MediaRedactor({media, setMedia}) {
    const [showMediaForm, setShowMediaForm] = useState(false);
    const [showFilesForm, setShowFilesForm] = useState(false);
    const [showTagsForm, setShowTagsForm] = useState(false);

    const navigate = useNavigate();

    const handleDeleteButtonClick = () => {
        deleteMedia(media.id)
            .then(({error}) => {
                if(!error) {
                    navigate("/");
                    toast.success(`Медиа "${media.name}" удалено`)
                }
            });
    };

    return (
        <>
            <MediaFormPopup show={showMediaForm} setShow={setShowMediaForm} media={media} setMedia={setMedia}/>
            <FilesFormPopup show={showFilesForm} setShow={setShowFilesForm} setMedia={setMedia} media={media}/>
            <TagsFormPopup show={showTagsForm} setShow={setShowTagsForm} setMedia={setMedia} media={media}/>
            <Accordion>
                <Accordion.Item eventKey={"1"}>
                    <Accordion.Header>Изменить</Accordion.Header>
                    <Accordion.Body>
                        <ListGroup>
                            <ListGroup.Item>
                                <Button className={"w-100"} onClick={() => setShowMediaForm(true)}>Основное</Button>
                            </ListGroup.Item>
                            <ListGroup.Item>
                                <Button className={"w-100"} onClick={() => setShowFilesForm(true)}>Файлы</Button>
                            </ListGroup.Item>
                            <ListGroup.Item>
                                <Button className={"w-100"} onClick={() => setShowTagsForm(true)}>Теги</Button>
                            </ListGroup.Item>
                            <ListGroup.Item>
                                <Button variant={"danger"} className={"w-100"} onClick={handleDeleteButtonClick}>Удалить</Button>
                            </ListGroup.Item>
                        </ListGroup>
                    </Accordion.Body>
                </Accordion.Item>
            </Accordion>
        </>
    );
}