import React, { useState } from 'react';
import {SearchBar} from "./SearchBar";
import {TagsSelector} from "./TagsSelector";
import {Button} from "react-bootstrap";

const SEARCH_BUTTON_TITLE = "Найти"

export function MediaSearchMenu() {
    const [selectedTagsIds, setSelectedTagsIds] = useState(new Set());
    const [searchString, setSearchString] = useState("")

    return (
        <>
            <SearchBar/>
            <TagsSelector onSelect={setSelectedTagsIds} selectedTags={selectedTagsIds}/>
            <Button title={SEARCH_BUTTON_TITLE}/>
        </>
    );
}
