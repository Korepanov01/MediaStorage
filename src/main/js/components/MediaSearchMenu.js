import React, { useState } from 'react';
import {SearchBar} from "./SearchBar";
import {TagsSelector} from "./TagsSelector";
import {Button} from "react-bootstrap";
import {TypeSelector} from "./TypeSelector";

const SEARCH_BUTTON_TITLE = "Найти"

export function MediaSearchMenu({onSearch: onSearch, searchParameters: searchParameters}) {
    const [selectedTypesIds, setSelectedTypesIds] = useState(new Set());
    const [selectedTagsIds, setSelectedTagsIds] = useState(new Set());
    const [searchString, setSearchString] = useState("")

    function handleClickSearchButton() {
        onSearch({...searchParameters, 
            typeIds: [...selectedTypesIds],
            searchString: searchString, 
            tagIds: [...selectedTagsIds], 
            pageIndex: 0})
    }

    return (
        <>
            <SearchBar onSearchStringChange={setSearchString}/>
            <TypeSelector selectedTypesIds={selectedTypesIds} onSelect={setSelectedTypesIds}/>
            <TagsSelector onSelect={setSelectedTagsIds} selectedTags={selectedTagsIds}/>
            <Button onClick={handleClickSearchButton}>{SEARCH_BUTTON_TITLE}</Button>
        </>
    );
}
