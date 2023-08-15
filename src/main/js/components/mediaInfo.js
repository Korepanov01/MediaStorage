import React from 'react';
import {Accordion, Badge} from "react-bootstrap";
import {MediaBuilder} from "../models/Media";

MediaInfo.defaultProps = {
    media: MediaBuilder.getDefault()
}

export function MediaInfo({media: media, tags: tags}) {
    return (
        <>
            <h1>{media.name}</h1>
            <h2>{media.mediaType.name}</h2>
            <h3>{media.category.name}</h3>
            <Accordion alwaysOpen>
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