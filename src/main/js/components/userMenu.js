import React from 'react';
import Container from "react-bootstrap/Container";
import {Button} from "react-bootstrap";

export function UserMenu({user: user, onAddMediaClick: handlerAddMediaClick}) {
   return (
        <Container fluid={true}>
            <h2>{user.name}</h2>
            <h3>{user.email}</h3>
            <Button className={"w-100"} onClick={handlerAddMediaClick}>Добавить медиа</Button>
        </Container>
    );
}
