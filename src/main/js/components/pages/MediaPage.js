import React, {useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import {Button, Col, Row} from "react-bootstrap";
import {MediaAPI} from "../../apis/MediaAPI";
import {FilesCarousel} from "../FileCarousel";
import {MediaFormPopup} from "../popups/MediaFormPopup";
import {MediaInfo} from "../MediaInfo";
import {MediaBuilder} from "../../models/Media";
import {PostPutMediaRequestBuilder} from "../../models/PostPutMediaRequest";
import {FileTypes} from "../../enums/FileTypes";
import {FilesFormPopup} from "../popups/FilesFormPopup";
import {MediaFileAPI} from "../../apis/MediaFileAPI";

export function MediaPage() {
    const {id} = useParams();

    const [media, setMedia] = useState(MediaBuilder.getDefault());
    const [mediaFiles, setMediaFiles] = useState([]);
    const [showMediaForm, setShowMediaForm] = useState(false);
    const [showFilesForm, setShowFilesForm] = useState(false);

    const [putMediaRequest, setPutMediaRequest] = useState(PostPutMediaRequestBuilder.getDefault());
    const [updated, setUpdated] = useState(false);
    const [filesUpdated, setFilesUpdated] = useState(false);

    useEffect(() => {
        MediaAPI.getById(id).then(media => {
            setMedia(media);
            setPutMediaRequest(PostPutMediaRequestBuilder.buildByMedia(media))
        });
    }, [updated]);

    useEffect(() => {
        MediaFileAPI.getByMediaId(id).then(mediaFiles => setMediaFiles(mediaFiles));
    }, [filesUpdated]);

    const handleFormSubmit = (putRequest) => {
        MediaAPI.put(id, putRequest)
            .then(() => {
                setUpdated(true);
                setShowMediaForm(false);
            });
    };

    return (
        <>
            <FilesFormPopup show={showFilesForm} onChangeShow={setShowFilesForm} onSubmit={() => setFilesUpdated(true)} mediaFiles={mediaFiles} mediaId={id}/>
            <MediaFormPopup show={showMediaForm} onChangeShow={setShowMediaForm} onSubmit={handleFormSubmit} initialData={putMediaRequest}/>
            <Row>
                <Col lg={4}>
                    <MediaInfo media={media}/>
                    <Button className={"w-100"} onClick={() => setShowFilesForm(true)}>Файлы</Button>
                    <Button className={"w-100"} onClick={() => setShowMediaForm(true)}>Изменить</Button>
                </Col>
                <Col lg={8}>
                    <FilesCarousel filesUrls={mediaFiles.filter(mediaFile => mediaFile.fileType === FileTypes.main).map(mediaFile => mediaFile.url)}/>
                </Col>
            </Row>
        </>
    );
}