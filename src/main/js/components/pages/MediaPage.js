import React, {useEffect, useState} from 'react';
import {useNavigate, useParams} from "react-router-dom";
import {Button, Col, Row} from "react-bootstrap";
import {FileRepository} from "../../repository/FileRepository";
import {MediaAPI} from "../../apis/MediaAPI";
import {FilesCarousel} from "../FileCarousel";
import {MediaFormPopup} from "../MediaFormPopup";
import {MediaInfo} from "../MediaInfo";
import {USER_ID} from "../../index";

export function MediaPage() {
    const {id} = useParams();
    const [media, setMedia] = useState(null);
    const [filesUrls, setFilesUrlsUrls] = useState([]);
    const [showMediaForm, setShowMediaForm] = useState(false);
    const [updated, setUpdated] = useState(false);

    const navigate = useNavigate();

    useEffect(() => {
        MediaAPI.getById(id).then(response => {
            console.log(JSON.stringify(response.data))
            setMedia(response.data);
        });
    }, []);

    useEffect(() => {
        FileRepository.getMainUrls(id).then(urls => {
            setFilesUrlsUrls(urls);
        });
    }, []);

    const handleFormSubmit = (newMedia) => {
        console.log(JSON.stringify(newMedia))
        MediaAPI.put(id, newMedia)
            .then(() => {
                let newMediaId = response.data.id;
                navigate(`/media/${newMediaId}`);
            });
    };

    return (
        <>
            <MediaFormPopup show={showMediaForm} onChangeShow={setShowMediaForm} userId={USER_ID} onSubmit={handleFormSubmit}/>
            <Row>
                <Col lg={4}>
                    {media !== null &&
                        <MediaInfo media={media}/>
                    }
                    <Button onClick={() => setShowMediaForm(true)}>Изменить</Button>
                </Col>
                <Col lg={8}>
                    <FilesCarousel filesUrls={filesUrls}/>
                </Col>
            </Row>
        </>
    );
}