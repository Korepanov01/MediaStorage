import React, {useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import {Button, Col, Row} from "react-bootstrap";
import {MediaAPI} from "../../apis/MediaAPI";
import {FilesCarousel} from "../FileCarousel";
import {MediaFormPopup} from "../MediaFormPopup";
import {MediaInfo} from "../MediaInfo";
import {USER_ID} from "../../index";
import {MediaBuilder} from "../../models/Media";
import {PostPutMediaRequestBuilder} from "../../models/PostPutMediaRequest";
import {FileAPI} from "../../apis/FileAPI";

export function MediaPage() {
    const {id} = useParams();
    const [media, setMedia] = useState(MediaBuilder.getDefault());
    const [filesUrls, setFilesUrlsUrls] = useState([]);
    const [showMediaForm, setShowMediaForm] = useState(false);

    const [putMediaRequest, setPutMediaRequest] = useState(PostPutMediaRequestBuilder.getDefault());
    const [updated, setUpdated] = useState(false);

    useEffect(() => {
        MediaAPI.getById(id).then(media => {
            setMedia(media);
            setPutMediaRequest(PostPutMediaRequestBuilder.buildByMedia(media))
        });
    }, [updated]);

    useEffect(() => {
        FileAPI.getMainUrls(id).then(urls => {
            setFilesUrlsUrls(urls);
        });
    }, []);

    const handleFormSubmit = (putRequest) => {
        console.log(JSON.stringify(putRequest))
        MediaAPI.put(id, putRequest)
            .then(() => {
                setUpdated(true);
                setShowMediaForm(false);
            });
    };

    return (
        <>
            <MediaFormPopup show={showMediaForm} onChangeShow={setShowMediaForm} userId={USER_ID} onSubmit={handleFormSubmit} initialData={putMediaRequest}/>
            <Row>
                <Col lg={4}>
                    <MediaInfo media={media}/>
                    <Button className={"w-100"} onClick={() => setShowMediaForm(true)}>Изменить</Button>
                </Col>
                <Col lg={8}>
                    <FilesCarousel filesUrls={filesUrls}/>
                </Col>
            </Row>
        </>
    );
}