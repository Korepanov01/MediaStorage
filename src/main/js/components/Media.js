import React, {useState} from "react";
import {Card} from "react-bootstrap";

export function Media({ media }) {
    return (
        <Card>
            <Card.Body>
                <Card.Title>{media.name}</Card.Title>
                <Card.Subtitle className="mb-2 text-muted">User: {media.user.name}</Card.Subtitle>
            </Card.Body>
        </Card>
    );
}