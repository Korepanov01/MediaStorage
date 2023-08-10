import React, { useState } from 'react';
import {SearchBar} from "./selectors/SearchBar";
import {TagsSelector} from "./selectors/TagsSelector";
import {Button} from "react-bootstrap";
import {MediaTypeSelector} from "./selectors/MediaTypeSelector";
import {CategorySelector} from "./selectors/CategorySelector";

const SEARCH_BUTTON_TITLE = "Найти"

export function MediaSearchMenu({onSearch: onSearch, searchParameters: searchParameters}) {
    const [selectedTypesIds, setSelectedTypesIds] = useState(new Set());
    const [selectedTagsIds, setSelectedTagsIds] = useState(new Set());
    const [searchString, setSearchString] = useState("")
    const [categoryId, setCategoryId] = useState(null)

    function handleClickSearchButton() {
        onSearch({...searchParameters,
            categoryId: categoryId,
            typeIds: [...selectedTypesIds],
            searchString: searchString, 
            tagIds: [...selectedTagsIds], 
            pageIndex: 0})
    }

    return (
        <>
            <SearchBar onSearchStringChange={setSearchString}/>
            <MediaTypeSelector selectedTypesIds={selectedTypesIds} onSelect={setSelectedTypesIds}/>
            <TagsSelector onSelect={setSelectedTagsIds} selectedTagsIds={selectedTagsIds}/>
            <CategorySelector onSelect={setCategoryId}/>
            <Button onClick={handleClickSearchButton}>{SEARCH_BUTTON_TITLE}</Button>
        </>
    );
}
