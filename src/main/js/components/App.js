import Container from "react-bootstrap/Container";

const React = require('react');

import { Header } from "./Header.js"

export function App(props) {
    return (
        <>
            <Container>
                <Header/>
            </Container>
        </>
    );
}
