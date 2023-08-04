import React, {useState} from "react";
import {Button, Form} from "react-bootstrap";

const PLACEHOLDER_TEXT = "Введите поисковый запрос"
const BUTTON_TEXT = "Поиск"

export function SearchBar({ onSearch }) {
    const [searchInput, setSearchInput] = useState("");

    const handleChange = (e) => {
        e.preventDefault();
        setSearchInput(e.target.value);
    };

    const handleSearch = (e) => {
        e.preventDefault();
        onSearch(searchInput);
    };

    return (
        <Form className="d-flex">
            <Form.Control
                type="search"
                placeholder={PLACEHOLDER_TEXT}
                className="me-2"
                aria-label="Search"
                value={searchInput}
                onChange={handleChange}
            />
            <Button onClick={handleSearch}>{BUTTON_TEXT}</Button>
        </Form>
    );
}