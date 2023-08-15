import React, {useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import {Col, Row} from "react-bootstrap";
import {MediaAPI} from "../../apis/mediaAPI";
import {FilesCarousel} from "../fileCarousel";
import {MediaInfo} from "../mediaInfo";
import {MediaBuilder} from "../../models/Media";
import {FileTypes} from "../../enums/fileTypes";
import {MediaFileAPI} from "../../apis/mediaFileAPI";
import {MediaRedactor} from "../mediaRedactor";
import {getTags, TagAPI} from "../../apis/tagAPI";

export function MediaPage() {
    const {id} = useParams();

    const [media, setMedia] = useState(MediaBuilder.getDefault());
    const [mediaFiles, setMediaFiles] = useState([]);
    const [tags, setTags] = useState([])

    useEffect(() => {
        getTags({mediaId: id}).then(result => {
            if (!result.error) setTags(result.data);
        });
    }, []);

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
                    <MediaInfo media={media} tags={tags}/>
                    <MediaRedactor media={media} mediaFiles={mediaFiles} tags={tags} onTagsChange={setTags}/>
                </Col>
                <Col lg={8}>
                    <FilesCarousel filesUrls={mediaFiles.filter(mediaFile => mediaFile.fileType === FileTypes.main).map(mediaFile => mediaFile.url)}/>
                </Col>
            </Row>
        </>
    );
}