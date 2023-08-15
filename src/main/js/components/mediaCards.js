import React from "react";
import { MediaCard } from './mediaCard';
import {CardGroup, Col, Row} from "react-bootstrap";
import Container from "react-bootstrap/Container";
import {tools} from "../tools";

export function MediaCards({medias, cardsInRow}) {
    return (
        <CardGroup>
            {tools(medias, cardsInRow).map((chunk, chunkIndex) => (
                <Row key={`row-${chunkIndex}`} className={"justify-content-between"}>
                    {chunk.map((media) => (
                        <Col lg = {12 / cardsInRow} key={`col-${media.id}`}>
                            <MediaCard  media={media} />
                        </Col>
                    ))}
                </Row>
            ))}
        </CardGroup>
    );
}