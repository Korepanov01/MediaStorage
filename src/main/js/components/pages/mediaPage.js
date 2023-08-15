import React, {useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import {Col, Row} from "react-bootstrap";
import {getMediaById} from "../../apis/mediaAPI";
import {FilesCarousel} from "../fileCarousel";
import {MediaInfo} from "../mediaInfo";
import {FileTypes} from "../../enums/fileTypes";
import {getMediaFiles} from "../../apis/mediaFileAPI";
import {MediaRedactor} from "../mediaRedactor";
import {getTags} from "../../apis/tagAPI";

export function MediaPage() {
    const {id} = useParams();

    const [media, setMedia] = useState({
        id: 0,
        name: "string",
        user: {
            id: 0,
            name: "string"
        }});
    const [mediaFiles, setMediaFiles] = useState([]);
    const [tags, setTags] = useState([])

    useEffect(() => {
        getTags({mediaId: id}).then(result => {
            if (!result.error) setTags(result.data);
        });
    }, []);

    useEffect(() => {
        getMediaById(id).then(({data, error}) => {
            if (!error) setMedia(data);
        });
    }, []);

    useEffect(() => {
        getMediaFiles({id}).then(({data, error}) => {
            if (!error) setMediaFiles(data)
        });
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