import Container from "react-bootstrap/Container";

const React = require('react');

import { Header } from "./Header.js"
import {MediaPage} from "./MediaPage";

import { BrowserRouter, Routes, Route } from 'react-router-dom';

export function App(props) {
    return (
        <BrowserRouter>
            <Container>
                <Header/>
                    <Routes>
                        <Route path="*" element={<MediaPage/>}/>
                        <Route path="search" element={<MediaPage/>}/>
                        <Route path="profile" element={<h1>Profile</h1>}/>
                    </Routes>
            </Container>
        </BrowserRouter>
    );
}
