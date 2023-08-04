import React from "react";
import { Card } from "react-bootstrap";
import {TagsCarousel} from "./TagsCarousel";

export function MediaCard({ media }) {
    return (
        <Card>
            <Card.Body>
                <Card.Title>{media.name}</Card.Title>
                <Card.Subtitle>Пользователь: {media.user.name}</Card.Subtitle>
                <Card.Footer><TagsCarousel mediaId={ media.id }/></Card.Footer>
            </Card.Body>
        </Card>
    );
}