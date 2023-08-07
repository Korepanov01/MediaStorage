import React, { useState } from 'react';
import {SearchBar} from "./SearchBar";
import {TagsSelector} from "./TagsSelector";
import {Button} from "react-bootstrap";

const SEARCH_BUTTON_TITLE = "Найти"

export function MediaSearchMenu({onSearch: onSearch, searchParameters: searchParameters}) {
    const [selectedTagsIds, setSelectedTagsIds] = useState(new Set());
    const [searchString, setSearchString] = useState("")

    function handleClickSearchButton() {
        onSearch({...searchParameters, searchString: searchString, tagIds: [...selectedTagsIds], pageIndex: 0})
    }

    return (
        <>
            <SearchBar onSearchStringChange={setSearchString}/>
            <TagsSelector onSelect={setSelectedTagsIds} selectedTags={selectedTagsIds}/>
            <Button onClick={handleClickSearchButton}>{SEARCH_BUTTON_TITLE}</Button>
        </>
    );
}
