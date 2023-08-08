import Container from "react-bootstrap/Container";

const React = require('react');

import { Header } from "./Header.js"
import {SearchMediaPage} from "./pages/SearchMediaPage";

import { BrowserRouter, Routes, Route } from 'react-router-dom';
import {MainPage} from "./pages/MainPage";
import {ProfilePage} from "./pages/ProfilePage";
import {MediaPage} from "./pages/MediaPage";

export function App() {
    return (
        <BrowserRouter>
            <Container>
                <Header/>
                    <Routes>
                        <Route path="/" element={<MainPage/>}/>
                        <Route path="search" element={<SearchMediaPage/>}/>
                        <Route path="profile" element={<ProfilePage/>}/>
                        <Route path="media/:id" element={<MediaPage/>}/>
                    </Routes>
            </Container>
        </BrowserRouter>
    );
}
