import Container from "react-bootstrap/Container";

const React = require('react');

import {SearchMediaPage} from "./pages/SearchMediaPage";
import {HashRouter as Browser, Routes, Route } from 'react-router-dom';
import {MainPage} from "./pages/MainPage";
import {ProfilePage} from "./pages/ProfilePage";
import {MediaPage} from "./pages/MediaPage";
import {AppNavbar} from "./AppNavbar";

export function App() {
    return (
        <Browser>
            <Container>
                <AppNavbar/>
                    <Routes>
                        <Route path="/" element={<MainPage/>}/>
                        <Route path="search" element={<SearchMediaPage/>}/>
                        <Route path="profile" element={<ProfilePage/>}/>
                        <Route path="media/:id" element={<MediaPage/>}/>
                    </Routes>
            </Container>
        </Browser>
    );
}
