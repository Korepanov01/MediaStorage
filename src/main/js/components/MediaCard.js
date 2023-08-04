import React from "react";
import { Card } from "react-bootstrap";
import {TagsCarousel} from "./TagsCarousel";

export function MediaCard({ media }) {
    return (
        <Card className={"flex-fill"} style={ {margin: "5px"} }>
            <Card.Body>
                <Card.Title>{media.name}</Card.Title>
                <Card.Subtitle>Пользователь: {media.user.name}</Card.Subtitle>
            </Card.Body>
            <Card.Footer>
                <TagsCarousel mediaId={ media.id }/>
            </Card.Footer>
        </Card>
    );
}