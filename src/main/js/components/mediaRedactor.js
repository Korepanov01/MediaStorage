import React, {useEffect, useState} from 'react';
import {Accordion, Button, ListGroup} from "react-bootstrap";
import {FilesFormPopup} from "./popups/filesFormPopup";
import {MediaFormPopup} from "./popups/mediaFormPopup";
import {TagsFormPopup} from "./popups/tagsFormPopup";
import {useNavigate} from "react-router-dom";
import {deleteMedia} from "../apis/mediaAPI";

export function MediaRedactor({media: media, mediaFiles: mediaFiles, tags: tags, onTagsChange: handleTagsChange}) {
    const [showMediaForm, setShowMediaForm] = useState(false);
    const [showFilesForm, setShowFilesForm] = useState(false);
    const [showTagsForm, setShowTagsForm] = useState(false);

    const [putMediaRequest, setPutMediaRequest] = useState({
        categoryId: 0,
        name: "string",
        description: "string",
        mediaTypeId: 0
    });

    const navigate = useNavigate();

    useEffect(() => {
        setPutMediaRequest(media);
    }, []);

    const handleMediaFormSubmit = (putRequest) => {
        MediaAPI.put(media.id, putRequest)
            .then(() => {
                setShowMediaForm(false);
            });
    };

    const handleDeleteButtonClick = () => {
        deleteMedia(media.id)
            .then(({data, error}) => {
                if(!error) navigate("/");
            });
    };

    return (
        <>
            <MediaFormPopup show={showMediaForm} onChangeShow={setShowMediaForm}
                            onSubmit={handleMediaFormSubmit} initialData={putMediaRequest}/>
            <FilesFormPopup show={showFilesForm} onChangeShow={setShowFilesForm} onSubmit={() => {
            }} mediaFiles={mediaFiles} mediaId={media.id}/>
            <TagsFormPopup show={showTagsForm} onChangeShow={setShowTagsForm}
                           onTagsChange={handleTagsChange} tags={tags} mediaId={media.id}/>
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