import React from "react";
import {Form} from "react-bootstrap";
import {Text} from "../../text";

export default function SearchBar({onSearchStringChange}) {

    return (
        <Form.Control
            type="search"
            placeholder={Text.placeholders.searchString}
            className="me-2"
            aria-label="Search"
            onChange={ (e) => { onSearchStringChange(e.target.value) }}
        />
    );
}