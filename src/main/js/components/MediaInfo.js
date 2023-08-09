import React, {useEffect, useState} from 'react';
import {TagAPI} from "../apis/TagAPI";
import {Accordion, Badge} from "react-bootstrap";
import {MediaBuilder} from "../models/Media";

MediaInfo.defaultProps = {
    media: MediaBuilder.getDefault()
}

export function MediaInfo({media: media}) {
    const [tags, setTags] = useState([])

    useEffect(() => {
        TagAPI.get({pageIndex: 0, pageSize: 100, mediaId: media?.id ?? 1}).then(response => {
            setTags(response.data);
        });
    }, []);

    return (
        <>
            <h1>{media?.name ?? ""}</h1>
            <h2>{media?.mediaType?.name ?? ""}</h2>
            <h3>{media?.category?.name ?? ""}</h3>
            <Accordion>
                {media?.description &&
                    <Accordion.Item eventKey="1">
                        <Accordion.Header>Описание</Accordion.Header>
                        <Accordion.Body>{media.description}</Accordion.Body>
                    </Accordion.Item>
                }
                {tags.length !== 0 &&
                    <Accordion.Item eventKey="2">
                        <Accordion.Header>Теги</Accordion.Header>
                        <Accordion.Body>
                            {tags.map(tag => (
                                <Badge key={tag.id}>{tag.name}</Badge>
                            ))}
                        </Accordion.Body>
                    </Accordion.Item>
                }
            </Accordion>
        </>
    );
}