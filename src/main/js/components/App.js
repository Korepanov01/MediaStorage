import Container from "react-bootstrap/Container";

const React = require('react');

import {SearchMediaPage} from "./pages/SearchMediaPage";
import {Routes, Route } from 'react-router-dom';
import {MainPage} from "./pages/MainPage";
import {ProfilePage} from "./pages/ProfilePage";
import {MediaPage} from "./pages/MediaPage";
import {AppNavbar} from "./AppNavbar";
import {AdminPage} from "./pages/AdminPage";

export function App() {
    return (
        <Container>
            <AppNavbar/>
            <Routes>
                <Route path="/" element={<MainPage/>}/>
                <Route path="search" element={<SearchMediaPage/>}/>
                <Route path="profile" element={<ProfilePage/>}/>
                <Route path="admin" element={<AdminPage/>}/>
                <Route path="media/:id" element={<MediaPage/>}/>
            </Routes>
        </Container>
    );
}
