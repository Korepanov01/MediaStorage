import React from "react";
import {Form} from "react-bootstrap";

const PLACEHOLDER_TEXT = "Введите поисковый запрос"

export function SearchBar({ onSearchStringChange }) {

    return (
        <Form className="d-flex">
            <Form.Control
                type="search"
                placeholder={PLACEHOLDER_TEXT}
                className="me-2"
                aria-label="Search"
                onChange={ (e) => { onSearchStringChange(e.target.value) }}
            />
        </Form>
    );
}