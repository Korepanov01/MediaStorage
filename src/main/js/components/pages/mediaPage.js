import React, {useLayoutEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import {Col, Row, Spinner} from "react-bootstrap";
import {getMediaById} from "../../apis/mediaAPI";
import {FilesCarousel} from "../fileCarousel";
import {MediaInfo} from "../mediaInfo";
import {FileTypes} from "../../enums/fileTypes";
import {MediaRedactor} from "../mediaRedactor";
import {useSelector} from "react-redux";

export function MediaPage() {
    const {id} = useParams();

    const authUserId = useSelector(state => state.auth.user?.id);

    const [isLoading, setIsLoading] = useState(true);
    const [media, setMedia] = useState(null);

    useLayoutEffect(() => {
        getMediaById(id).then(({data: media, error}) => {
            if (!error) {
                setMedia(media);
                setIsLoading(false);
            }
        });
    }, []);

    return (
        <>
            {isLoading ? (
                <Spinner/>
            ) : (
                <Row>
                    <Col lg={4}>
                        <MediaInfo media={media}/>
                        {(authUserId === media.user.id) &&
                            <MediaRedactor media={media} setMedia={setMedia}/>
                        }
                    </Col>
                    <Col lg={8}>
                        {media.files.length !== 0 &&
                            <FilesCarousel filesUrls={media.files.filter(file => file.type === FileTypes.main).map(file => file.url)}/>
                            ||
                            <h1>Нет файлов</h1>
                        }
                    </Col>
                </Row>
            )}
        </>
    );
}