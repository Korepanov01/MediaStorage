import Container from "react-bootstrap/Container";

const React = require('react');

import { Header } from "./Header.js"
import {MediaList} from "./MediaList";
import {MediaPage} from "./MediaPage";

export function App(props) {
    return (
        <>
            <Container>
                <Header/>
                <MediaPage/>
            </Container>
        </>
    );
}
