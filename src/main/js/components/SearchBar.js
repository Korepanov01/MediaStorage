import React, {useState} from "react";
import Container from "react-bootstrap/Container";
import {Button, Col, Form, Row} from "react-bootstrap";

const PLACEHOLDER_TEXT = "Введите поисковый запрос"
const BUTTON_TEXT = "Поиск"

export function SearchBar() {
    const [searchInput, setSearchInput] = useState("");

    const handleChange = (e) => {
        e.preventDefault();
        setSearchInput(e.target.value);
    };

    return (
        <Form className="d-flex">
            <Form.Control
                type="search"
                placeholder={PLACEHOLDER_TEXT}
                className="me-2"
                aria-label="Search"
            />
            <Button>{BUTTON_TEXT}</Button>
        </Form>
    );
}