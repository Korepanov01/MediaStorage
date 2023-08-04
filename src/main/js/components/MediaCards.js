import React from "react";
import { MediaCard } from './MediaCard';
import {CardGroup, Col, Row} from "react-bootstrap";
import Container from "react-bootstrap/Container";
import {getChunks} from "../utils/GetChunks";

export function MediaCards({medias: medias, cardsInRow: cardsInRow}) {
    return (
        <CardGroup>
            <Container fluid>
            {getChunks(medias, cardsInRow).map((chunk, i) => (
                <Row key={ i } className={"justify-content-between"}>
                    {chunk.map((media) => (
                        <Col lg = {12 / cardsInRow} key={media.id}>
                            <MediaCard  media={media} />
                        </Col>
                    ))}
                </Row>
            ))}
                </Container>
        </CardGroup>
    );
}