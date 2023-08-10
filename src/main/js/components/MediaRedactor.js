import React, {useEffect, useState} from 'react';
import {Button} from "react-bootstrap";
import {PostPutMediaRequestBuilder} from "../models/PostPutMediaRequest";
import {MediaAPI} from "../apis/MediaAPI";
import {FilesFormPopup} from "./popups/FilesFormPopup";
import {MediaFormPopup} from "./popups/MediaFormPopup";
import {TagsFormPopup} from "./popups/TagsFormPopup";

export function MediaRedactor({media: media, mediaFiles: mediaFiles, tags: tags, onTagsChange: handleTagsChange}) {
    const [showMediaForm, setShowMediaForm] = useState(false);
    const [showFilesForm, setShowFilesForm] = useState(false);
    const [showTagsForm, setShowTagsForm] = useState(false);

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
            <Button className={"w-100"} onClick={() => setShowFilesForm(true)}>Файлы</Button>

            <MediaFormPopup show={showMediaForm} onChangeShow={setShowMediaForm} onSubmit={handleMediaFormSubmit} initialData={putMediaRequest}/>
            <Button className={"w-100"} onClick={() => setShowMediaForm(true)}>Изменить</Button>

            <TagsFormPopup show={showTagsForm} onChangeShow={setShowTagsForm} onTagsChange={handleTagsChange} tags={tags} mediaId={media.id} />
            <Button className={"w-100"} onClick={() => setShowTagsForm(true)}>Теги</Button>
        </>
    );
}