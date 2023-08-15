import React, {useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import {Col, Row, Spinner} from "react-bootstrap";
import {getMediaById} from "../../apis/mediaAPI";
import {FilesCarousel} from "../fileCarousel";
import {MediaInfo} from "../mediaInfo";
import {FileTypes} from "../../enums/fileTypes";
import {getMediaFiles} from "../../apis/mediaFileAPI";
import {MediaRedactor} from "../mediaRedactor";
import {getTags} from "../../apis/tagAPI";
import {useSelector} from "react-redux";

export function MediaPage() {
    const {id} = useParams();

    const isLoggedIn = useSelector(state => state.auth.isLoggedIn);
    const authUserId = useSelector(state => state.auth.user.id);

    const [mediaFiles, setMediaFiles] = useState([]);
    const [tags, setTags] = useState([])

    const [isLoading, setIsLoading] = useState(true);
    const [media, setMedia] = useState(null);

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
            {isLoading ? (
                <Spinner/>
            ) : (
                <Row>
                    <Col lg={4}>
                        <MediaInfo media={media} tags={tags}/>
                        {(isLoggedIn && authUserId === media.user.id) &&
                            <MediaRedactor media={media} mediaFiles={mediaFiles} tags={tags} onTagsChange={setTags}/>
                        }
                    </Col>
                    <Col lg={8}>
                        <FilesCarousel
                            filesUrls={mediaFiles.filter(mediaFile => mediaFile.fileType === FileTypes.main).map(mediaFile => mediaFile.url)}/>
                    </Col>
                </Row>
            )}
        </>
    );
}