import React, {useEffect} from 'react';
import Nav from "react-bootstrap/Nav";
import {Link, Navigate, Route, Routes, useNavigate} from "react-router-dom";
import * as PropTypes from "prop-types";
import TagsTable from "../admintables/tagsTable";

function Redirect(props) {
    return null;
}

Redirect.propTypes = {to: PropTypes.string};

export function AdminPage() {
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