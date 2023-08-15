import Container from "react-bootstrap/Container";

const React = require('react');

import {SearchMediaPage} from "./pages/SearchMediaPage";
import {Routes, Route} from 'react-router-dom';
import {MainPage} from "./pages/mainPage";
import {ProfilePage} from "./pages/profilePage";
import {MediaPage} from "./pages/mediaPage";
import {AppNavbar} from "./appNavbar";
import {AdminPage} from "./pages/adminPage";

export function App() {
    return (
        <Container>
            <AppNavbar/>
            <Routes>
                <Route path="/" element={<MainPage/>}/>
                <Route path="search" element={<SearchMediaPage/>}/>
                <Route path="profile" element={<ProfilePage/>}/>
                <Route path="admin/*" element={<AdminPage/>}/>
                <Route path="media/:id" element={<MediaPage/>}/>
            </Routes>
        </Container>
    );
}
