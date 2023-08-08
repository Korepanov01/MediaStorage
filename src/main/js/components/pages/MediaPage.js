import React, {useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import {Accordion, Badge, Col, Row} from "react-bootstrap";
import {FileRepository} from "../../repository/FileRepository";
import {MediaAPI} from "../../apis/MediaAPI";
import {FilesCarousel} from "../FileCarousel";
import Container from "react-bootstrap/Container";
import {TagAPI} from "../../apis/TagAPI";

export function MediaPage() {
    const {id} = useParams();
    const [media, setMedia] = useState(null);
    const [filesUrls, setFilesUrlsUrls] = useState([]);
    const [tags, setTags] = useState([])

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

    useEffect(() => {
        TagAPI.get({pageIndex: 0, pageSize: 100, mediaId: id}).then(response => {
            setTags(response.data);
        });
    }, []);

    return (
        <Container fluid={true}>
            <Row>
                <Col lg={4}>
                    {media !== null &&
                        <>
                            <h1>{media.name}</h1>
                            <h2>{media.mediaType.name}</h2>
                            <h3>{media.category.name}</h3>
                            <Accordion>
                                {media.description &&
                                    <Accordion.Item eventKey="1">
                                        <Accordion.Header>Описание</Accordion.Header>
                                        <Accordion.Body>{media.description}</Accordion.Body>
                                    </Accordion.Item>
                                }
                                {tags.length !== 0 &&
                                    <Accordion.Item eventKey="2">
                                        <Accordion.Header>Теги</Accordion.Header>
                                        <Accordion.Body>
                                            {tags.map(tag => (<Badge key={tag.id}>{tag.name}</Badge>))}
                                        </Accordion.Body>
                                    </Accordion.Item>
                                }
                            </Accordion>
                        </>
                    }
                </Col>
                <Col lg={8}>
                    <FilesCarousel filesUrls={filesUrls}/>
                </Col>
            </Row>
        </Container>
    );
}