import React from "react";
import { Card } from "react-bootstrap";

export function Media({ media }) {
    return (
        <Card>
            <Card.Body>
                <Card.Title>{media.name}</Card.Title>
                <Card.Subtitle>User: {media.user.name}</Card.Subtitle>
            </Card.Body>
        </Card>
    );
}