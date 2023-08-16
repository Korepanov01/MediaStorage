import React from "react";
import {Card} from "react-bootstrap";
import {Title} from "./title";

export function InfoCard({title, children}) {
    return (
        <Card>
            <Card.Header><Title level={5}>{title}</Title></Card.Header>
            <Card.Body>
                {children}
            </Card.Body>
        </Card>
    );
}