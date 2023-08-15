import React, {useLayoutEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import {Col, Row, Spinner} from "react-bootstrap";
import {getMediaById} from "../../apis/mediaAPI";
import {FilesCarousel} from "../fileCarousel";
import {MediaInfo} from "../mediaInfo";
import {FileTypes} from "../../enums/fileTypes";
import {MediaRedactor} from "../mediaRedactor";
import {getTagByMediaId} from "../../apis/tagAPI";
import {useSelector} from "react-redux";
import {getMediaFilesByMediaId} from "../../apis/mediaFileAPI";

export function MediaPage() {
    const {id} = useParams();

    const authUserId = useSelector(state => state.auth.user?.id);

    const [isLoading, setIsLoading] = useState(true);
    const [media, setMedia] = useState(null);
    const [mediaFiles, setMediaFiles] = useState([]);
    const [tags, setTags] = useState([])

    useLayoutEffect(() => {
        Promise.all([
            getTagByMediaId(id).then(({error, data: tags}) => {
                if (!error) setTags(tags);
            }),
            getMediaById(id).then(({data: media, error}) => {
                if (!error) setMedia(media);
            }),
            getMediaFilesByMediaId(id).then(({data: mediaFiles, error}) => {
                if (!error) setMediaFiles(mediaFiles)
            })
        ]).then(() => setIsLoading(false));
    }, []);

    return (
        <>
            {isLoading ? (
                <Spinner/>
            ) : (
                <Row>
                    <Col lg={4}>
                        <MediaInfo media={media} tags={tags}/>
                        {(authUserId === media.user.id) &&
                            <MediaRedactor media={media} mediaFiles={mediaFiles}
                                           tags={tags} onTagsChange={setTags}/>
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