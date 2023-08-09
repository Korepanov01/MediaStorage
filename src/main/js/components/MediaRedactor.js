import React, {useEffect, useState} from 'react';
import {Button} from "react-bootstrap";
import {PostPutMediaRequestBuilder} from "../models/PostPutMediaRequest";
import {MediaAPI} from "../apis/MediaAPI";
import {FilesFormPopup} from "./popups/FilesFormPopup";
import {MediaFormPopup} from "./popups/MediaFormPopup";

export function MediaRedactor({media: media, mediaFiles: mediaFiles}) {
    const [showMediaForm, setShowMediaForm] = useState(false);
    const [showFilesForm, setShowFilesForm] = useState(false);

    const [putMediaRequest, setPutMediaRequest] = useState(PostPutMediaRequestBuilder.getDefault());

    useEffect(() => {
        setPutMediaRequest(PostPutMediaRequestBuilder.buildByMedia(media));
    }, []);

    const handleMediaFormSubmit = (putRequest) => {
        MediaAPI.put(media.id, putRequest)
            .then(() => {
                setShowMediaForm(false);
            });
    };

    return (
        <>
            <FilesFormPopup show={showFilesForm} onChangeShow={setShowFilesForm} onSubmit={() => {}} mediaFiles={mediaFiles} mediaId={media.id}/>
            <MediaFormPopup show={showMediaForm} onChangeShow={setShowMediaForm} onSubmit={handleMediaFormSubmit} initialData={putMediaRequest}/>
            <Button className={"w-100"} onClick={() => setShowFilesForm(true)}>Файлы</Button>
            <Button className={"w-100"} onClick={() => setShowMediaForm(true)}>Изменить</Button>
        </>
    );
}