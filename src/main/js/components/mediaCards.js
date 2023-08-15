import React from "react";
import { MediaCard } from './mediaCard';
import {CardGroup, Col, Row} from "react-bootstrap";
import Container from "react-bootstrap/Container";
import {utils} from "../utils";

export function MediaCards({medias: medias, cardsInRow: cardsInRow}) {
    return (
        <CardGroup>
            <Container fluid>
                {utils(medias, cardsInRow).map((chunk, chunkIndex) => (
                    <Row key={`row-${chunkIndex}`} className={"justify-content-between"}>
                        {chunk.map((media) => (
                            <Col lg = {12 / cardsInRow} key={`col-${media.id}`}>
                                <MediaCard  media={media} />
                            </Col>
                        ))}
                    </Row>
                ))}
            </Container>
        </CardGroup>
    );
}