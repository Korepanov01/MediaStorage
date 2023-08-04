import React from "react";
import { Media } from './Media';
import {CardGroup, ListGroup, Row} from "react-bootstrap";

export function MediaList({ medias: medias }) {

    return (
        <>
            {medias.map((media) => (
                <Media key={media.id} media={media} />
            ))}
        </>
    );
}