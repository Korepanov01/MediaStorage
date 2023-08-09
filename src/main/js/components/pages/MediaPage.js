import React, {useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import {Button, Col, Row} from "react-bootstrap";
import {MediaAPI} from "../../apis/MediaAPI";
import {FilesCarousel} from "../FileCarousel";
import {MediaInfo} from "../MediaInfo";
import {MediaBuilder} from "../../models/Media";
import {FileTypes} from "../../enums/FileTypes";
import {MediaFileAPI} from "../../apis/MediaFileAPI";
import {MediaRedactor} from "../MediaRedactor";

export function MediaPage() {
    const {id} = useParams();

    const [media, setMedia] = useState(MediaBuilder.getDefault());
    const [mediaFiles, setMediaFiles] = useState([]);

    useEffect(() => {
        MediaAPI.getById(id).then(media => {
            setMedia(media);
        });
    }, []);

    useEffect(() => {
        MediaFileAPI.getByMediaId(id).then(mediaFiles => setMediaFiles(mediaFiles));
    }, []);

    return (
        <>
            <Row>
                <Col lg={4}>
                    <MediaInfo media={media}/>
                    <MediaRedactor media={media} mediaFiles={mediaFiles}/>
                </Col>
                <Col lg={8}>
                    <FilesCarousel filesUrls={mediaFiles.filter(mediaFile => mediaFile.fileType === FileTypes.main).map(mediaFile => mediaFile.url)}/>
                </Col>
            </Row>
        </>
    );
}