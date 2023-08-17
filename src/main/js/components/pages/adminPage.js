import React from 'react';
import Nav from "react-bootstrap/Nav";
import {Link, Route, Routes} from "react-router-dom";
import TagsTable from "../admintables/tagsTable";
import {useSelector} from "react-redux";
import {useNavigate} from "react-router-dom";
import {Roles} from "../../enums/roles";

export function AdminPage() {
    const navigate = useNavigate()
    const user = useSelector(state => state.auth.user?.roles?.includes(Roles.ADMIN) ?? navigate("/"));

    return (
        <>
            <Nav variant="tabs" defaultActiveKey="users">
                <Nav.Item>
                    <Nav.Link as={Link} to={"users"} eventKey={"users"}>Пользователи</Nav.Link>
                </Nav.Item>
                <Nav.Item>
                    <Nav.Link as={Link} to={"tags"} eventKey={"users"}>Теги</Nav.Link>
                </Nav.Item>
                <Nav.Item>
                    <Nav.Link as={Link} to={"categories"} eventKey={"users"}>Категории</Nav.Link>
                </Nav.Item>
            </Nav>
            <Routes>
                <Route path="users" element={<h1>users</h1>}/>
                <Route path="tags" element={<TagsTable/>}/>
                <Route path="categories" element={<h1>categories</h1>}/>
            </Routes>
        </>
    );
}